package com.adammcneilly.androidstudyguide.compose

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
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
}
