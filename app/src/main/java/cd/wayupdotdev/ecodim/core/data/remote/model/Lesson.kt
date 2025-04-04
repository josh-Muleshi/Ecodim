package cd.wayupdotdev.ecodim.core.data.remote.model

import java.util.Date

data class Lesson(
    val uid: String = "",
    val userId: String = "",
    val content: String = "",
    val createdAt: Date? = null,
)
