package com.adammcneilly.androidstudyguide

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.adammcneilly.androidstudyguide.articlelist.ArticleListViewState
import com.adammcneilly.androidstudyguide.articlelist.BaseArticleListViewModel
import com.adammcneilly.androidstudyguide.compose.ArticleList

@Composable
fun ArticleListScreen(
    viewModel: BaseArticleListViewModel
) {
    val state = viewModel.state.observeAsState()

    val currentState = state.value

    when (currentState) {
        is ArticleListViewState.Success -> {
            ArticleList(
                articles = currentState.articles,
                onBookmarkClicked = { article ->
//                    this.onBookmarkClicked(article)
                },
                onArticleClicked = { article ->
//                    this.onArticleClicked(article)
                }
            )
        }
        is ArticleListViewState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .wrapContentSize(align = Alignment.Center)
            )
        }
    }
}
