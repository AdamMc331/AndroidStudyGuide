package com.adammcneilly.androidstudyguide.articlelist

import com.adammcneilly.androidstudyguide.models.Article

data class ArticleListViewState(
    val showLoading: Boolean,
    val showArticles: Boolean,
    val showError: Boolean,
    val articles: List<Article> = emptyList()
) {
    companion object {
        fun loading(): ArticleListViewState {
            return ArticleListViewState(
                showLoading = true,
                showArticles = false,
                showError = false
            )
        }

        fun success(articles: List<Article>): ArticleListViewState {
            return ArticleListViewState(
                showLoading = false,
                showArticles = true,
                showError = false,
                articles = articles
            )
        }

        fun error(): ArticleListViewState {
            return ArticleListViewState(
                showLoading = false,
                showArticles = false,
                showError = true
            )
        }
    }
}
