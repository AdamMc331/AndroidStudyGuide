package com.adammcneilly.androidstudyguide.di

import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.data.remote.androidessence.AndroidEssenceArticleService
import com.adammcneilly.androidstudyguide.data.remote.androidessence.AndroidEssenceRetrofitAPI
import org.koin.dsl.module

val dataModule = module {
    single {
        AndroidEssenceRetrofitAPI.getDefaultApi()
    }

    single<ArticleRepository> {
        AndroidEssenceArticleService(
            api = get()
        )
    }
}
