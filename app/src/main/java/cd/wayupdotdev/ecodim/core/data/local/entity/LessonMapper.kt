package cd.wayupdotdev.ecodim.core.data.local.entity

import cd.wayupdotdev.ecodim.core.domain.model.Lesson
import java.util.Date

fun LessonEntity.toDomain(): Lesson = Lesson(
    uid = id,
    userId = userId,
    content = content,
    createdAt = Date(createdAt),
    updatedAt = Date(updatedAt)
)

fun Lesson.toEntity(isFavorite: Boolean = false): LessonEntity =
    LessonEntity(
        id = uid,
        userId = userId,
        content = content,
        createdAt = createdAt.time,
        updatedAt = updatedAt.time,
        isFavorite = isFavorite
    )