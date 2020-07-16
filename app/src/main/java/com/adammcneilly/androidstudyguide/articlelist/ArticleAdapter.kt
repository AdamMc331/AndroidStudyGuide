package com.adammcneilly.androidstudyguide.articlelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adammcneilly.androidstudyguide.databinding.ListItemArticleBinding
import com.adammcneilly.androidstudyguide.models.Article

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

        init {
            binding.root.setOnClickListener(this)
        }

        fun bindArticle(article: Article) {
            binding.article = article
            binding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            val article = binding.article
            if (article != null) {
                clickListener.onArticleClicked(article)
            }
        }
    }
}
