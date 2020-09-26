package com.adammcneilly.androidstudyguide.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.util.HtmlString

@Entity
data class PersistableArticle(
    @PrimaryKey(autoGenerate = false)
    val url: String,
    val title: String = "",
    val authorName: String = "",
    val bookmarked: Boolean = false
) {

    fun toArticle(): Article {
        return Article(
            htmlTitle = HtmlString(this.title),
            url = this.url,
            authorName = this.authorName,
            bookmarked = this.bookmarked
        )
    }
}

fun Article.toPersistableArticle(): PersistableArticle {
    return PersistableArticle(
        url = this.url,
        title = this.htmlTitle.getInput(),
        authorName = this.authorName,
        bookmarked = this.bookmarked
    )
}
