package com.adammcneilly.androidstudyguide.data.local

import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.data.DataResponse
import com.adammcneilly.androidstudyguide.models.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * This is an implementation of [ArticleRepository] that will only return the articles that the
 * user has bookmarked.
 *
 * This service pulls the bookmarks stored locally in the [database].
 */
class BookmarkedArticlesService(
    private val database: ArticleDatabase
) : ArticleRepository {
    override fun fetchArticles(): Flow<DataResponse<List<Article>>> {
        return database.fetchBookmarks()
            .map { persistableArticles ->
                val articles = persistableArticles.map(PersistableArticle::toArticle)
                DataResponse.Success(articles)
            }
            .catch { error ->
                DataResponse.Error(error)
            }
    }

    override suspend fun persistArticle(article: Article) {
        database.insertArticle(article.toPersistableArticle())
    }
}
