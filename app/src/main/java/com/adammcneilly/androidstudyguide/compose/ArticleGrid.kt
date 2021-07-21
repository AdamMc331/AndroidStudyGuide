package com.adammcneilly.androidstudyguide.compose

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.adammcneilly.androidstudyguide.R
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.util.HtmlString

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArticleGrid(
    articles: List<Article>,
    onBookmarkClicked: (Article) -> Unit,
    onArticleClicked: (Article) -> Unit,
) {
    val numColumns = 3

    LazyVerticalGrid(
        cells = GridCells.Fixed(numColumns),
        modifier = Modifier
            .fillMaxSize(),
    ) {
        items(
            items = articles,
        ) { article ->
            Box(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.article_list_padding))
            ) {
                ArticleListItem(
                    article = article,
                    onBookmarkClicked = onBookmarkClicked,
                    onArticleClicked = onArticleClicked,
                )
            }
        }
    }
}

@Preview(
    name = "Day Mode - Tablet",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.NEXUS_10,
)
@Composable
private fun ArticleGridPreview() {
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
        ArticleGrid(
            articleList,
            onBookmarkClicked = {},
            onArticleClicked = {}
        )
    }
}
