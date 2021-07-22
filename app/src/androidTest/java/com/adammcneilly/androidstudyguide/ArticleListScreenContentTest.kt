package com.adammcneilly.androidstudyguide

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.adammcneilly.androidstudyguide.articlelist.ArticleListViewState
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.util.HtmlString
import org.junit.Rule
import org.junit.Test

class ArticleListScreenContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun createLoadedState() {
        val articleTitle = "Test Article"

        val testArticle = Article(
            htmlTitle = HtmlString(articleTitle)
        )

        val articleList = listOf(testArticle)

        val viewState = ArticleListViewState.Success(articleList)

        composeTestRule.setContent {
            ArticleListScreenContent(
                currentState = viewState,
                onBookmarkClicked = {},
                onArticleClicked = {},
            )
        }

        composeTestRule.onNodeWithText(articleTitle).assertIsDisplayed()
    }
}
