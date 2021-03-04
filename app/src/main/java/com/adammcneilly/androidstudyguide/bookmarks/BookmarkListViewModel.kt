package com.adammcneilly.androidstudyguide.bookmarks

import com.adammcneilly.androidstudyguide.R
import com.adammcneilly.androidstudyguide.articlelist.BaseArticleListViewModel
import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.di.BookmarkedArticles
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkListViewModel @Inject constructor(
    @BookmarkedArticles
    private val articleRepository: ArticleRepository
) : BaseArticleListViewModel(articleRepository) {

    override val emptyStateMessageTextRes: Int
        get() = R.string.bookmarks_empty_message
}
