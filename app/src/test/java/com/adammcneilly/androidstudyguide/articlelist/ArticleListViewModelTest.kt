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
}
