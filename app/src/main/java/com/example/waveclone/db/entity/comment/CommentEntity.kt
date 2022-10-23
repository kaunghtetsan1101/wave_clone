package com.example.waveclone.db.entity.comment

import androidx.room.*
import com.example.waveclone.utils.COMMENT
import com.example.waveclone.utils.REPLY

@Entity(tableName = COMMENT)
data class CommentEntity(
    @PrimaryKey
    @ColumnInfo(name = "ID")
    val id: String,
    @ColumnInfo(name = "USER_ID")
    val userId: String,
    @ColumnInfo(name = "USER_NAME")
    val userName: String,
    @ColumnInfo(name = "POST_ID")
    val postId: String = "P001",
    @ColumnInfo(name = "DESCRIPTION")
    val description: String
)

@Entity(
    tableName = REPLY,
    indices = [
        Index(value = arrayOf("COMMENT_REPLY_ID"))
    ],
    foreignKeys = [
        ForeignKey(
            entity = CommentEntity::class,
            parentColumns = ["ID"],
            childColumns = ["COMMENT_REPLY_ID"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ReplyEntity(
    @PrimaryKey
    @ColumnInfo(name = "ID")
    val id: String,
    @ColumnInfo(name = "USER_ID")
    val userId: String,
    @ColumnInfo(name = "USER_NAME")
    val userName: String,
    @ColumnInfo(name = "POST_ID")
    val postId: String = "P001",
    @ColumnInfo(name = "COMMENT_REPLY_ID")
    val commentReplyId: String,
    @ColumnInfo(name = "DESCRIPTION")
    val description: String
)
