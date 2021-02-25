package com.adammcneilly.androidstudyguide.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
    Row(
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth(),
    ) {
        ArticleTitleAndAuthor(article = article)
        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Image(
                painterResource(R.drawable.ic_bookmark_unselected),
                contentDescription = "Bookmark",
            )
        }
    }
}

@Composable
private fun ArticleTitleAndAuthor(article: Article) {
    Column {
        Text(
            text = article.htmlTitle.getInput(),
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .padding(bottom = 8.dp)
        )
        Text(
            text = "By ${article.authorName}",
            style = MaterialTheme.typography.subtitle1,
        )
    }
}

@Preview
@Composable
fun PreviewArticleListItem() {
    val article = Article(
        htmlTitle = HtmlString("Adam's test article."),
        authorName = "Adam McNeilly"
    )

    MaterialTheme {
        Surface {
            ArticleListItem(article = article)
        }
    }
}