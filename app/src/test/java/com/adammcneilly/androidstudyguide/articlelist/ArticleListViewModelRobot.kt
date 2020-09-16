package com.adammcneilly.androidstudyguide.articlelist

import com.adammcneilly.androidstudyguide.fakes.FakeArticleRepository
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.testObserver
import com.google.common.truth.Truth.assertThat

class ArticleListViewModelRobot {
    private lateinit var viewModel: ArticleListViewModel
    private val fakeRepository = FakeArticleRepository()

    suspend fun emitArticles(articles: List<Article>) = apply {
        fakeRepository.emitArticles(articles)
    }

    suspend fun emitFailure(error: Throwable) = apply {
        fakeRepository.emitFailure(error)
    }

    fun buildViewModel() = apply {
        viewModel = ArticleListViewModel(
            articleRepository = fakeRepository
        )
    }

    fun clickRetry() = apply {
        viewModel.retryClicked()
    }

    fun clickBookmark(article: Article) = apply {
        viewModel.bookmarkClicked(article)
    }

    fun assertViewState(expectedViewState: ArticleListViewState) = apply {
        val actualViewState = viewModel.state.testObserver().observedValue
        assertThat(actualViewState).isEqualTo(expectedViewState)
    }

    fun assertNumberOfCallsToFetchArticles(expectedCalls: Int) = apply {
        val actualCalls = fakeRepository.getFetchedArticlesCallCount()
        assertThat(actualCalls).isEqualTo(expectedCalls)
    }

    fun assertArticleWasPersisted(article: Article) = apply {
        val wasPersisted = fakeRepository.getPersistedArticles().contains(article)
        assertThat(wasPersisted).isTrue()
    }

    fun cleanUp() = apply {
        fakeRepository.closeChannels()
    }
}
