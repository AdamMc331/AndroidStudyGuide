package com.adammcneilly.androidstudyguide.articlelist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.adammcneilly.androidstudyguide.CoroutinesTestRule
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.util.HtmlString
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArticleListViewModelTest {

    private val testRobot = ArticleListViewModelRobot()

    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @JvmField
    @Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun successfulRequest() {
        val testArticles = listOf(
            Article(htmlTitle = HtmlString("Test Title"))
        )

        testRobot
            .buildViewModel()
            .assertViewState(
                expectedViewState = ArticleListViewState.Loading
            )
            .emitArticles(testArticles)
            .assertViewState(
                expectedViewState = ArticleListViewState.Success(testArticles)
            )
            .assertNumberOfCallsToFetchArticles(1)
    }

    @Test
    fun failureRequest() {
        val networkError = Throwable("Network Error")

        testRobot
            .buildViewModel()
            .assertViewState(
                expectedViewState = ArticleListViewState.Loading
            )
            .emitFailure(networkError)
            .assertViewState(
                expectedViewState = ArticleListViewState.Error(networkError)
            )
            .assertNumberOfCallsToFetchArticles(1)
    }

    @Test
    fun retryFailedRequest() {
        val testArticles = listOf(
            Article(htmlTitle = HtmlString("Test Title"))
        )

        val networkError = Throwable("Network Error")

        testRobot
            .buildViewModel()
            .assertViewState(
                ArticleListViewState.Loading
            )
            .emitFailure(networkError)
            .assertViewState(
                ArticleListViewState.Error(networkError)
            )
            .clickRetry()
            .assertViewState(
                ArticleListViewState.Loading
            )
            .emitArticles(testArticles)
            .assertViewState(
                ArticleListViewState.Success(testArticles)
            )
            .assertNumberOfCallsToFetchArticles(2)
    }

    @Test
    fun bookmarkArticle() {
        val unbookmarkedArticle = Article(
            htmlTitle = HtmlString("Test Title")
        )
        val bookmarkedArticle = unbookmarkedArticle.copy(bookmarked = true)

        val initialArticles = listOf(unbookmarkedArticle)
        val updatedArticles = listOf(bookmarkedArticle)

        testRobot
            .buildViewModel()
            .emitArticles(initialArticles)
            .assertViewState(
                ArticleListViewState.Success(initialArticles)
            )
            .clickBookmark(unbookmarkedArticle)
            .assertViewState(
                ArticleListViewState.Success(updatedArticles)
            )
    }
}
