package com.adammcneilly.androidstudyguide.di

import com.adammcneilly.androidstudyguide.data.remote.androidessence.AndroidEssenceRetrofitAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object RetrofitModule {
    @Provides
    fun provideAndroidEssenceRetrofitAPI(): AndroidEssenceRetrofitAPI {
        return AndroidEssenceRetrofitAPI.getDefaultApi()
    }
}
