package cd.wayupdotdev.ecodim.core.ui

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cd.wayupdotdev.ecodim.features.about.AboutScreen
import cd.wayupdotdev.ecodim.features.comments.CommentScreen
import cd.wayupdotdev.ecodim.features.favorite.FavoriteScreen
import cd.wayupdotdev.ecodim.features.home.HomeScreen
import cd.wayupdotdev.ecodim.features.home.HomeViewModel
import cd.wayupdotdev.ecodim.features.setting.SettingScreen
import cd.wayupdotdev.ecodim.features.topicDetail.TopicDetailScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavHost(
    modifier: Modifier,
    drawerState: DrawerState,
    navController: NavHostController,
    isDarkTheme: Boolean,
    onDarkModeChange: (Boolean) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destination.HomeScreen.route
    ) {
        composable(route = Destination.HomeScreen.route) {
            HomeScreen (
                drawerState = drawerState,
                onTopicItemClicked = { topicUid ->
                    navController.navigate(Destination.TopicDetailScreen.createRoute(
                        topicUid = topicUid
                    ))
                },
                onSettingClicked = { navController.navigate(Destination.SettingsScreen.route) },
                viewModel = viewModel
            )
        }

        composable(
            route = Destination.TopicDetailScreen.route,
        ) { backStackEntry ->
            val topicUid = backStackEntry.arguments?.getString("topicUid") ?: ""
            TopicDetailScreen(
                topicUid = topicUid,
                onBackBtnClicked = { navController.navigateUp() }
            )
        }


        composable(route = Destination.SettingsScreen.route) {
            SettingScreen (
                onHelpBtnClicked = {  },
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
                onBackBtnClicked = { navController.navigateUp() }
            )
        }

        composable(route = Destination.FavoriteScreen.route) {
            FavoriteScreen (
                drawerState = drawerState,
                onTopicItemClicked = { topicUid ->
                    navController.navigate(Destination.TopicDetailScreen.createRoute(
                        topicUid = topicUid
                    ))
                }
            )
        }
    }
}
