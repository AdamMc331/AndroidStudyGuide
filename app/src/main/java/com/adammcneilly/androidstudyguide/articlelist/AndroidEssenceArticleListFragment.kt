package com.adammcneilly.androidstudyguide.articlelist

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

/**
 * This page is responsible for displaying a list of articles to the user. The user should be able
 * to interact with these articles by clicking on them, which will navigate away from our application
 * and to a web browser for reading the articles.
 */
@AndroidEntryPoint
class AndroidEssenceArticleListFragment : BaseArticleListFragment() {
    override val viewModel: AndroidEssenceArticleListViewModel by viewModels()
}
