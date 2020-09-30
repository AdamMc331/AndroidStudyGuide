package com.adammcneilly.androidstudyguide.bookmarks

import androidx.fragment.app.viewModels
import com.adammcneilly.androidstudyguide.articlelist.BaseArticleListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkListFragment : BaseArticleListFragment() {
    override val viewModel: BookmarkListViewModel by viewModels()
}
