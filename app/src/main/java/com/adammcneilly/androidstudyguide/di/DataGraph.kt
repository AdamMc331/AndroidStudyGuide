package com.adammcneilly.androidstudyguide.di

import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.data.remote.androidessence.AndroidEssenceArticleService
import com.adammcneilly.androidstudyguide.data.remote.androidessence.AndroidEssenceRetrofitAPI

/**
 * Dependency graph to expose all of our dependencies related to requesting data for the application.
 */
interface DataGraph {
    val articleRepository: ArticleRepository
}

class NetworkDataGraph : DataGraph {
    override val articleRepository: ArticleRepository by lazy {
        AndroidEssenceArticleService(
            api = AndroidEssenceRetrofitAPI.getDefaultApi()
        )
    }
}
