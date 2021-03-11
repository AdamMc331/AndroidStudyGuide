package com.adammcneilly.androidstudyguide

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.ui.graphics.vector.ImageVector

sealed class HomeScreenTab(
    val route: String,
    @StringRes val labelResourceId: Int,
    val icon: ImageVector,
) {
    object AllArticles : HomeScreenTab(
        "all_articles",
        R.string.all_articles,
        Icons.Default.LibraryBooks,
    )

    object Bookmarks : HomeScreenTab(
        "bookmarks",
        R.string.bookmarks,
        Icons.Default.Bookmark,
    )
}
