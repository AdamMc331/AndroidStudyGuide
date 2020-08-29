package com.adammcneilly.androidstudyguide.di

import android.content.Context
import com.adammcneilly.androidstudyguide.data.local.ArticleDatabase
import com.adammcneilly.androidstudyguide.data.local.RoomArticleDatabase
import com.adammcneilly.androidstudyguide.data.local.RoomStudyGuideDatabase
import com.adammcneilly.androidstudyguide.data.remote.androidessence.AndroidEssenceRetrofitAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * This Hilt [Module] is responsible for defining how we create any Retrofit services used in the
 * study guide application.
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
object ThirdPartyModule {
    @Provides
    fun provideAndroidEssenceRetrofitAPI(): AndroidEssenceRetrofitAPI {
        return AndroidEssenceRetrofitAPI.getDefaultApi()
    }

    @Provides
    fun provideArticleDatabase(@ApplicationContext appContext: Context): ArticleDatabase {
        val roomDatabase = RoomStudyGuideDatabase.createDatabase(appContext)
        return RoomArticleDatabase(roomDatabase)
    }
}
