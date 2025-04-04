package cd.wayupdotdev.ecodim.core.domain.repository

import cd.wayupdotdev.ecodim.core.data.remote.model.Lesson
import cd.wayupdotdev.ecodim.core.data.local.entity.LessonEntity
import kotlinx.coroutines.flow.Flow

interface LessonRepository {
    fun getAll(): Flow<List<Lesson>?>
    fun getLessonByUid(uidLesson: String): Flow<LessonEntity?>
    fun getFavoriteLessons(): Flow<List<LessonEntity>>
    suspend fun updateFavorite(lessonId: String, isFavorite: Boolean)
}