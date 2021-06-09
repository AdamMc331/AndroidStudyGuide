package com.adammcneilly.androidstudyguide.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.adammcneilly.androidstudyguide.R
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.util.HtmlString

@Composable
fun ArticleList(
    articles: List<Article>,
    onBookmarkClicked: (Article) -> Unit,
    onArticleClicked: (Article) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(all = dimensionResource(id = R.dimen.article_list_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.article_list_spacing)),
    ) {
        items(articles) { article ->
            ArticleListItem(
                article = article,
                onBookmarkClicked = onBookmarkClicked,
                onArticleClicked = onArticleClicked,
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

    StudyGuideTheme(isInDarkMode = true) {
        ArticleList(
            articleList,
            onBookmarkClicked = {},
            onArticleClicked = {}
        )
    }
}
