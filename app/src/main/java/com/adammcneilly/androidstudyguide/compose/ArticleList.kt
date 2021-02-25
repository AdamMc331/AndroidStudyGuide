package com.adammcneilly.androidstudyguide.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.util.HtmlString

@Composable
fun ArticleList(articles: List<Article>) {
    LazyColumn(
        contentPadding = PaddingValues(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(articles) { article ->
            ArticleListItem(article = article)
        }
    }
}

@Preview
@Composable
fun PreviewArticleList() {
    val firstArticle = Article(
        htmlTitle = HtmlString("Adam's test article."),
        authorName = "Adam McNeilly",
        bookmarked = true,
        tags = listOf("Jetpack", "Compose")
    )
    
    val secondArticle = firstArticle.copy(
        bookmarked = false,
    )
    
    val thirdArticle = secondArticle.copy(
        tags = listOf("Android"),
    )
    
    val articleList = listOf(firstArticle, secondArticle, thirdArticle)

    StudyGuideTheme(isInDarkMode = true) {
        ArticleList(articleList)
    }
}