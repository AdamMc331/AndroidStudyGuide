package com.adammcneilly.androidstudyguide

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.adammcneilly.androidstudyguide.articlelist.ArticleListViewState
import com.adammcneilly.androidstudyguide.articlelist.BaseArticleListViewModel
import com.adammcneilly.androidstudyguide.compose.ArticleList
import com.adammcneilly.androidstudyguide.models.Article

@Composable
fun ArticleListScreen(
    viewModel: BaseArticleListViewModel,
    onBookmarkClicked: (Article) -> Unit,
    onArticleClicked: (Article) -> Unit,
) {
    val state = viewModel.state.observeAsState()

    val currentState = state.value

    when (currentState) {
        is ArticleListViewState.Success -> {
            ArticleList(
                articles = currentState.articles,
                onBookmarkClicked = onBookmarkClicked,
                onArticleClicked = onArticleClicked,
            )
        }
        is ArticleListViewState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center)
                )
            }
        }
    }
}
