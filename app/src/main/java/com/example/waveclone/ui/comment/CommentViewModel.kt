package com.example.waveclone.ui.comment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waveclone.db.entity.comment.CommentEntity
import com.example.waveclone.db.entity.comment.ReplyEntity
import com.example.waveclone.model.pojo.CommentWithReplies
import com.example.waveclone.repo.CommentRepository
import com.example.waveclone.utils.DataState
import com.example.waveclone.utils.POST_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val repository: CommentRepository
) : ViewModel() {
    val comments = MutableLiveData<MutableList<CommentWithReplies>>()
    val commentDescription = MutableLiveData<String>()

    init {
        commentsLoad()
//        replyInsert()
    }

    fun commentInsert() {
        viewModelScope.launch {
            commentDescription.value?.let {
                if (it.isNotEmpty())
                    repository.insertComment(
                        CommentEntity(
                            id = "C${Random.nextInt(0, 1000)}",
                            userId = "U${Random.nextInt(0, 1000)}",
                            userName = "Homa${Random.nextInt(0, 1000)}",
                            postId = POST_ID,
                            description = it
                        )
                    ).catch { }
                        .flowOn(Dispatchers.IO)
                        .collectIndexed { _, state ->
                            when (state) {
                                is DataState.Loading -> {
                                    println("Loading.........")
                                }
                                is DataState.Success -> {
                                    println(state.result)
                                    commentDescription.postValue("")
                                    comments.value?.add(
                                        CommentWithReplies(
                                            commentEntity = state.result
                                        )
                                    )
                                }
                                else -> {
                                    println("Error in Inserting.......")
                                }
                            }
                        }
            }
        }
    }

    private fun replyInsert() {
        viewModelScope.launch {
            repository.insertReply(
                ReplyEntity(
                    id = "R001",
                    userId = "U001",
                    userName = "Kaung Htet San",
                    postId = POST_ID,
                    commentReplyId = "C001",
                    description = "This is reply one."
                )
            ).catch { }
                .flowOn(Dispatchers.IO)
                .collectIndexed { _, state ->
                    when (state) {
                        is DataState.Loading -> {
                            println("Loading.........")
                        }
                        is DataState.Success -> {
                            println(state.result)
                        }
                        else -> {
                            println("Error in Inserting.......")
                        }
                    }
                }
        }
    }

    fun commentsLoad() {
        viewModelScope.launch {
            repository.getComments(POST_ID)
                .catch {
                    this.emit(
                        DataState.Error("Comment load Error.....")
                    )
                }
                .flowOn(Dispatchers.IO)
                .collectIndexed { _, state ->
                    when (state) {
                        is DataState.Loading -> {
                            println("Loading.........")
                        }
                        is DataState.Success -> {
                            println(state.result)
                            val data = listOf(
                                CommentWithReplies(
                                    commentEntity = CommentEntity(
                                        id = "C001",
                                        userId = "U001",
                                        userName = "Kaung Htet San",
                                        postId = POST_ID,
                                        description = "This is comment one."
                                    )
                                ),
                                CommentWithReplies(
                                    commentEntity = CommentEntity(
                                        id = "C002",
                                        userId = "U002",
                                        userName = "Tiger",
                                        postId = POST_ID,
                                        description = "This is comment two."
                                    )
                                )
                            )
                            println(state.result)
                            comments.postValue(state.result.toMutableList())
                        }
                        else -> {
                            println("Comment load Error.....")
                        }
                    }
                }
        }
    }

}