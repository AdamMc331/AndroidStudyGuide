package com.adammcneilly.androidstudyguide.articlelist

import com.adammcneilly.androidstudyguide.models.Article

/**
 * This sealed class defines each possible state the user can be in when articles are being requested.
 */
sealed class ArticleListViewState {
    object Loading : ArticleListViewState()
    data class Success(val articles: List<Article>) : ArticleListViewState()
    data class Error(val error: Throwable) : ArticleListViewState()
}
