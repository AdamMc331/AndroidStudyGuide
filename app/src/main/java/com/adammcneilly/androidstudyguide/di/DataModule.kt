package com.adammcneilly.androidstudyguide.di

import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.data.local.ArticleDatabase
import com.adammcneilly.androidstudyguide.data.local.BookmarkedArticlesService
import com.adammcneilly.androidstudyguide.data.remote.androidessence.AndroidEssenceArticleService
import com.adammcneilly.androidstudyguide.data.remote.androidessence.AndroidEssenceRetrofitAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * This is a Hilt [Module] that defines how to create instances of any data related dependencies
 * in the application.
 *
 * @see ThirdPartyModule
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
object DataModule {

    @AndroidEssenceArticles
    @Provides
    fun provideAndroidEssenceArticles(
        api: AndroidEssenceRetrofitAPI,
        database: ArticleDatabase
    ): ArticleRepository {
        return AndroidEssenceArticleService(api, database)
    }

    @BookmarkedArticles
    @Provides
    fun provideBookmarkedArticles(
        database: ArticleDatabase
    ): ArticleRepository {
        return BookmarkedArticlesService(database)
    }
}
