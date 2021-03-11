package com.adammcneilly.androidstudyguide.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.util.HtmlString
import org.junit.Rule
import org.junit.Test

class ArticleListItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun displayBookmarkedArticleWithNoTags() {
        val title = "My Title"

        val article = Article(
            htmlTitle = HtmlString(title),
            authorName = "Adam McNeilly",
            bookmarked = true,
            tags = emptyList(),
        )

        composeTestRule.setContent {
            ArticleListItem(
                article = article,
                onBookmarkClicked = { /*TODO*/ },
                onArticleClicked = { /*TODO*/ }
            )
        }

        composeTestRule.onNodeWithText(title, useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithText("By Adam McNeilly", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Bookmarked").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Article Tags").assertDoesNotExist()
    }

    @Test
    fun displayNonBookmarkedArticleWithTags() {
        val title = "My Title"

        val article = Article(
            htmlTitle = HtmlString(title),
            authorName = "Adam McNeilly",
            bookmarked = false,
            tags = listOf("Jetpack", "Compose"),
        )

        composeTestRule.setContent {
            ArticleListItem(
                article = article,
                onBookmarkClicked = { /*TODO*/ },
                onArticleClicked = { /*TODO*/ }
            )
        }

        composeTestRule.onNodeWithText(title, useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithText("By Adam McNeilly", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Not Bookmarked").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Article Tags").assertIsDisplayed()

        composeTestRule.onNodeWithText("Jetpack", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithText("Compose", useUnmergedTree = true).assertIsDisplayed()
    }
}
