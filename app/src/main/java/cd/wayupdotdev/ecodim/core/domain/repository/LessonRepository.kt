package cd.wayupdotdev.ecodim.core.domain.repository

import cd.wayupdotdev.ecodim.core.data.model.Lesson
import kotlinx.coroutines.flow.Flow

interface LessonRepository {
    fun getAll(): Flow<List<Lesson>?>
    fun getLessonByUid(uidLesson: String): Flow<Lesson?>
}