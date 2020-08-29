package com.adammcneilly.androidstudyguide.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PersistableArticle(
    @PrimaryKey(autoGenerate = false)
    val url: String,
    val title: String,
    val authorName: String,
    val bookmarked: Boolean
)
