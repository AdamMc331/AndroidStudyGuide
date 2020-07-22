package com.adammcneilly.androidstudyguide.data

import com.adammcneilly.androidstudyguide.models.Article

class InMemoryArticleService : ArticleRepository {
    override suspend fun fetchArticles(): List<Article> {
        return listOf(
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
