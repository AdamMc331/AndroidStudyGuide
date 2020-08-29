package com.adammcneilly.androidstudyguide.articlelist

import android.content.res.Resources
import android.text.Spanned
import com.adammcneilly.androidstudyguide.R
import com.adammcneilly.androidstudyguide.models.Article

class ArticleListItemViewModel {
    var article: Article? = null

    val bookmarkButtonRes: Int
        get() = if (article?.bookmarked == true) {
            R.drawable.ic_bookmark_selected
        } else {
            R.drawable.ic_bookmark_unselected
        }

    val articleTitle: Spanned?
        get() = article?.htmlTitle?.getSpanned()

    fun getAuthorText(resources: Resources): String {
        return resources.getString(
            R.string.by_author,
            article?.authorName
        )
    }
}
