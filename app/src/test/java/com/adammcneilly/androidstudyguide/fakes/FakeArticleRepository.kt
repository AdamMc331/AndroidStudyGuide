package com.adammcneilly.androidstudyguide.fakes

import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.data.DataResponse
import com.adammcneilly.androidstudyguide.models.Article
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow

class FakeArticleRepository : ArticleRepository {
    private var fetchArticlesCallCount = 0
    private val persistedArticles: MutableList<Article> = mutableListOf()

    private val articleListChannel: Channel<DataResponse<List<Article>>> = Channel()

    override fun fetchArticles(): Flow<DataResponse<List<Article>>> {
        fetchArticlesCallCount++

        return articleListChannel.consumeAsFlow()
    }

    override suspend fun persistArticle(article: Article) {
        persistedArticles.add(article)
    }

    suspend fun emitArticles(articles: List<Article>) {
        val response = DataResponse.Success(articles)
        articleListChannel.send(response)
    }

    suspend fun emitFailure(error: Throwable) {
        val response = DataResponse.Error(error)
        articleListChannel.send(response)
    }

    fun getFetchedArticlesCallCount(): Int {
        return fetchArticlesCallCount
    }

    fun getPersistedArticles(): List<Article> {
        return persistedArticles.toList()
    }

    fun closeChannels() {
        articleListChannel.close()
    }
}
