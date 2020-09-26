package com.adammcneilly.androidstudyguide.bookmarks

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.adammcneilly.androidstudyguide.articlelist.ArticleAdapter
import com.adammcneilly.androidstudyguide.articlelist.ArticleClickListener
import com.adammcneilly.androidstudyguide.articlelist.ArticleListViewState
import com.adammcneilly.androidstudyguide.databinding.FragmentArticleListBinding
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.util.visibleIf
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkListFragment : Fragment(), ArticleClickListener {
    private lateinit var binding: FragmentArticleListBinding
    private lateinit var adapter: ArticleAdapter
    private val viewModel: BookmarkListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = ArticleAdapter(
            clickListener = this
        )
        binding = FragmentArticleListBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToViewModel()

        binding.retryButton.setOnClickListener {
            viewModel.retryClicked()
        }
    }

    private fun subscribeToViewModel() {
        viewModel.state.observe(viewLifecycleOwner, Observer { viewState ->
            displayViewState(viewState)
        })
    }

    private fun displayViewState(viewState: ArticleListViewState) {
        binding.progressBar.visibleIf(viewState is ArticleListViewState.Loading)
        binding.articleList.visibleIf(viewState is ArticleListViewState.Success)
        binding.errorGroup.visibleIf(viewState is ArticleListViewState.Error)

        if (viewState is ArticleListViewState.Success) {
            adapter.articles = viewState.articles
        }
    }

    private fun setupRecyclerView() {
        binding.articleList.adapter = adapter
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
