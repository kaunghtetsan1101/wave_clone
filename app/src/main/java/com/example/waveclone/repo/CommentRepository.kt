package com.example.waveclone.repo

import androidx.room.withTransaction
import com.example.waveclone.db.AppDatabase
import com.example.waveclone.db.entity.comment.CommentEntity
import com.example.waveclone.db.entity.comment.ReplyEntity
import com.example.waveclone.model.pojo.CommentWithReplies
import com.example.waveclone.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CommentRepository @Inject constructor(
    private val appDatabase: AppDatabase
) {
    private val dao = appDatabase.commentReplyDao()

    suspend fun insertComment(comment: CommentEntity): Flow<DataState<CommentEntity>> =
        flow {
            emit(DataState.Loading)
            appDatabase.withTransaction {
                dao.insertComment(comment)
            }
            emit(DataState.Success(comment))
        }

    suspend fun insertReply(reply: ReplyEntity): Flow<DataState<Boolean>> =
        flow {
            emit(DataState.Loading)
            appDatabase.withTransaction {
                dao.insertReply(reply)
            }
            emit(DataState.Success(true))
        }

    suspend fun getComments(postId: String): Flow<DataState<List<CommentWithReplies>>> =
        flow {
            emit(DataState.Loading)
            val data = dao.findById(postId)
            emit(DataState.Success(data))
        }
}