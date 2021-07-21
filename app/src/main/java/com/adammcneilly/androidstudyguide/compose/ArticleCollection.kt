package com.adammcneilly.androidstudyguide.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.booleanResource
import com.adammcneilly.androidstudyguide.R
import com.adammcneilly.androidstudyguide.models.Article

/**
 * We have a boolean resource that determines whether or not to show an article grid vs a list.
 * This is based on the width of the screen.
 *
 * This composable will go into the article collection composable based on said boolean.
 *
 * @param[childModifier] This is a separate modifier applied to child items inside the list items
 * to show a placeholder UI while data is loading.
 */
@Composable
fun ArticleCollection(
    articles: List<Article>,
    onBookmarkClicked: (Article) -> Unit,
    onArticleClicked: (Article) -> Unit,
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
) {
    val shouldShowGrid = booleanResource(id = R.bool.showArticleGrid)

    if (shouldShowGrid) {
        ArticleGrid(articles, onBookmarkClicked, onArticleClicked, modifier, childModifier)
    } else {
        ArticleList(articles, onBookmarkClicked, onArticleClicked, modifier, childModifier)
    }
}
