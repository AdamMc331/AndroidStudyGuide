package com.adammcneilly.androidstudyguide.data

import com.adammcneilly.androidstudyguide.models.Article

/**
 * Represents any source of articles to be displayed.
 */
interface ArticleRepository {
    fun fetchArticles(): List<Article>
}
