package cd.wayupdotdev.ecodim.core.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController

@Composable
fun BottomNavigationBar(navController: NavHostController, destination: NavDestination?) {
    NavigationBar {
        NavigationBarItem(
            selected = destination.isCurrent(Destination.HomeScreen),
            onClick = {
                if (!destination.isCurrent(Destination.HomeScreen)) {
                    navController.navigate(Destination.HomeScreen) {
                        popUpTo(Destination.HomeScreen.route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            },
            label = {
                Text(text = "Accueil")
            },
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Home,
                    contentDescription = null
                )
            }
        )

        NavigationBarItem(
            selected = destination.isCurrent(Destination.FavoriteScreen),
            onClick = {
                if (!destination.isCurrent(Destination.FavoriteScreen)) {
                    navController.navigate(Destination.FavoriteScreen) {
                        popUpTo(Destination.HomeScreen.route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            },
            label = {
                Text(text = "Favoris")
            },
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Bookmark,
                    contentDescription = null
                )
            }
        )

        NavigationBarItem(
            selected = destination.isCurrent(Destination.AboutScreen),
            onClick = {
                if (!destination.isCurrent(Destination.AboutScreen)) {
                    navController.navigate(Destination.AboutScreen) {
                        popUpTo(Destination.HomeScreen.route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            },
            label = {
                Text(text = "Apropos")
            },
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Info,
                    contentDescription = null
                )
            }
        )
    }
}