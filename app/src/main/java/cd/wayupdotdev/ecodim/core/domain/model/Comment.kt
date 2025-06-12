package cd.wayupdotdev.ecodim.core.domain.model

import java.util.Date

data class Comment(
    val uid: String,
    val userId: String,
    val userName: String,
    val text: String,
    val createdAt: Date,
)
