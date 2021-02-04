package com.adammcneilly.androidstudyguide.articlelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.data.DataResponse
import com.adammcneilly.androidstudyguide.models.Article
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * This class is responsible for requesting articles from the [articleRepository] and mapping
 * those requests into an [ArticleListViewState] which is then exposed through our [state] LiveData.
 */
open class BaseArticleListViewModel(
    private val articleRepository: ArticleRepository
) : ViewModel() {
    private val _state: MutableLiveData<ArticleListViewState> = MutableLiveData()
    val state: LiveData<ArticleListViewState> = _state

    init {
        fetchArticlesFromRepository()
    }

    /**
     * Whenever an error occurs, the user will be able to click a retry button, which will re-run the
     * request for articles.
     */
    fun retryClicked() {
        fetchArticlesFromRepository()
    }

    /**
     * Whenever the user clicks the bookmark button for a specific [article], we'll toggle
     * the bookmarked state and persist it locally.
     */
    fun bookmarkClicked(article: Article) {
        val updatedArticle = article.copy(
            bookmarked = !article.bookmarked
        )

        viewModelScope.launch {
            articleRepository.persistArticle(updatedArticle)
        }
    }

    /**
     * Requests the articles from our [articleRepository] and maps that response into a [ArticleListViewState].
     */
    private fun fetchArticlesFromRepository() {
        viewModelScope.launch {
            _state.value = ArticleListViewState.Loading

            articleRepository.fetchArticles().collect { response ->
                _state.value = when (response) {
                    is DataResponse.Success -> {
                        handleSuccessfulNetworkResponse(response)
                    }
                    is DataResponse.Error -> ArticleListViewState.Error(response.error)
                }
            }
        }
    }

    private fun handleSuccessfulNetworkResponse(response: DataResponse.Success<List<Article>>): ArticleListViewState {
        val articleList = response.data

        return if (articleList.isEmpty()) {
            ArticleListViewState.Empty
        } else {
            ArticleListViewState.Success(response.data)
        }
    }
}
