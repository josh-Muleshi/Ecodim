package cd.wayupdotdev.ecodim.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import cd.wayupdotdev.ecodim.features.home.HomeScreen
import cd.wayupdotdev.ecodim.features.setting.SettingScreen
import cd.wayupdotdev.ecodim.features.topicDetail.TopicDetailScreen

@Composable
fun AppNavHost(
    modifier: Modifier,
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
                onTopicClick = { topicUid ->
                    navController.navigate(
                        Destination.TopicDetailScreen.createRoute(
                            topicUid = topicUid
                        )
                    )
                }
            )
        }

        composable(
            route = Destination.TopicDetailScreen.route,
            deepLinks = listOf(navDeepLink {
                uriPattern = "https://ecodim.app/topic/{topicUid}"
            })

        ) {
            TopicDetailScreen(
                onBackClick = navController::navigateUp
            )
        }

        composable(route = Destination.SettingsScreen.route) {
            SettingScreen (
                onBackClick = { navController.navigateUp() },
                darkMode = isDarkTheme,
                onDarkModeChange = onDarkModeChange
            )
        }
    }
}
