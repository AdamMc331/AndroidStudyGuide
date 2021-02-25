package com.adammcneilly.androidstudyguide.articlelist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.adammcneilly.androidstudyguide.compose.ArticleList
import com.adammcneilly.androidstudyguide.compose.StudyGuideTheme
import com.adammcneilly.androidstudyguide.databinding.FragmentArticleListBinding
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.util.visibleIf

/**
 * This page is responsible for displaying a list of articles to the user. The user should be able
 * to interact with these articles by clicking on them, which will navigate away from our application
 * and to a web browser for reading the articles.
 */
abstract class BaseArticleListFragment : Fragment(), ArticleClickListener {

//    private lateinit var binding: FragmentArticleListBinding
    private lateinit var adapter: ArticleAdapter

    abstract val viewModel: BaseArticleListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = ArticleAdapter(
            clickListener = this
        )

        val composeView = ComposeView(requireContext())

        composeView.setContent {
            StudyGuideTheme {
                val state = viewModel.state.observeAsState()

                val currentState = state.value

                when (currentState) {
                    is ArticleListViewState.Success -> {
                        ArticleList(currentState.articles)
                    }
                }
            }
        }

        return composeView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToViewModel()

//        binding.retryButton.setOnClickListener {
//            viewModel.retryClicked()
//        }
    }

    private fun subscribeToViewModel() {
        viewModel.state.observe(
            viewLifecycleOwner,
            { viewState ->
                displayViewState(viewState)
            }
        )
    }

    private fun displayViewState(viewState: ArticleListViewState) {
//        binding.progressBar.visibleIf(viewState is ArticleListViewState.Loading)
//        binding.articleList.visibleIf(viewState is ArticleListViewState.Success)
//        binding.errorGroup.visibleIf(viewState is ArticleListViewState.Error)
//        binding.emptyStateTextView.visibleIf(viewState is ArticleListViewState.Empty)
//
//        binding.emptyStateTextView.setText(viewModel.emptyStateMessageTextRes)
//
//        if (viewState is ArticleListViewState.Success) {
//            adapter.articles = viewState.articles
//        }
    }

    private fun setupRecyclerView() {
//        binding.articleList.adapter = adapter
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
