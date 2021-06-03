package com.adammcneilly.androidstudyguide.analytics

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

object BookmarkScreenViewedEvent : AnalyticsEvent {
    override val eventName: String
        get() = "viewed_bookmarks"

    override val properties: Map<String, Any>
        get() = emptyMap()
}
