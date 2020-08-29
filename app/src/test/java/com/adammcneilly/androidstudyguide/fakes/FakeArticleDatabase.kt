package com.adammcneilly.androidstudyguide.fakes

import com.adammcneilly.androidstudyguide.data.local.ArticleDatabase
import com.adammcneilly.androidstudyguide.data.local.PersistableArticle

class FakeArticleDatabase : ArticleDatabase {
    private var mockedBookmarks: List<PersistableArticle> = emptyList()

    override suspend fun fetchBookmarks(): List<PersistableArticle> {
        return mockedBookmarks
    }

    override suspend fun insertArticle(article: PersistableArticle) {
        // NoOP
    }

    fun setMockedBookmarks(bookmarks: List<PersistableArticle>) {
        this.mockedBookmarks = bookmarks
    }
}
