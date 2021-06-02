package com.adammcneilly.androidstudyguide.articlelist

import com.adammcneilly.androidstudyguide.R
import com.adammcneilly.androidstudyguide.analytics.AnalyticsTracker
import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.di.AndroidEssenceArticles
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidEssenceArticleListViewModel @Inject constructor(
    @AndroidEssenceArticles
    private val articleRepository: ArticleRepository,
    analyticsTracker: AnalyticsTracker,
) : BaseArticleListViewModel(articleRepository, analyticsTracker) {

    override val emptyStateMessageTextRes: Int
        get() = R.string.android_essence_empty_message
}
