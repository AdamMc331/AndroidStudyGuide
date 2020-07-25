package com.adammcneilly.androidstudyguide.data.remote.androidessence

import com.adammcneilly.androidstudyguide.models.Article
import com.google.common.truth.Truth.assertThat

class AndroidEssenceArticleServiceTestRobot {
    private val fakeAPI = FakeAndroidEssenceRetrofitAPI()
    private lateinit var service: AndroidEssenceArticleService

    fun buildService() = apply {
        this.service = AndroidEssenceArticleService(
            api = fakeAPI
        )
    }

    fun mockFeed(feed: AndroidEssenceFeed) = apply {
        fakeAPI.setMockedFeed(feed)
    }

    suspend fun assertFetchedArticles(expectedArticles: List<Article>) = apply {
        val actualArticles = service.fetchArticles()
        assertThat(actualArticles).isEqualTo(expectedArticles)
    }
}
