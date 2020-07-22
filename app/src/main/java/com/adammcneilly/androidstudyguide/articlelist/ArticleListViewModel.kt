package com.adammcneilly.androidstudyguide.articlelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.models.Article

class ArticleListViewModel(
    articleRepository: ArticleRepository
) : ViewModel() {

    private val _articles: MutableLiveData<List<Article>> = MutableLiveData()
    val articles: LiveData<List<Article>> = _articles

    init {
        val fetchedArticles = articleRepository.fetchArticles()
        _articles.value = fetchedArticles
    }
}
