package com.adammcneilly.androidstudyguide.di

import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.data.remote.androidessence.AndroidEssenceArticleService
import dagger.Binds
import dagger.Module
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
abstract class DataModule {

    @Binds
    abstract fun bindArticleRepository(
        androidEssenceArticleService: AndroidEssenceArticleService
    ): ArticleRepository
}
