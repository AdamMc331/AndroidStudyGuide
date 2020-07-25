package com.adammcneilly.androidstudyguide.data.remote.androidessence

import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.util.HtmlString

class AndroidEssenceArticleService(
    private val api: AndroidEssenceRetrofitAPI
) : ArticleRepository {

    override suspend fun fetchArticles(): List<Article> {
        return api.getFeed().items?.map(AndroidEssenceFeedItem::toArticle).orEmpty()
    }
}

/**
 * TODO: If any of these networking values are null, we should throw an error so we're aware.
 *  but we've decided not to crash, so we don't ruin the UX.
 */
private fun AndroidEssenceFeedItem.toArticle(): Article {
    return Article(
        htmlTitle = HtmlString(this.title.orEmpty()),
        authorName = this.author?.name.orEmpty(),
        url = this.link?.href.orEmpty()
    )
}
