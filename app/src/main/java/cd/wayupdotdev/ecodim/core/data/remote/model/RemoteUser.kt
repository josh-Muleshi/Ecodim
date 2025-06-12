package cd.wayupdotdev.ecodim.core.data.remote.model

import androidx.annotation.Keep
import cd.wayupdotdev.ecodim.core.domain.model.User
import java.util.Date

@Keep
data class RemoteUser(
    val uid: String = "",
    val author: String = "",
    val createdAt: Date = Date(),
)

fun RemoteUser.toDomain(): User {
    return User(
        uid = uid,
        author = author,
        createdAt = createdAt,
    )
}