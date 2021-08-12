package com.adammcneilly.androidstudyguide

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.androidstudyguide.articlelist.ArticleListViewState
import com.adammcneilly.androidstudyguide.compose.ArticleCollection
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.util.HtmlString
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder

@Composable
fun ArticleListScreenContent(
    currentState: ArticleListViewState?,
    onBookmarkClicked: (Article) -> Unit,
    onArticleClicked: (Article) -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        when (currentState) {
            is ArticleListViewState.Success -> {
                ArticleCollection(
                    articles = currentState.articles,
                    onBookmarkClicked = onBookmarkClicked,
                    onArticleClicked = onArticleClicked,
                    isRefreshing = currentState.refreshing,
                    onRefresh = onRefresh,
                )
            }
            is ArticleListViewState.Loading -> {
                val placeholderArticles = (1..5).map { index ->
                    Article(
                        htmlTitle = HtmlString("Placeholder article $index"),
                        tags = listOf("testing", "testing"),
                    )
                }

                ArticleCollection(
                    articles = placeholderArticles,
                    onBookmarkClicked = {},
                    onArticleClicked = {},
                    modifier = Modifier.progressSemantics(),
                    childModifier = Modifier.placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.fade(),
                        shape = CircleShape,
                    ),
                    isRefreshing = false,
                    onRefresh = {},
                )
            }
        }
    }
}
