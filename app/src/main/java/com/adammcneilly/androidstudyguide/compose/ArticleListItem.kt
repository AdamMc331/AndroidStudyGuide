package com.adammcneilly.androidstudyguide.compose

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.androidstudyguide.R
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.util.HtmlString

/**
 * @param[childModifier] This is a modifier that should be passed the children of an article
 * list item composable (such as the texts and button items). In practice, this is used to show a
 * placeholder effect until data has loaded for an article.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArticleListItem(
    article: Article,
    onBookmarkClicked: (Article) -> Unit,
    onArticleClicked: (Article) -> Unit,
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
) {
    Card(
        onClick = {
            onArticleClicked(article)
        }
    ) {
        Row(
            modifier = modifier
                .padding(all = dimensionResource(id = R.dimen.article_list_item_padding))
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ArticleTitleAndAuthor(
                article = article,
                modifier = Modifier.weight(1f),
                childModifier = childModifier,
            )
            BookmarkButton(
                article = article,
                onClick = onBookmarkClicked,
                modifier = childModifier,
            )
        }
    }
}

/**
 * See [ArticleListItem] documentation for an understanding of why we need [childModifier].
 */
@Composable
private fun ArticleTagsRow(
    article: Article,
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.article_tags_spacing)),
        modifier = modifier
            .semantics {
                contentDescription = "Article Tags"
            }
    ) {
        article.tags.forEach { tag ->
            Text(
                text = tag,
                style = MaterialTheme.typography.caption,
                modifier = childModifier
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
    modifier: Modifier = Modifier,
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
        },
        modifier = modifier,
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
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = article.htmlTitle.getInput(),
            style = MaterialTheme.typography.h5,
            modifier = childModifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(id = R.dimen.article_title_bottom_padding))
        )
        Text(
            text = "By ${article.authorName}",
            style = MaterialTheme.typography.subtitle1,
            modifier = childModifier
                .fillMaxWidth(0.75F)
                .padding(bottom = dimensionResource(id = R.dimen.article_author_bottom_padding))
        )
        if (article.tags.isNotEmpty()) {
            ArticleTagsRow(
                article = article,
                childModifier = childModifier,
            )
        }
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Day Mode - Tablet",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.PIXEL_C,
)
@Composable
private fun ArticleListItemPreview() {
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
