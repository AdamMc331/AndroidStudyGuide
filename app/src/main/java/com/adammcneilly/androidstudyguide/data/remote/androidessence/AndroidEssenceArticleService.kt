package com.adammcneilly.androidstudyguide.data.remote.androidessence

import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.data.DataResponse
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.util.HtmlString
import javax.inject.Inject

/**
 * This networking service will request [Article] entities from the Android Essence RSS Feed.
 *
 * @property[api] The retrofit instance that will make our networking requests.
 */
class AndroidEssenceArticleService @Inject constructor(
    private val api: AndroidEssenceRetrofitAPI
) : ArticleRepository {

    override suspend fun fetchArticles(): DataResponse<List<Article>> {
        return try {
            val articles = api.getFeed().items?.map(AndroidEssenceFeedItem::toArticle).orEmpty()
            DataResponse.Success(articles)
        } catch (e: Throwable) {
            DataResponse.Error(e)
        }
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
