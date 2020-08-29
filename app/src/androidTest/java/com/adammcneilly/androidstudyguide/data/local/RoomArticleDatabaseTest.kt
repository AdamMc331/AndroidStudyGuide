package com.adammcneilly.androidstudyguide.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
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
        val testArticle = PersistableArticle(
            url = "https://testUrl",
            bookmarked = true,
            authorName = "",
            title = ""
        )

        runBlocking {
            articleDatabase.insertArticle(testArticle)

            val bookmarks = articleDatabase.fetchBookmarks()
            assertThat(bookmarks.first()).isEqualTo(testArticle)
        }
    }
}
