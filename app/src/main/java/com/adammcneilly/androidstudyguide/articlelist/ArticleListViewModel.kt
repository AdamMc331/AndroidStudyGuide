package com.adammcneilly.androidstudyguide.articlelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.data.DataResponse
import kotlinx.coroutines.launch

class ArticleListViewModel(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    private val _state: MutableLiveData<ArticleListViewState> = MutableLiveData()
    val state: LiveData<ArticleListViewState> = _state

    init {
        fetchArticlesFromRepository()
    }

    fun retryClicked() {
        fetchArticlesFromRepository()
    }

    private fun fetchArticlesFromRepository() {
        viewModelScope.launch {
            _state.value = ArticleListViewState.Loading

            val response = articleRepository.fetchArticles()

            _state.value = when (response) {
                is DataResponse.Success -> ArticleListViewState.Success(response.data)
                is DataResponse.Error -> ArticleListViewState.Error(response.error)
            }
        }
    }
}
