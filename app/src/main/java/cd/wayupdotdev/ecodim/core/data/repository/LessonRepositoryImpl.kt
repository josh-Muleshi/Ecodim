package cd.wayupdotdev.ecodim.core.data.repository

import cd.wayupdotdev.ecodim.core.data.local.dao.LessonDao
import cd.wayupdotdev.ecodim.core.data.local.entity.LessonEntity
import cd.wayupdotdev.ecodim.core.data.remote.model.Lesson
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
import kotlinx.coroutines.launch

class LessonRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val lessonDao: LessonDao
) : LessonRepository {

    override fun getAll(): Flow<List<Lesson>> = lessonDao.getAllLessons()
        .flatMapLatest {
            callbackFlow {
                val listener = firestore.collection(FireBaseConstants.lesson)
                    .orderBy(Lesson::createdAt.name, Query.Direction.DESCENDING)
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            close(error)
                            return@addSnapshotListener
                        }

                        val lessons = value?.toObjects(Lesson::class.java).orEmpty()

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
            lesson.createdAt?.let { createdAt ->
                val localEntity = lessonDao.getLessonByUid(lesson.uid).firstOrNull()

                // Ne rien faire si la version distante n’est pas plus récente
                if (localEntity != null && localEntity.updatedAt >= createdAt.time) {
                    return@let null
                }

                LessonEntity(
                    id = lesson.uid,
                    userId = lesson.userId,
                    content = lesson.content,
                    updatedAt = createdAt.time,
                    isFavorite = localEntity?.isFavorite ?: false // Garde la valeur locale
                )
            }
        }.filterNotNull()

        if (updatedEntities.isNotEmpty()) {
            lessonDao.insertLessons(updatedEntities)
        }
    }

    override fun getLessonByUid(uidLesson: String): Flow<LessonEntity?> =
        lessonDao.getLessonByUid(uidLesson)

    override fun getFavoriteLessons(): Flow<List<LessonEntity>> =
        lessonDao.getFavoriteLessons()

    override suspend fun updateFavorite(lessonId: String, isFavorite: Boolean) {
        lessonDao.updateFavorite(lessonId, isFavorite)
    }
}
