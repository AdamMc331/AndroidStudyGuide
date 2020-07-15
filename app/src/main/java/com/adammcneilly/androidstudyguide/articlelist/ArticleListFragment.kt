package com.adammcneilly.androidstudyguide.articlelist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.adammcneilly.androidstudyguide.data.ArticleRepository
import com.adammcneilly.androidstudyguide.data.InMemoryArticleService
import com.adammcneilly.androidstudyguide.databinding.FragmentArticleListBinding
import com.adammcneilly.androidstudyguide.models.Article

class ArticleListFragment : Fragment(), ArticleClickListener {

    private lateinit var binding: FragmentArticleListBinding
    private lateinit var adapter: ArticleAdapter

    private val articleRepository: ArticleRepository = InMemoryArticleService()

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

        adapter.articles = articleRepository.fetchArticles()
    }

    private fun setupRecyclerView() {
        binding.articleList.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.articleList.addItemDecoration(dividerItemDecoration)
    }

    override fun onArticleClicked(article: Article) {
        val uri = Uri.parse(article.url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}
