package com.adammcneilly.androidstudyguide.data.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDAO {
    @Query("SELECT * FROM PersistableArticle WHERE isBookmarked = 1")
    fun fetchBookmarks(): Flow<List<PersistableArticle>>
}
