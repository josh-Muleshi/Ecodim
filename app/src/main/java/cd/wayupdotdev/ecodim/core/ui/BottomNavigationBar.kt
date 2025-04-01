package cd.wayupdotdev.ecodim.core.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

sealed class TopLevelRoute(val route: String, val icon: ImageVector, val name: String) {
    data object HomeScreen : TopLevelRoute("home", Icons.Rounded.Home, "Accueil")
    data object FavoriteScreen : TopLevelRoute("favorite", Icons.Rounded.Bookmark, "Favoris")
    data object AboutScreen : TopLevelRoute("about", Icons.Rounded.Info, "Apropos")
}

val topLevelRoutes = listOf(
    TopLevelRoute.HomeScreen,
    TopLevelRoute.FavoriteScreen,
    TopLevelRoute.AboutScreen
)

@Composable
fun BottomNavigationBar(navController: NavHostController, destination: NavDestination?) {
    NavigationBar {
        topLevelRoutes.forEach { route ->
            NavigationBarItem(
                selected = destination.isCurrent(route.route),
                onClick = {
                    if (!destination.isCurrent(route.route)) {
                        navController.navigate(route.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                label = { Text(text = route.name) },
                icon = { Icon(imageVector = route.icon, contentDescription = route.name) }
            )
        }
    }
}

fun NavDestination?.isCurrent(route: String): Boolean {
    return this?.hierarchy?.any { it.route == route } == true
}
