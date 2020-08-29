package com.adammcneilly.androidstudyguide.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adammcneilly.androidstudyguide.models.Article

@Entity
data class PersistableArticle(
    @PrimaryKey(autoGenerate = false)
    val url: String,
    val title: String,
    val authorName: String,
    val bookmarked: Boolean
)

fun Article.toPersistableArticle(): PersistableArticle {
    return PersistableArticle(
        url = this.url,
        title = this.htmlTitle.getInput(),
        authorName = this.authorName,
        bookmarked = this.bookmarked
    )
}
