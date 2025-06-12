package cd.wayupdotdev.ecodim.core.domain.model

import cd.wayupdotdev.ecodim.core.data.remote.model.RemoteUser
import java.util.Date

data class User(
    val uid: String,
    val author: String,
    val createdAt: Date,
)

fun User.toRemote(): RemoteUser {
    return RemoteUser(
        uid = uid,
        author = author,
        createdAt = createdAt,
    )
}
