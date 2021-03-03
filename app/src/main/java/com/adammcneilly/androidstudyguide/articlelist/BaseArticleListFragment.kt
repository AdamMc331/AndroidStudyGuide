package com.adammcneilly.androidstudyguide.articlelist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.adammcneilly.androidstudyguide.compose.ArticleList
import com.adammcneilly.androidstudyguide.compose.StudyGuideTheme
import com.adammcneilly.androidstudyguide.models.Article

/**
 * This page is responsible for displaying a list of articles to the user. The user should be able
 * to interact with these articles by clicking on them, which will navigate away from our application
 * and to a web browser for reading the articles.
 */
abstract class BaseArticleListFragment : Fragment(), ArticleClickListener {

    abstract val viewModel: BaseArticleListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val composeView = ComposeView(requireContext())

        composeView.setContent {
            StudyGuideTheme {
                val state = viewModel.state.observeAsState()

                val currentState = state.value

                when (currentState) {
                    is ArticleListViewState.Success -> {
                        ArticleList(
                            articles = currentState.articles,
                            onBookmarkClicked = { article ->
                                this.onBookmarkClicked(article)
                            },
                            onArticleClicked = { article ->
                                this.onArticleClicked(article)
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
        }

        return composeView
    }

    override fun onArticleClicked(article: Article) {
        val uri = Uri.parse(article.url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    override fun onBookmarkClicked(article: Article) {
        viewModel.bookmarkClicked(article)
    }
}
