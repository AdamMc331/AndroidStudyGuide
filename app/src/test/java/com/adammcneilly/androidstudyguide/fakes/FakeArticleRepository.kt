package com.adammcneilly.androidstudyguide.fakes

import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.models.Article
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FakeArticleRepository : ArticleRepository {
    private var fetchArticlesCallCount = 0

    private lateinit var articleListContinuation: Continuation<List<Article>>

    override suspend fun fetchArticles(): List<Article> {
        fetchArticlesCallCount++

        return suspendCoroutine { continuation ->
            articleListContinuation = continuation
        }
    }

    fun emitArticles(articles: List<Article>) {
        articleListContinuation.resume(articles)
    }

    fun getFetchedArticlesCallCount(): Int {
        return fetchArticlesCallCount
    }
}
