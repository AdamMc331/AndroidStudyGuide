package com.adammcneilly.androidstudyguide.data

import com.adammcneilly.androidstudyguide.models.Article

class InMemoryArticleService : ArticleRepository {
    override fun fetchArticles(): List<Article> {
        return listOf(
            Article(
                title = "Article One",
                authorName = "Adam McNeilly",
                url = ""
            ),
            Article(
                title = "Article Two",
                authorName = "Adam McNeilly",
                url = ""
            ),
            Article(
                title = "Article Three",
                authorName = "Jane Doe",
                url = ""
            ),
            Article(
                title = "Article Four",
                authorName = "John Smith",
                url = ""
            )
        )
    }
}
