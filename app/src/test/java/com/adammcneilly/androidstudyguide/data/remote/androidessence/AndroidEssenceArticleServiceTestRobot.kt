package com.adammcneilly.androidstudyguide.data.remote.androidessence

import com.adammcneilly.androidstudyguide.data.DataResponse
import com.adammcneilly.androidstudyguide.data.local.PersistableArticle
import com.adammcneilly.androidstudyguide.fakes.FakeArticleDatabase
import com.adammcneilly.androidstudyguide.models.Article
import com.google.common.truth.Truth.assertThat

class AndroidEssenceArticleServiceTestRobot {
    private val fakeAPI = FakeAndroidEssenceRetrofitAPI()
    private val fakeDatabase = FakeArticleDatabase()
    private lateinit var service: AndroidEssenceArticleService

    fun buildService() = apply {
        this.service = AndroidEssenceArticleService(
            api = fakeAPI,
            database = fakeDatabase
        )
    }

    fun mockFeed(feed: AndroidEssenceFeed) = apply {
        fakeAPI.setMockedFeed(feed)
    }

    fun mockBookmarks(bookmarkedArticles: List<PersistableArticle>) = apply {
        fakeDatabase.setMockedBookmarks(bookmarkedArticles)
    }

    suspend fun assertFetchedArticles(expectedArticles: List<Article>) = apply {
        val response = service.fetchArticles()
        val actualArticles = (response as DataResponse.Success<List<Article>>).data

        assertThat(actualArticles).isEqualTo(expectedArticles)
    }

    suspend fun persistArticle(article: Article) = apply {
        service.persistArticle(article)
    }

    fun assertInsertArticleToDBCallCount(expectedCount: Int) = apply {
        assertThat(fakeDatabase.getInsertArticleCallCount()).isEqualTo(expectedCount)
    }
}
