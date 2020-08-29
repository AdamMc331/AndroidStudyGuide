package com.adammcneilly.androidstudyguide.data.remote.androidessence

import com.adammcneilly.androidstudyguide.data.local.PersistableArticle
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.util.HtmlString
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class AndroidEssenceArticleServiceTest {

    private val testRobot = AndroidEssenceArticleServiceTestRobot()

    @Test
    fun fetchValidAccountList() = runBlockingTest {
        val mockFeed = AndroidEssenceFeed(
            items = listOf(
                AndroidEssenceFeedItem(
                    title = "Test Title",
                    author = AndroidEssenceAuthor(
                        name = "Adam McNeilly"
                    ),
                    link = AndroidEssenceLink(
                        href = "Test URL"
                    )
                )
            )
        )

        val expectedArticles = listOf(
            Article(
                htmlTitle = HtmlString("Test Title"),
                authorName = "Adam McNeilly",
                url = "Test URL"
            )
        )

        testRobot
            .mockFeed(mockFeed)
            .buildService()
            .assertFetchedArticles(expectedArticles)
    }

    @Test
    fun fetchArticlesWithOneBookmarked() = runBlockingTest {
        val testUrl = "Test URL"

        val mockFeed = AndroidEssenceFeed(
            items = listOf(
                AndroidEssenceFeedItem(
                    title = "Test Title",
                    author = AndroidEssenceAuthor(
                        name = "Adam McNeilly"
                    ),
                    link = AndroidEssenceLink(
                        href = testUrl
                    )
                )
            )
        )

        val mockBookmarks = listOf(
            PersistableArticle(
                url = testUrl,
                bookmarked = true
            )
        )

        val expectedArticles = listOf(
            Article(
                htmlTitle = HtmlString("Test Title"),
                authorName = "Adam McNeilly",
                url = testUrl,
                bookmarked = true
            )
        )

        testRobot
            .mockFeed(mockFeed)
            .mockBookmarks(mockBookmarks)
            .buildService()
            .assertFetchedArticles(expectedArticles)
    }

    @Test
    fun persistArticleShouldCallDatabaseToInsert() = runBlockingTest {
        testRobot
            .buildService()
            .persistArticle(Article())
            .assertInsertArticleToDBCallCount(expectedCount = 1)
    }
}
