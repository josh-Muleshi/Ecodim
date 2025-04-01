package cd.wayupdotdev.ecodim.core.ui

import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavOptionsBuilder

sealed class Destination(val route: String) {
    data object HomeScreen : Destination("home")
    data object SettingsScreen : Destination("settings")
    data object TopicDetailScreen : Destination("topic-detail/{topicUid}") {
        fun createRoute(topicUid: String): String {
            return "topic-detail/$topicUid"
        }
    }
    data object AboutScreen : Destination("about")
    data object CommentScreen : Destination("comment")
    data object FavoriteScreen : Destination("favorite")
    data object SearchScreen : Destination("search")
}

fun NavDestination?.isCurrent(destination: Destination): Boolean {
    return this?.hierarchy?.any { it.route == destination.route } == true
}

fun NavController.navigate(
    destination: Destination,
    builder: (NavOptionsBuilder.() -> Unit)? = null
) {

    // some times this throw an illegalStateException (should figure out why)
    if (builder != null) {
        try {
            navigate(destination.route, builder)
        } catch (e: Exception) {
            navigate(destination.route)
        }
    } else {
        navigate(destination.route)
    }
}

