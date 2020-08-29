package com.adammcneilly.androidstudyguide.data.remote.androidessence

import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.data.DataResponse
import com.adammcneilly.androidstudyguide.data.local.ArticleDatabase
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.util.HtmlString
import javax.inject.Inject

/**
 * This networking service will request [Article] entities from the Android Essence RSS Feed.
 *
 * @property[api] The retrofit instance that will make our networking requests.
 */
class AndroidEssenceArticleService @Inject constructor(
    private val api: AndroidEssenceRetrofitAPI,
    private val database: ArticleDatabase
) : ArticleRepository {

    override suspend fun fetchArticles(): DataResponse<List<Article>> {
        return try {
            val articles = api.getFeed().items?.map(AndroidEssenceFeedItem::toArticle).orEmpty()
            val bookmarks = database.fetchBookmarks()

            // This is not efficient as it has two nested loops, see if we can improve this.
            val updatedBookmarks = articles.map { article ->
                val isBookmarked = bookmarks.any {
                    it.url == article.url
                }

                article.copy(
                    bookmarked = isBookmarked
                )
            }

            DataResponse.Success(updatedBookmarks)
        } catch (e: Throwable) {
            DataResponse.Error(e)
        }
    }
}

/**
 * TODO: If any of these networking values are null, we should throw an error so we're aware.
 *  but we've decided not to crash, so we don't ruin the UX.
 *  https://github.com/AdamMc331/AndroidStudyGuide/issues/28
 */
private fun AndroidEssenceFeedItem.toArticle(): Article {
    return Article(
        htmlTitle = HtmlString(this.title.orEmpty()),
        authorName = this.author?.name.orEmpty(),
        url = this.link?.href.orEmpty()
    )
}
