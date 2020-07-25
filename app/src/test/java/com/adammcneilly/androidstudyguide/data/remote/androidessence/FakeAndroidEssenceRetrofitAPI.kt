package com.adammcneilly.androidstudyguide.data.remote.androidessence

class FakeAndroidEssenceRetrofitAPI : AndroidEssenceRetrofitAPI {
    private lateinit var mockedFeed: AndroidEssenceFeed

    override suspend fun getFeed(): AndroidEssenceFeed {
        return mockedFeed
    }

    fun setMockedFeed(feed: AndroidEssenceFeed) {
        this.mockedFeed = feed
    }
}
