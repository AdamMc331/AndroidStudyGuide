package com.adammcneilly.androidstudyguide.articlelist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.adammcneilly.androidstudyguide.CoroutinesTestRule
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.util.HtmlString
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class BaseArticleListViewModelTest {

    private val testRobot = BaseArticleListViewModelRobot()

    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @JvmField
    @Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @After
    fun tearDown() {
        testRobot.cleanUp()
    }

    @Test
    fun successfulRequest() = runBlockingTest {
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
    fun emptyArticleListRequest() = runBlockingTest {
        val emptyArticles: List<Article> = emptyList()

        testRobot
            .buildViewModel()
            .assertViewState(
                expectedViewState = ArticleListViewState.Loading
            )
            .emitArticles(emptyArticles)
            .assertViewState(
                expectedViewState = ArticleListViewState.Empty
            )
            .assertNumberOfCallsToFetchArticles(1)
    }

    @Test
    fun failureRequest() = runBlockingTest {
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
    fun retryFailedRequest() = runBlockingTest {
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
    fun clickingBookmarkShouldPersistArticle() = runBlockingTest {
        val unbookmarkedArticle = Article(
            htmlTitle = HtmlString("Test Title")
        )
        val bookmarkedArticle = unbookmarkedArticle.copy(bookmarked = true)

        val initialArticles = listOf(unbookmarkedArticle)

        testRobot
            .buildViewModel()
            .emitArticles(initialArticles)
            .assertViewState(
                ArticleListViewState.Success(initialArticles)
            )
            .clickBookmark(unbookmarkedArticle)
            .assertArticleWasPersisted(bookmarkedArticle)
    }
}
