package cd.wayupdotdev.ecodim.core.ui

import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavOptionsBuilder

sealed class Destination(val route: String) {
    object HomeScreen : Destination("home")
    object TopicDetail : Destination("event-detail/{eventUid}") {
        fun createRoute(eventUid: String): String {
            return "event-detail/$eventUid"
        }
    }
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