package cd.wayupdotdev.ecodim.core.data.remote.model

import androidx.annotation.Keep
import cd.wayupdotdev.ecodim.core.domain.model.Lesson
import java.util.Date

@Keep
data class RemoteLesson(
    var uid: String = "",
    var userId: String = "",
    var content: String = "",
    var createdAt: Date? = null
)

fun RemoteLesson.toDomain(): Lesson? = createdAt?.let {
    Lesson(
        uid = uid,
        userId = userId,
        content = content,
        createdAt = it
    )
}

