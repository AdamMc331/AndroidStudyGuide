package com.adammcneilly.androidstudyguide.articlelist

import androidx.hilt.lifecycle.ViewModelInject
import com.adammcneilly.androidstudyguide.R
import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.di.AndroidEssenceArticles

class AndroidEssenceArticleListViewModel @ViewModelInject constructor(
    @AndroidEssenceArticles
    private val articleRepository: ArticleRepository
) : BaseArticleListViewModel(articleRepository) {

    override val emptyStateMessageTextRes: Int
        get() = R.string.android_essence_empty_message
}
