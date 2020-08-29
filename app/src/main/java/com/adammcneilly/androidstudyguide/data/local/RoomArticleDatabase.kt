package com.adammcneilly.androidstudyguide.data.local

/**
 * This implementation uses a Room database to request and insert Article information.
 *
 * This is separate from the overall application database defined in [RoomStudyGuideDatabase].
 */
class RoomArticleDatabase(
    private val roomDatabase: RoomStudyGuideDatabase
) : ArticleDatabase {
    override suspend fun fetchBookmarks(): List<PersistableArticle> {
        return roomDatabase.articleDao().fetchBookmarks()
    }

    override suspend fun insertArticle(article: PersistableArticle) {
        roomDatabase.articleDao().insertArticle(article)
    }
}
