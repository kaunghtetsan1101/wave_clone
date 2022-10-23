package com.example.waveclone.model.pojo

import androidx.room.Embedded
import androidx.room.Relation
import com.example.waveclone.db.entity.comment.CommentEntity
import com.example.waveclone.db.entity.comment.ReplyEntity


data class CommentWithReplies(
    @Embedded
    val commentEntity: CommentEntity,
    @Relation(
        parentColumn = "ID",
        entityColumn = "COMMENT_REPLY_ID",
        entity = ReplyEntity::class
    )
    val replies: List<ReplyEntity>? = emptyList()
)
