package com.example.waveclone.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.waveclone.db.dao.TransactionInfoDao
import com.example.waveclone.db.dao.comment.CommentDao
import com.example.waveclone.db.entity.TransactionInfoEntity
import com.example.waveclone.db.entity.comment.CommentEntity
import com.example.waveclone.db.entity.comment.ReplyEntity

@Database(
    entities = [TransactionInfoEntity::class, CommentEntity::class, ReplyEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionInfoDao(): TransactionInfoDao
    abstract fun commentReplyDao(): CommentDao
}