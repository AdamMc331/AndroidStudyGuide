package com.adammcneilly.androidstudyguide.articlelist

import com.adammcneilly.androidstudyguide.models.Article

sealed class ArticleListViewState {
    object Loading : ArticleListViewState()
    data class Success(val articles: List<Article>) : ArticleListViewState()
    data class Error(val error: Throwable) : ArticleListViewState()
}
