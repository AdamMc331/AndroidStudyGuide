package com.adammcneilly.androidstudyguide.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Devices.PIXEL_C
import androidx.compose.ui.tooling.preview.Preview
import com.adammcneilly.androidstudyguide.R
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.util.HtmlString

val ColumnWidthKey = SemanticsPropertyKey<Float>("ColumnWidth")
var SemanticsPropertyReceiver.columnWidth by ColumnWidthKey

@Composable
fun ArticleList(
    articles: List<Article>,
    onBookmarkClicked: (Article) -> Unit,
    onArticleClicked: (Article) -> Unit,
) {
    val columnWidthPercentage = getColumnWidthPercentage()

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        ArticleListColumn(
            articles,
            onBookmarkClicked,
            onArticleClicked,
            modifier = Modifier
                .fillMaxWidth(columnWidthPercentage)
                .align(Alignment.Center)
                .semantics {
                    columnWidth = columnWidthPercentage
                },
        )
    }
}

/**
 * This was extracted into a separate function, so that it can be wrapped by
 * [ArticleList] to make sure it is centered on the screen.
 */
@Composable
private fun ArticleListColumn(
    articles: List<Article>,
    onBookmarkClicked: (Article) -> Unit,
    onArticleClicked: (Article) -> Unit,
    modifier: Modifier = Modifier,
) {

    LazyColumn(
        contentPadding = PaddingValues(all = dimensionResource(id = R.dimen.article_list_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.article_list_spacing)),
        modifier = modifier,
    ) {
        items(
            items = articles,
            key = { article ->
                article.toString()
            },
        ) { article ->
            ArticleListItem(
                article = article,
                onBookmarkClicked = onBookmarkClicked,
                onArticleClicked = onArticleClicked,
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
 */
@Composable
private fun getColumnWidthPercentage(): Float {
    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp

    // https://developer.android.com/training/multiscreen/screensizes#TaskUseSWQuali
    // 600dp is the smallest width for a standard 7" tablet.
    val isTablet = (screenWidth >= 600)

    // Alternatively, if you don't want to do this conditional logic inside your Kotlin code,
    // you can put a boolean inside your resource folders and reference it here.
    // val isTablet = booleanResource(id = R.bool.isTablet)

    return if (isTablet) {
        0.5F
    } else {
        1F
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
    device = PIXEL_C,
)
@Composable
private fun ArticleListPreview() {
    val firstArticle = Article(
        htmlTitle = HtmlString("Adam's test article."),
        authorName = "Adam McNeilly",
        bookmarked = true,
        tags = listOf("Jetpack", "Compose")
    )

    val secondArticle = firstArticle.copy(
        bookmarked = false,
    )

    val thirdArticle = secondArticle.copy(
        tags = listOf("Android"),
    )

    val articleList = listOf(firstArticle, secondArticle, thirdArticle)

    StudyGuideTheme {
        ArticleList(
            articleList,
            onBookmarkClicked = {},
            onArticleClicked = {}
        )
    }
}
