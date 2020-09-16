package com.adammcneilly.androidstudyguide.data

import com.adammcneilly.androidstudyguide.models.Article
import kotlinx.coroutines.flow.Flow

/**
 * This is a data source for any number of [Article] entities that should be shown to the user.
 */
interface ArticleRepository {
    /**
     * This will make an asynchronous request for articles, returning a [DataResponse] that encapsulates
     * both the success and failure scenarios.
     */
    fun fetchArticles(): Flow<DataResponse<List<Article>>>

    /**
     * This will take the supplied [article] and persist it on the device.
     */
    suspend fun persistArticle(article: Article)
}
