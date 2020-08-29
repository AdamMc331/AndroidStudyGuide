package com.adammcneilly.androidstudyguide.articlelist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.data.DataResponse
import com.adammcneilly.androidstudyguide.models.Article
import kotlinx.coroutines.launch

/**
 * This class is responsible for requesting articles from the [articleRepository] and mapping
 * those requests into an [ArticleListViewState] which is then exposed through our [state] LiveData.
 */
class ArticleListViewModel @ViewModelInject constructor(
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

        // NOTE: When we change the repository to return a flow of articles, we won't need
        // to do this.
        val currentArticles = (_state.value as? ArticleListViewState.Success)?.articles.orEmpty()
        val updatedArticles = currentArticles.map { item ->
            val isSameItem = item.htmlTitle == article.htmlTitle
            if (isSameItem) {
                item.copy(bookmarked = !item.bookmarked)
            } else {
                item
            }
        }
        _state.value = ArticleListViewState.Success(updatedArticles)
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
