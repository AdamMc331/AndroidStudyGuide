package com.adammcneilly.androidstudyguide.articlelist

import com.adammcneilly.androidstudyguide.R
import com.adammcneilly.androidstudyguide.models.Article
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ArticleListItemViewModelTest {

    @Test
    fun getBookmarkIconRes() {
        val viewModel = ArticleListItemViewModel()

        viewModel.article = Article(bookmarked = true)
        assertThat(viewModel.bookmarkButtonRes).isEqualTo(R.drawable.ic_bookmark_selected)

        viewModel.article = Article(bookmarked = false)
        assertThat(viewModel.bookmarkButtonRes).isEqualTo(R.drawable.ic_bookmark_unselected)
    }
}
