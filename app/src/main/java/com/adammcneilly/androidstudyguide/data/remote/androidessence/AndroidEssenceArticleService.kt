package com.adammcneilly.androidstudyguide.data.remote.androidessence

import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.data.DataResponse
import com.adammcneilly.androidstudyguide.data.local.ArticleDatabase
import com.adammcneilly.androidstudyguide.data.local.toPersistableArticle
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.util.HtmlString
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * This networking service will request [Article] entities from the Android Essence RSS Feed.
 *
 * @property[api] The retrofit instance that will make our networking requests.
 */
class AndroidEssenceArticleService @Inject constructor(
    private val api: AndroidEssenceRetrofitAPI,
    private val database: ArticleDatabase
) : ArticleRepository {

    override fun fetchArticles(): Flow<DataResponse<List<Article>>> {
        val apiArticlesFlow = flow {
            val articles = api.getFeed().items?.map(AndroidEssenceFeedItem::toArticle).orEmpty()
            emit(articles)
        }

        val bookmarkedArticlesFlow = database.fetchBookmarks()

        return apiArticlesFlow.combine(bookmarkedArticlesFlow) { apiArticles, bookmarkArticles ->
            val updatedBookmarks = apiArticles.map { article ->
                val isBookmarked = bookmarkArticles.any {
                    it.url == article.url
                }

                article.copy(
                    bookmarked = isBookmarked
                )
            }

            DataResponse.Success(updatedBookmarks)
        }.catch { error ->
            DataResponse.Error(error)
        }
    }

    override suspend fun persistArticle(article: Article) {
        database.insertArticle(article.toPersistableArticle())
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
