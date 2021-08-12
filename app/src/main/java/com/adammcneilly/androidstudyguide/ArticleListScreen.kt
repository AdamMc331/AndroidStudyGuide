package com.adammcneilly.androidstudyguide

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.adammcneilly.androidstudyguide.articlelist.BaseArticleListViewModel
import com.adammcneilly.androidstudyguide.models.Article

@Composable
fun ArticleListScreen(
    viewModel: BaseArticleListViewModel,
    onBookmarkClicked: (Article) -> Unit,
    onArticleClicked: (Article) -> Unit,
    paddingValues: PaddingValues,
) {
    val state = viewModel.state.observeAsState()

    val currentState = state.value

    ArticleListScreenContent(
        currentState = currentState,
        onBookmarkClicked = onBookmarkClicked,
        onArticleClicked = onArticleClicked,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        onRefresh = {
            viewModel.refresh()
        },
    )
}
