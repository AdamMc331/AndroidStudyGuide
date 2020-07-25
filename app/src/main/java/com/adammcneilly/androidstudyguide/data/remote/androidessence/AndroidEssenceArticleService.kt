package com.adammcneilly.androidstudyguide.data.remote.androidessence

import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.models.Article

class AndroidEssenceArticleService(
    private val api: AndroidEssenceRetrofitAPI
) : ArticleRepository {

    override suspend fun fetchArticles(): List<Article> {
        return api.getFeed().items.map(AndroidEssenceFeedItem::toArticle)
    }
}

private fun AndroidEssenceFeedItem.toArticle(): Article {
    return Article(
        title = this.title,
        authorName = this.author.name,
        url = this.url
    )
}
