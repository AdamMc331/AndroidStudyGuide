package com.adammcneilly.androidstudyguide.articlelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adammcneilly.androidstudyguide.R
import com.adammcneilly.androidstudyguide.databinding.ListItemArticleBinding
import com.adammcneilly.androidstudyguide.models.Article
import com.google.android.material.chip.Chip

/**
 * This adapter class is responsible for taking a list of [articles] and binding them into a
 * [RecyclerView].
 *
 * @property[clickListener] This will handle all the callbacks for click events on an article item.
 */
class ArticleAdapter(
    private val clickListener: ArticleClickListener
) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    var articles: List<Article> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemArticleBinding.inflate(inflater, parent, false)
        return ArticleViewHolder(binding, clickListener)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.bindArticle(article)
    }

    class ArticleViewHolder(
        private val binding: ListItemArticleBinding,
        private val clickListener: ArticleClickListener
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private val viewModel = ArticleListItemViewModel()

        init {
            binding.articleContent.setOnClickListener(this)
            binding.bookmarkButton.setOnClickListener(this)
        }

        fun bindArticle(article: Article) {
            this.viewModel.article = article
            binding.articleTitle.text = viewModel.articleTitle
            binding.articleAuthor.text = viewModel.getAuthorText(itemView.resources)

            binding.bookmarkButton.setImageResource(viewModel.bookmarkButtonRes)

            binding.chipGroup.removeAllViews()
            viewModel.articleTags.forEach { tag ->
                val chip = Chip(itemView.context)
                chip.text = tag
                binding.chipGroup.addView(chip)
            }
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.bookmark_button -> viewModel.article?.let(clickListener::onBookmarkClicked)
                else -> viewModel.article?.let(clickListener::onArticleClicked)
            }
        }
    }
}
