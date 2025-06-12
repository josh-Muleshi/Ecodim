package cd.wayupdotdev.ecodim.core.data.remote.model

import androidx.annotation.Keep
import cd.wayupdotdev.ecodim.core.domain.model.Comment
import java.util.Date

@Keep
data class RemoteComment(
    val uid: String = "",
    val userId: String = "",
    val userName: String = "",
    val text: String = "",
    val createdAt: Date = Date()
)

fun RemoteComment.toDomain(): Comment {
    return Comment(
        uid = uid,
        userId = userId,
        userName = userName,
        text = text,
        createdAt = createdAt
    )
}