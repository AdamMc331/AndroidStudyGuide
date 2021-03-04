package com.adammcneilly.androidstudyguide.di

import android.content.Context
import com.adammcneilly.androidstudyguide.data.local.ArticleDatabase
import com.adammcneilly.androidstudyguide.data.local.RoomArticleDatabase
import com.adammcneilly.androidstudyguide.data.local.RoomStudyGuideDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideArticleDatabase(@ApplicationContext appContext: Context): ArticleDatabase {
        val roomDatabase = RoomStudyGuideDatabase.createDatabase(appContext)
        return RoomArticleDatabase(roomDatabase)
    }
}
