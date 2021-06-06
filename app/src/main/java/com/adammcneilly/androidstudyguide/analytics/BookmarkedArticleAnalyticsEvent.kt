package com.adammcneilly.androidstudyguide.analytics

/**
 * This analytics event will be fired every time a user bookmarks an article.
 *
 * @property[articleTitle] The title identifying the article that is bookmarked.
 * @property[isBookmarked] True if the user is bookmarking the article, false if they are removing
 * the bookmark.
 */
data class BookmarkedArticleAnalyticsEvent(
    private val articleTitle: String,
    private val isBookmarked: Boolean
) : AnalyticsEvent {

    override val eventName: String
        get() = "bookmarked_article"

    override val properties: Map<String, Any>
        get() = mapOf(
            "article_title" to articleTitle,
            "is_bookmarked" to isBookmarked,
        )
}
