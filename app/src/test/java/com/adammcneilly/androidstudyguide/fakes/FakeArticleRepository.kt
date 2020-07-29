package com.adammcneilly.androidstudyguide.fakes

import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.models.Article

class FakeArticleRepository : ArticleRepository {
    private var mockedArticles: List<Article> = emptyList()
    private var fetchArticlesCallCount = 0

    override suspend fun fetchArticles(): List<Article> {
        fetchArticlesCallCount++
        return mockedArticles
    }

    fun setMockedArticles(articles: List<Article>) {
        this.mockedArticles = articles
    }

    fun getFetchedArticlesCallCount(): Int {
        return fetchArticlesCallCount
    }
}
