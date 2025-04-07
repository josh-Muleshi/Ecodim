package cd.wayupdotdev.ecodim.core.data.repository

import cd.wayupdotdev.ecodim.core.data.local.dao.LessonDao
import cd.wayupdotdev.ecodim.core.data.local.entity.LessonEntity
import cd.wayupdotdev.ecodim.core.data.local.entity.toDomain
import cd.wayupdotdev.ecodim.core.data.local.entity.toEntity
import cd.wayupdotdev.ecodim.core.data.remote.model.RemoteLesson
import cd.wayupdotdev.ecodim.core.data.remote.model.toDomain
import cd.wayupdotdev.ecodim.core.domain.model.Lesson
import cd.wayupdotdev.ecodim.core.domain.repository.LessonRepository
import cd.wayupdotdev.ecodim.core.utils.FireBaseConstants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class LessonRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val lessonDao: LessonDao
) : LessonRepository {

    override fun getAll(): Flow<List<Lesson>?> = lessonDao.getAllLessons()
        .flatMapLatest {
            callbackFlow {
                val listener = firestore.collection(FireBaseConstants.lesson)
                    .orderBy(RemoteLesson::createdAt.name, Query.Direction.DESCENDING)
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            close(error)
                            return@addSnapshotListener
                        }

                        val remoteLessons = value?.toObjects(RemoteLesson::class.java).orEmpty()
                        val lessons = remoteLessons.mapNotNull { it.toDomain() }

                        launch(Dispatchers.IO) {
                            updateLocalDatabase(lessons)
                        }

                        trySend(lessons).isSuccess
                    }

                awaitClose { listener.remove() }
            }
        }.flowOn(Dispatchers.IO)

    private suspend fun updateLocalDatabase(lessons: List<Lesson>) {
        val updatedEntities = lessons.mapNotNull { lesson ->
            val localEntity = lessonDao.getLessonByUid(lesson.uid).firstOrNull()

            if (localEntity != null && localEntity.updatedAt >= lesson.updatedAt.time) {
                return@mapNotNull null
            }

            lesson.toEntity(isFavorite = localEntity?.isFavorite ?: false)
        }

        if (updatedEntities.isNotEmpty()) {
            lessonDao.insertLessons(updatedEntities)
        }
    }

    override fun getLessonByUid(uidLesson: String): Flow<LessonEntity?> =
        lessonDao.getLessonByUid(uidLesson)

    override fun getFavoriteLessons(): Flow<List<Lesson>> =
        lessonDao.getFavoriteLessons().map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun updateFavorite(lessonId: String, isFavorite: Boolean) {
        lessonDao.updateFavorite(lessonId, isFavorite)
    }
}
