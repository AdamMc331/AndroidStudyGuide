package com.adammcneilly.androidstudyguide.models

import com.adammcneilly.androidstudyguide.util.HtmlString

/**
 * An article is a text document that provides helpful learning resources for the reader.
 *
 * @property[authorName] This is the name of the person who wrote the article. (Ex: Adam McNeilly)
 * @property[url] This is the web address for this article. (ex: https://androidessence.com/wtf)
 * @property[htmlTitle] Some data sources for articles may include HTML characters, so we need the
 * [HtmlString] class to do the necessary decoding before displaying this on the UI. Any one who creates
 * an instance of an Article will need to encode their title first.
 * @property[bookmarked] True if the user has bookmarked this article to read later, false otherwise.
 */
data class Article(
    val htmlTitle: HtmlString = HtmlString(""),
    val authorName: String = "",
    val url: String = "",
    val bookmarked: Boolean = false,
    val tags: List<String> = emptyList()
)
