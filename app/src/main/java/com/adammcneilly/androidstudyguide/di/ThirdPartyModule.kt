package com.adammcneilly.androidstudyguide.di

import com.adammcneilly.androidstudyguide.data.remote.androidessence.AndroidEssenceRetrofitAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

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
}
