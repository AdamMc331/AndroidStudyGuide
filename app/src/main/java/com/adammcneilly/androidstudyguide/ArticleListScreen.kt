package com.adammcneilly.androidstudyguide

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.adammcneilly.androidstudyguide.articlelist.ArticleListViewState
import com.adammcneilly.androidstudyguide.articlelist.BaseArticleListViewModel
import com.adammcneilly.androidstudyguide.compose.ArticleCollection
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        when (currentState) {
            is ArticleListViewState.Success -> {
                ArticleCollection(
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
}
