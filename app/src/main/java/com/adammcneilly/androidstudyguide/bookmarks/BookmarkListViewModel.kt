package com.adammcneilly.androidstudyguide.bookmarks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.androidstudyguide.articlelist.ArticleListViewState
import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.data.DataResponse
import com.adammcneilly.androidstudyguide.models.Article
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * This class is responsible for requesting articles from the [articleRepository] and mapping
 * those requests into an [ArticleListViewState] which is then exposed through our [state] LiveData.
 */
class BookmarkListViewModel @ViewModelInject constructor(
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

    fun bookmarkClicked(article: Article) {
        val updatedArticle = article.copy(
            bookmarked = !article.bookmarked
        )

        viewModelScope.launch {
            articleRepository.persistArticle(updatedArticle)
        }
    }

    private fun fetchArticlesFromRepository() {
        viewModelScope.launch {
            _state.value = ArticleListViewState.Loading

            articleRepository.fetchArticles().collect { response ->
                _state.value = when (response) {
                    is DataResponse.Success -> ArticleListViewState.Success(response.data)
                    is DataResponse.Error -> ArticleListViewState.Error(response.error)
                }
            }
        }
    }
}
