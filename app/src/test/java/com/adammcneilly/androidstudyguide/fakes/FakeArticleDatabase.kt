package com.adammcneilly.androidstudyguide.fakes

import com.adammcneilly.androidstudyguide.data.local.ArticleDatabase
import com.adammcneilly.androidstudyguide.data.local.PersistableArticle

class FakeArticleDatabase : ArticleDatabase {
    override suspend fun fetchBookmarks(): List<PersistableArticle> {
        return emptyList()
    }

    override suspend fun insertArticle(article: PersistableArticle) {
        // NoOP
    }
}
