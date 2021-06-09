package com.adammcneilly.androidstudyguide.compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.util.HtmlString
import org.junit.Rule
import org.junit.Test

class ArticleListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun displayArticleList() {
        val articles = (0..10).map { index ->
            Article(htmlTitle = HtmlString("Article Number: $index"))
        }

        composeTestRule.setContent {
            ArticleList(
                articles = articles,
                onBookmarkClicked = { /*TODO*/ },
                onArticleClicked = { /*TODO*/ }
            )
        }

        composeTestRule.onRoot().printToLog("ArticleListItemTest")

        composeTestRule.onNodeWithText("Article Number: 0", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithText("Article Number: 1", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithText("Article Number: 2", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithText("Article Number: 3", useUnmergedTree = true).assertExists()
//        composeTestRule.onNodeWithText("Article Number: 4", useUnmergedTree = true).assertExists()
//        composeTestRule.onNodeWithText("Article Number: 5", useUnmergedTree = true).assertExists()
//        composeTestRule.onNodeWithText("Article Number: 6", useUnmergedTree = true).assertExists()
//        composeTestRule.onNodeWithText("Article Number: 7", useUnmergedTree = true).assertExists()
//        composeTestRule.onNodeWithText("Article Number: 8", useUnmergedTree = true).assertExists()
//        composeTestRule.onNodeWithText("Article Number: 9", useUnmergedTree = true).assertExists()
    }

    @Test
    fun clickingBookmarkIconUpdatesUI() {
        // Start off with a test article that is not
        // bookmarked by the user.
        val testArticle = Article(
            htmlTitle = HtmlString("Test Article"),
            bookmarked = false,
        )

        composeTestRule.setContent {
            // Maintain a local article list, that can be updated
            // on click, so the UI should re-render.
            var articleList: List<Article> by remember {
                mutableStateOf(listOf(testArticle))
            }

            ArticleList(
                articles = articleList,
                // When a bookmark icon is clicked, just map the entire list
                // to say the items are bookmarked.
                // Since this test only has one article, this is fine.
                onBookmarkClicked = {
                    articleList = articleList.map {
                        it.copy(bookmarked = true)
                    }
                },
                onArticleClicked = { /*TODO*/ },
            )
        }

        // First, verify that a icon for a not-bookmarked article exists.
        composeTestRule.onNodeWithContentDescription("Not Bookmarked").assertExists()

        // Click on the not-bookmarked article.
        composeTestRule.onNodeWithContentDescription("Not Bookmarked").performClick()

        // Verify that an icon for a bookmarked article exists.
        composeTestRule.onNodeWithContentDescription("Bookmarked").assertExists()
    }
}
