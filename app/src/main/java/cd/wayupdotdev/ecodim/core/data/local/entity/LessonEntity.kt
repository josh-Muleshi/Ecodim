package cd.wayupdotdev.ecodim.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lessons")
data class LessonEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val content: String,
    val updatedAt: Long,
    val isFavorite: Boolean = false
)