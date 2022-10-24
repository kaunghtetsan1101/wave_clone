package com.example.waveclone.ui.comment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.waveclone.db.entity.comment.CommentEntity
import com.example.waveclone.db.entity.comment.ReplyEntity
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
    val commentDescription = MutableLiveData<String>()
    val commentEntity = MutableLiveData<CommentEntity?>()
    val commentReplyId = MutableLiveData<String?>()
    val isReply = MutableLiveData(false)

    val data = Pager(
        PagingConfig(
            pageSize = 5,
            enablePlaceholders = false,
            initialLoadSize = 5
        ),
    ) {
        repository.getPagedCommentList()
    }.flow.cachedIn(viewModelScope)

    fun commentInsert() {
        viewModelScope.launch {
            commentDescription.value?.let {
                if (it.isNotEmpty()) {
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
                                }
                                else -> {
                                    println("Error in Inserting.......")
                                }
                            }
                        }
                }

            }
        }
    }

    fun replyInsert() {
        viewModelScope.launch {
            commentDescription.value?.let {
                repository.insertReply(
                    ReplyEntity(
                        id = "R${Random.nextInt(0, 1000)}",
                        userId = "U${Random.nextInt(0, 1000)}",
                        userName = "Hola${Random.nextInt(0, 1000)}",
                        postId = POST_ID,
                        commentReplyId = commentReplyId.value ?: commentEntity.value?.id!!,
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
                                commentEntity.postValue(null)
                                commentReplyId.postValue(null)
                                isReply.postValue(false)
                            }
                            else -> {
                                println("Error in Inserting.......")
                            }
                        }
                    }
            }
        }
    }

}