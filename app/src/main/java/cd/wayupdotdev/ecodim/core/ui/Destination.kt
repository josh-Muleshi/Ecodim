package cd.wayupdotdev.ecodim.core.ui

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

