package com.example.waveclone.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.waveclone.db.AppDatabase
import com.example.waveclone.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDb(app: Application): AppDatabase {
        return Room
            .databaseBuilder(
                app,
                AppDatabase::class.java,
                DATABASE_NAME
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideContext(app : Application) : Context {
        return app.applicationContext
    }
}