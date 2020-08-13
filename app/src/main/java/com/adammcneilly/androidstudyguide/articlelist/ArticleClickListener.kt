package com.adammcneilly.androidstudyguide.articlelist

import com.adammcneilly.androidstudyguide.models.Article

/**
 * This interface defines any click events that should be handled for an [Article] list item.
 */
interface ArticleClickListener {
    fun onArticleClicked(article: Article)
}
