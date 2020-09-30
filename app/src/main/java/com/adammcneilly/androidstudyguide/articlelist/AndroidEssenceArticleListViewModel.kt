package com.adammcneilly.androidstudyguide.articlelist

import androidx.hilt.lifecycle.ViewModelInject
import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.di.AndroidEssenceArticles

class AndroidEssenceArticleListViewModel @ViewModelInject constructor(
    @AndroidEssenceArticles
    private val articleRepository: ArticleRepository
) : BaseArticleListViewModel(articleRepository)
