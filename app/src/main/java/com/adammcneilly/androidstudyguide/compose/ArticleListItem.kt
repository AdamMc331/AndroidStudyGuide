package com.adammcneilly.androidstudyguide.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.adammcneilly.androidstudyguide.R
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.util.HtmlString

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArticleListItem(
    article: Article,
    onBookmarkClicked: (Article) -> Unit,
    onArticleClicked: (Article) -> Unit,
) {
    Card(
        onClick = {
            onArticleClicked(article)
        }
    ) {
        Row(
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.article_list_item_padding))
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ArticleTitleAndAuthor(
                article = article,
                modifier = Modifier.weight(1f)
            )
            BookmarkButton(
                article = article,
                onClick = onBookmarkClicked,
            )
        }
    }
}

@Composable
private fun ArticleTagsRow(article: Article) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.article_tags_spacing)),
        modifier = Modifier
            .semantics {
                contentDescription = "Article Tags"
            }
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
                        horizontal = dimensionResource(id = R.dimen.article_tag_horizontal_padding),
                        vertical = dimensionResource(id = R.dimen.article_tag_vertical_padding),
                    )
            )
        }
    }
}

@Composable
private fun BookmarkButton(
    article: Article,
    onClick: (Article) -> Unit,
) {
    val iconRes = if (article.bookmarked) {
        R.drawable.ic_bookmark_selected
    } else {
        R.drawable.ic_bookmark_unselected
    }

    val contentDescription = if (article.bookmarked) {
        "Bookmarked"
    } else {
        "Not Bookmarked"
    }

    IconButton(
        onClick = {
            onClick(article)
        }
    ) {
        Image(
            painterResource(iconRes),
            contentDescription = contentDescription,
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
                .padding(bottom = dimensionResource(id = R.dimen.article_title_bottom_padding))
        )
        Text(
            text = "By ${article.authorName}",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.article_author_bottom_padding))
        )
        if (article.tags.isNotEmpty()) {
            ArticleTagsRow(article = article)
        }
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
            ArticleListItem(
                article = article,
                onBookmarkClicked = {},
                onArticleClicked = {}
            )
        }
    }
}
