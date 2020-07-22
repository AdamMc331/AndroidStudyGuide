package com.adammcneilly.androidstudyguide.data

import com.adammcneilly.androidstudyguide.models.Article
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InMemoryArticleService(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ArticleRepository {
    override suspend fun fetchArticles(): List<Article> {
        return withContext(ioDispatcher) {
            listOf(
                Article(
                    title = "Article One",
                    authorName = "Adam McNeilly",
                    url = "https://androidessence.com/mastering-room-database-migrations"
                ),
                Article(
                    title = "Article Two",
                    authorName = "Adam McNeilly",
                    url = "https://androidessence.com/mastering-room-database-migrations"
                ),
                Article(
                    title = "Article Three",
                    authorName = "Jane Doe",
                    url = "https://androidessence.com/room-relationship-recap"
                ),
                Article(
                    title = "Article Four",
                    authorName = "John Smith",
                    url = "https://androidessence.com/room-relationship-recap"
                )
            )
        }
    }
}
