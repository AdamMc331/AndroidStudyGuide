package com.adammcneilly.androidstudyguide.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.androidstudyguide.R
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.util.HtmlString

@Composable
fun ArticleListItem(article: Article) {
    Card {
        Row(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ArticleTitleAndAuthor(
                article = article,
                modifier = Modifier.weight(1f)
            )
            BookmarkButton(article = article)
        }
    }
}

@Composable
private fun ArticleTagsRow(article: Article) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
       article.tags.forEach { tag ->
           Text(
               text = tag,
               style = MaterialTheme.typography.caption,
               modifier = Modifier
                   .background(
                       color = MaterialTheme.colors.primary,
                       shape = CircleShape
                   )
                   .padding(
                       horizontal = 8.dp,
                       vertical = 4.dp,
                   )
           )
       }
    }
}

@Composable
private fun BookmarkButton(article: Article) {
    val iconRes = if (article.bookmarked) {
        R.drawable.ic_bookmark_selected
    } else {
        R.drawable.ic_bookmark_unselected
    }

    IconButton(
        onClick = { /*TODO*/ }
    ) {
        Image(
            painterResource(iconRes),
            contentDescription = "Bookmark",
            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.secondary)
        )
    }
}

@Composable
private fun ArticleTitleAndAuthor(
    article: Article,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = article.htmlTitle.getInput(),
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .padding(bottom = 8.dp)
        )
        Text(
            text = "By ${article.authorName}",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .padding(bottom = 12.dp)
        )
        ArticleTagsRow(article = article)
    }
}

@Preview
@Composable
fun PreviewArticleListItem() {
    val article = Article(
        htmlTitle = HtmlString("Adam's test article."),
        authorName = "Adam McNeilly",
        bookmarked = true,
        tags = listOf("Jetpack", "Compose")
    )

    StudyGuideTheme {
        Surface {
            ArticleListItem(article = article)
        }
    }
}