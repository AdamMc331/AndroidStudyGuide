package com.adammcneilly.androidstudyguide.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomArticleDatabaseTest {
    private lateinit var appDatabase: RoomStudyGuideDatabase
    private lateinit var articleDatabase: RoomArticleDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(
            context,
            RoomStudyGuideDatabase::class.java
        )
            .build()

        articleDatabase = RoomArticleDatabase(appDatabase)
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun insertReadBookmarks() {
        // TODO: This test fails if we removes the test tags.
        //  Worth looking into why - it seems that when it reads from DB
        //  that tags returns a single list with an empty tag.
        val testArticle = PersistableArticle(
            url = "https://testUrl",
            bookmarked = true,
            authorName = "",
            title = "",
            tags = listOf("Test"),
        )

        runBlocking {
            articleDatabase.insertArticle(testArticle)

            val bookmarksFlow = articleDatabase.fetchBookmarks()
            val bookmarkList = bookmarksFlow.first()
            val firstBookmark = bookmarkList.first()
            assertThat(firstBookmark).isEqualTo(testArticle)
        }
    }
}
