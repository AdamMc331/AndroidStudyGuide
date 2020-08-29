package com.adammcneilly.androidstudyguide.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticleDAO {
    @Query("SELECT * FROM PersistableArticle WHERE bookmarked = 1")
    suspend fun fetchBookmarks(): List<PersistableArticle>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: PersistableArticle)
}
