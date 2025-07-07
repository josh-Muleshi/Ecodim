package cd.wayupdotdev.ecodim.core.data.remote.model

import androidx.annotation.Keep
import cd.wayupdotdev.ecodim.core.domain.model.Lesson
import java.util.Date

@Keep
data class RemoteLesson(
    var uid: String = "",
    var userId: String = "",
    var content: String = "",
    var week: Boolean = false,
    var createdAt: Date = Date(),
    var updatedAt: Date = createdAt
)

fun RemoteLesson.toDomain(): Lesson {
    return Lesson(
        uid = uid,
        userId = userId,
        content = content,
        week = week,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}




