package com.adammcneilly.androidstudyguide.articlelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.androidstudyguide.data.ArticleRepository
import kotlinx.coroutines.launch

class ArticleListViewModel(
    articleRepository: ArticleRepository
) : ViewModel() {

    private val _state: MutableLiveData<ArticleListViewState> = MutableLiveData()
    val state: LiveData<ArticleListViewState> = _state

    init {
        viewModelScope.launch {
            _state.value = ArticleListViewState.Loading

            val articles = articleRepository.fetchArticles()
            _state.value = ArticleListViewState.Success(articles)
        }
    }
}
