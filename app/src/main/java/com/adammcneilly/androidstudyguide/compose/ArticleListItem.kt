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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Devices
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
    val widthPercentage = getItemWidthPercentage()

    Card(
        onClick = {
            onArticleClicked(article)
        }
    ) {
        Row(
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.article_list_item_padding))
                .fillMaxWidth(widthPercentage),
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

/**
 * Get a reference to the screen configuration, and determine if we're on a tablet or any large
 * width device (like a phone in landscape).
 *
 * If so, we're going to return a width percentage of 0.5 so articles take up 50% of the screen.
 * Otherwise, we can return 1, to take up the whole screen.
 *
 * The centering of article items is handled inside [ArticleList].
 */
@Composable
private fun getItemWidthPercentage(): Float {
    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp

    // https://developer.android.com/training/multiscreen/screensizes#TaskUseSWQuali
    // 600dp is the smallest width for a standard 7" tablet.
    val isTablet = (screenWidth >= 600)

    return if (isTablet) {
        0.5F
    } else {
        1F
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
