package com.example.waveclone.db.dao.comment

import androidx.paging.PagingSource
import androidx.room.*
import com.example.waveclone.db.entity.comment.CommentEntity
import com.example.waveclone.db.entity.comment.ReplyEntity
import com.example.waveclone.model.pojo.CommentWithReplies
import com.example.waveclone.utils.COMMENT

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(
        comment: CommentEntity
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReply(
        reply: ReplyEntity
    )

    @Transaction
    @Query("SELECT * FROM $COMMENT WHERE POST_ID = :id")
    suspend fun findById(id: String): List<CommentWithReplies>

    @Transaction
    @Query("SELECT * FROM $COMMENT ORDER BY id ASC")
    fun getPagedCommentList(): PagingSource<Int, CommentWithReplies>
}