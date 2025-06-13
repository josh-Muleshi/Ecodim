package cd.wayupdotdev.ecodim.core.ui

import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cd.wayupdotdev.ecodim.core.data_preferences.SettingDataStorePreferences
import cd.wayupdotdev.ecodim.ui.theme.EcodimTheme
import kotlinx.coroutines.launch

fun ComponentActivity.installUi() {
    val dataStoreUtil = SettingDataStorePreferences(applicationContext)

    val systemTheme =
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> true
            Configuration.UI_MODE_NIGHT_NO -> false
            else -> false
        }

    setContent {

        val isDarkTheme by dataStoreUtil.getTheme(systemTheme)
            .collectAsStateWithLifecycle(initialValue = systemTheme)

        val coroutineScope = rememberCoroutineScope()
        val navController = rememberNavController()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

        val destination = navController
            .currentBackStackEntryAsState().value?.destination

        EcodimTheme(darkTheme = isDarkTheme) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        AppDrawer(
                            onCloseDrawer = { coroutineScope.launch { drawerState.close() } },
                            onMenuItemClick = { menuItem ->
                                val route = when (menuItem) {
                                    "Accueil" -> Destination.HomeScreen.route
                                    "Communautés" -> Destination.CommentScreen.route
                                    "Favoris" -> Destination.FavoriteScreen.route
                                    "Recherche" -> Destination.SearchScreen.route
                                    "Paramètres" -> Destination.SettingsScreen.route
                                    "Aide & Support" -> null // Pour debug ou future implémentation
                                    else -> null
                                }

                                route?.let {
                                    if (navController.currentDestination?.route != it) {
                                        navController.navigate(it) {
                                            popUpTo(navController.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                }

                                coroutineScope.launch { drawerState.close() }
                            }
                        )
                    }
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            key(destination?.route) {
                                AnimatedVisibility(
                                    visible = shouldShowBottomNavigation(destination),
                                    enter = slideInVertically { it / 2 },
                                    exit = slideOutVertically { it / 2 },
                                ) {
                                    BottomNavigationBar(
                                        navController = navController,
                                        destination = destination
                                    )
                                }
                            }

                        }
                    ) { paddingValues ->
                        AppNavHost(
                            modifier = Modifier.padding(paddingValues),
                            drawerState = drawerState,
                            navController = navController,
                            isDarkTheme = isDarkTheme,
                            onDarkModeChange = { darkMode ->
                                coroutineScope.launch {
                                    dataStoreUtil.saveTheme(darkMode)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

private fun shouldShowBottomNavigation(destination: NavDestination?): Boolean {
    return destination?.hierarchy?.none {
        it.route == Destination.TopicDetailScreen.route ||
                it.route == Destination.SettingsScreen.route ||
                it.route == Destination.CommentScreen.route ||
                it.route == Destination.SearchScreen.route
    } == true
}
