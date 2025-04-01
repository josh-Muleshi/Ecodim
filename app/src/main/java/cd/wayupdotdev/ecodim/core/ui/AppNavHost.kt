package cd.wayupdotdev.ecodim.core.ui

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import cd.wayupdotdev.ecodim.features.about.AboutScreen
import cd.wayupdotdev.ecodim.features.comments.CommentScreen
import cd.wayupdotdev.ecodim.features.favorite.FavoriteScreen
import cd.wayupdotdev.ecodim.features.home.HomeScreen
import cd.wayupdotdev.ecodim.features.search.SearchScreen
import cd.wayupdotdev.ecodim.features.setting.SettingScreen
import cd.wayupdotdev.ecodim.features.topicDetail.TopicDetailScreen

@Composable
fun AppNavHost(
    modifier: Modifier,
    drawerState: DrawerState,
    navController: NavHostController,
    isDarkTheme: Boolean,
    onDarkModeChange: (Boolean) -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destination.HomeScreen.route
    ) {
        composable(route = Destination.HomeScreen.route) {
            HomeScreen (
                drawerState = drawerState,
                onSearchBtnClicked = { navController.navigate(Destination.SearchScreen.route) },
                onTopicItemClicked = { topicUid ->
                    navController.navigate("topic-detail/{$topicUid}")
                },
                onSettingClicked = { navController.navigate(Destination.SettingsScreen.route) }
            )
        }

        composable(
            route = "topic-detail/{topicUid}",
        ) { backStackEntry ->
            val topicUid = backStackEntry.arguments?.getString("topicUid") ?: ""
            TopicDetailScreen(
                onBackClick = navController::navigateUp
            )
        }


        composable(route = Destination.SettingsScreen.route) {
            SettingScreen (
                onAboutBtnClicked = { navController.navigate(Destination.AboutScreen.route) },
                onSecurityBtnClicked = {},
                onBackBtnClicked = { navController.navigateUp()  },
                darkMode = isDarkTheme,
                onDarkModeChange = onDarkModeChange
            )
        }

        composable(route = Destination.AboutScreen.route) {
            AboutScreen (
                drawerState = drawerState,
            )
        }

        composable(route = Destination.CommentScreen.route) {
            CommentScreen (
                onBackClick = { navController.navigateUp() },
                darkMode = isDarkTheme,
                onDarkModeChange = onDarkModeChange
            )
        }

        composable(route = Destination.FavoriteScreen.route) {
            FavoriteScreen (
                drawerState = drawerState,
            )
        }

        composable(route = Destination.SearchScreen.route) {
            SearchScreen (
                onBackBtnClicked = { navController.navigateUp()  },
            )
        }
    }
}
