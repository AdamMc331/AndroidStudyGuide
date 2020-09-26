package com.adammcneilly.androidstudyguide.articlelist

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.data.DataResponse
import com.adammcneilly.androidstudyguide.di.AndroidEssenceArticles
import com.adammcneilly.androidstudyguide.di.BookmarkedArticles
import com.adammcneilly.androidstudyguide.models.Article
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * This class is responsible for requesting articles from the [androidEssenceArticleRepository] and mapping
 * those requests into an [ArticleListViewState] which is then exposed through our [state] LiveData.
 */
class ArticleListViewModel @ViewModelInject constructor(
    @AndroidEssenceArticles
    androidEssenceArticleRepository: ArticleRepository,
    @BookmarkedArticles
    bookmarksArticleRepository: ArticleRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val articleListType: ArticleListType? =
        savedStateHandle.get(ArticleListFragment.ARG_ARTICLE_LIST_TYPE)

    private val repository = when (articleListType) {
        ArticleListType.BOOKMARKS -> bookmarksArticleRepository
        else -> androidEssenceArticleRepository
    }

    private val _state: MutableLiveData<ArticleListViewState> = MutableLiveData()
    val state: LiveData<ArticleListViewState> = _state

    init {
        Log.d("ARM", "Creating ViewModel: $articleListType")
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
            repository.persistArticle(updatedArticle)
        }
    }

    private fun fetchArticlesFromRepository() {
        viewModelScope.launch {
            _state.value = ArticleListViewState.Loading

            repository.fetchArticles().collect { response ->
                _state.value = when (response) {
                    is DataResponse.Success -> ArticleListViewState.Success(response.data)
                    is DataResponse.Error -> ArticleListViewState.Error(response.error)
                }
            }
        }
    }
}
