package com.adammcneilly.androidstudyguide.data.local

import kotlinx.coroutines.flow.Flow

/**
 * This interface defines the data contract for requesting or inserting any data to a local
 * database on the device.
 *
 * This allows us to have the freedom to change our backing database implementation
 * (Room -> SQLDelight -> Realm -> ???)
 */
interface ArticleDatabase {
    fun fetchBookmarks(): Flow<List<PersistableArticle>>

    suspend fun insertArticle(article: PersistableArticle)
}
