package com.adammcneilly.androidstudyguide.fakes

import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.data.DataResponse
import com.adammcneilly.androidstudyguide.models.Article
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FakeArticleRepository : ArticleRepository {
    private var fetchArticlesCallCount = 0

    private lateinit var articleListContinuation: Continuation<DataResponse<List<Article>>>

    override suspend fun fetchArticles(): DataResponse<List<Article>> {
        fetchArticlesCallCount++

        return suspendCoroutine { continuation ->
            articleListContinuation = continuation
        }
    }

    fun emitArticles(articles: List<Article>) {
        val response = DataResponse.Success(articles)
        articleListContinuation.resume(response)
    }

    fun emitFailure(error: Throwable) {
        val response = DataResponse.Error<List<Article>>(error)
        articleListContinuation.resume(response)
    }

    fun getFetchedArticlesCallCount(): Int {
        return fetchArticlesCallCount
    }
}
