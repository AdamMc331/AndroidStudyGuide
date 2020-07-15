package com.adammcneilly.androidstudyguide.articlelist

import com.adammcneilly.androidstudyguide.models.Article

interface ArticleClickListener {
    fun onArticleClicked(article: Article)
}
