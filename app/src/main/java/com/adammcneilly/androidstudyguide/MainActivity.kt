package com.adammcneilly.androidstudyguide

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import com.adammcneilly.androidstudyguide.articlelist.AndroidEssenceArticleListViewModel
import com.adammcneilly.androidstudyguide.bookmarks.BookmarkListViewModel
import com.adammcneilly.androidstudyguide.compose.StudyGuideTheme
import com.adammcneilly.androidstudyguide.models.Article
import dagger.hilt.android.AndroidEntryPoint

/**
 * This is the single activity in the application. It should be the user's main entry point
 * into using the study guide.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StudyGuideTheme {
                val navController = rememberNavController()

                val homeScreenTabs = listOf(
                    HomeScreenTab.AllArticles,
                    HomeScreenTab.Bookmarks,
                )

                Scaffold(
                    topBar = {
                        StudyGuideAppBar()
                    },
                    bottomBar = {
                        BottomNavigation {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentRoute = navBackStackEntry?.destination?.route
                            homeScreenTabs.forEach { tab ->
                                BottomNavigationItemForTab(tab, currentRoute, navController)
                            }
                        }
                    }
                ) { paddingValues ->
                    NavHost(navController, startDestination = HomeScreenTab.AllArticles.route) {
                        composable(HomeScreenTab.AllArticles.route) {
                            val viewModel: AndroidEssenceArticleListViewModel by viewModels()

                            ArticleListScreen(
                                viewModel = viewModel,
                                onBookmarkClicked = viewModel::bookmarkClicked,
                                onArticleClicked = ::showArticleInBrowser,
                                paddingValues = paddingValues,
                            )
                        }
                        composable(HomeScreenTab.Bookmarks.route) {
                            val viewModel: BookmarkListViewModel by viewModels()

                            ArticleListScreen(
                                viewModel = viewModel,
                                onBookmarkClicked = viewModel::bookmarkClicked,
                                onArticleClicked = ::showArticleInBrowser,
                                paddingValues = paddingValues,
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun RowScope.BottomNavigationItemForTab(
        tab: HomeScreenTab,
        currentRoute: String?,
        navController: NavHostController
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    tab.icon,
                    contentDescription = stringResource(id = tab.labelResourceId)
                )
            },
            label = { Text(stringResource(tab.labelResourceId)) },
            selected = (currentRoute == tab.route),
            onClick = {
                navController.navigate(tab.route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    popUpTo(navController.graph.startDestinationId)
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                }
            }
        )
    }

    @Composable
    private fun StudyGuideAppBar() {
        TopAppBar(
            title = {
                Text(stringResource(id = R.string.app_name))
            }
        )
    }

    /**
     * This was added as an attempt to handle back navigation. When the user is on the bookmarks tab
     * and presses back, we expect they will go to the all articles tab, but currently it just closes
     * the app.
     *
     * This is an open issue that still needs to be resolved.
     */
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }

    private fun showArticleInBrowser(article: Article) {
        val uri = Uri.parse(article.url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}
