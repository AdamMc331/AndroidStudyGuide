package com.adammcneilly.androidstudyguide.articlelist

import com.adammcneilly.androidstudyguide.fakes.FakeAnalyticsTracker
import com.adammcneilly.androidstudyguide.fakes.FakeArticleRepository
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.testObserver
import com.google.common.truth.Truth.assertThat

class BaseArticleListViewModelRobot {
    private lateinit var viewModel: BaseArticleListViewModel
    private val fakeRepository = FakeArticleRepository()
    private val fakeAnalyticsTracker = FakeAnalyticsTracker()

    suspend fun emitArticles(articles: List<Article>) = apply {
        fakeRepository.emitArticles(articles)
    }

    suspend fun emitFailure(error: Throwable) = apply {
        fakeRepository.emitFailure(error)
    }

    fun buildViewModel() = apply {
        viewModel = object : BaseArticleListViewModel(
            articleRepository = fakeRepository,
            analyticsTracker = fakeAnalyticsTracker,
        ) {
            /**
             * Supplying default value since it's not needed for these tests.
             */
            override val emptyStateMessageTextRes: Int
                get() = 0
        }
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
