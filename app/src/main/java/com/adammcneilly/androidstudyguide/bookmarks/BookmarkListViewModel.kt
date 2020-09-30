package com.adammcneilly.androidstudyguide.bookmarks

import androidx.hilt.lifecycle.ViewModelInject
import com.adammcneilly.androidstudyguide.articlelist.BaseArticleListViewModel
import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.di.BookmarkedArticles

class BookmarkListViewModel @ViewModelInject constructor(
    @BookmarkedArticles
    private val articleRepository: ArticleRepository
) : BaseArticleListViewModel(articleRepository)
