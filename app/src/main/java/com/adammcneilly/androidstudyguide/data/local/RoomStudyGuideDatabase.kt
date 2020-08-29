package com.adammcneilly.androidstudyguide.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * This database implementation defines the Room database contracts for ALL information
 * stored in the study guide application.
 */
@Database(
    entities = [PersistableArticle::class],
    version = 1
)
abstract class RoomStudyGuideDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDAO

    companion object {
        fun createDatabase(
            appContext: Context
        ): RoomStudyGuideDatabase {
            return Room.databaseBuilder(
                appContext,
                RoomStudyGuideDatabase::class.java,
                "android-study-guide.db"
            )
                .build()
        }
    }
}
