package com.adammcneilly.androidstudyguide.articlelist

import com.adammcneilly.androidstudyguide.fakes.FakeArticleRepository
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.testObserver
import com.google.common.truth.Truth.assertThat

class ArticleListViewModelRobot {
    private lateinit var viewModel: ArticleListViewModel
    private val fakeRepository = FakeArticleRepository()

    fun mockArticles(articles: List<Article>) = apply {
        fakeRepository.setMockedArticles(articles)
    }

    fun buildViewModel() = apply {
        viewModel = ArticleListViewModel(
            articleRepository = fakeRepository
        )
    }

    fun assertViewState(expectedViewState: ArticleListViewState) = apply {
        val actualViewState = viewModel.state.testObserver().observedValue
        assertThat(actualViewState).isEqualTo(expectedViewState)
    }
}
