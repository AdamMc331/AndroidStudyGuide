package com.adammcneilly.androidstudyguide.fakes

import com.adammcneilly.androidstudyguide.data.local.ArticleDatabase
import com.adammcneilly.androidstudyguide.data.local.PersistableArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeArticleDatabase : ArticleDatabase {
    private var mockedBookmarks: List<PersistableArticle> = emptyList()
    private var insertArticleCallCount = 0

    override fun fetchBookmarks(): Flow<List<PersistableArticle>> {
        return flowOf(mockedBookmarks)
    }

    override suspend fun insertArticle(article: PersistableArticle) {
        insertArticleCallCount++
    }

    fun setMockedBookmarks(bookmarks: List<PersistableArticle>) {
        this.mockedBookmarks = bookmarks
    }

    fun getInsertArticleCallCount(): Int {
        return insertArticleCallCount
    }
}
