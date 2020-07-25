package com.adammcneilly.androidstudyguide.models

import com.adammcneilly.androidstudyguide.util.HtmlString

/**
 * @property[htmlTitle] Some data sources for articles may include HTML characters, so we need the
 * [HtmlString] class to do the necessary decoding before displaying this on the UI. Any one who creates
 * an instance of an Article will need to encode their title first.
 */
data class Article(
    val htmlTitle: HtmlString,
    val authorName: String,
    val url: String
)
