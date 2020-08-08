package com.adammcneilly.androidstudyguide.di

import com.adammcneilly.androidstudyguide.articlelist.ArticleListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ArticleListViewModel(
            articleRepository = get()
        )
    }
}
