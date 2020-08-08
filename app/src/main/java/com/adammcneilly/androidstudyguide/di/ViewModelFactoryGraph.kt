package com.adammcneilly.androidstudyguide.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adammcneilly.androidstudyguide.articlelist.ArticleListViewModel
import com.adammcneilly.androidstudyguide.dataGraph

interface ViewModelFactoryGraph {
    fun getArticleListViewModelFactory(): ViewModelProvider.Factory
}

@Suppress("UNCHECKED_CAST")
class BaseViewModelFactoryGraph(
    private val dataGraph: DataGraph
) : ViewModelFactoryGraph {
    override fun getArticleListViewModelFactory(): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ArticleListViewModel(
                    articleRepository = dataGraph.articleRepository
                ) as T
            }
        }
    }
}
