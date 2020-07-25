package com.adammcneilly.androidstudyguide.data.remote.androidessence

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
}
