package com.adammcneilly.androidstudyguide.di

import android.content.Context
import android.util.Log
import com.adammcneilly.androidstudyguide.data.local.ArticleDatabase
import com.adammcneilly.androidstudyguide.data.local.RoomArticleDatabase
import com.adammcneilly.androidstudyguide.data.local.RoomStudyGuideDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideArticleDatabase(@ApplicationContext appContext: Context): ArticleDatabase {
        Log.d("ARM", "Creating a Database")
        val roomDatabase = RoomStudyGuideDatabase.createDatabase(appContext)
        return RoomArticleDatabase(roomDatabase)
    }
}
