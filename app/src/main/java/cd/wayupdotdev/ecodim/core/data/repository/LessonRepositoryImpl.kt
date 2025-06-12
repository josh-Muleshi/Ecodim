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
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LessonRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val lessonDao: LessonDao
) : LessonRepository {

    override fun getAll(): Flow<List<Lesson>> = channelFlow {
        // Émettre les données locales immédiatement
        lessonDao.getAllLessons()
            .map { it.map { entity -> entity.toDomain() } }
            .onEach { send(it) }
            .launchIn(this)

        // Ensuite on écoute Firestore
        val listener = firestore.collection(FireBaseConstants.LESSONS)
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
            }

        awaitClose {
            listener.remove()
        }
    }.flowOn(Dispatchers.IO)

    private suspend fun updateLocalDatabase(remoteLessons: List<Lesson>) {
        val localLessons = lessonDao.getAllLessons().firstOrNull().orEmpty()

        val remoteUids = remoteLessons.map { it.uid }.toSet()
        val localUids = localLessons.map { it.id }

        // Trouver les leçons locales qui ne sont plus dans Firestore
        val toDeleteUids = localUids.filterNot { it in remoteUids }

        if (toDeleteUids.isNotEmpty()) {
            lessonDao.deleteLessonsByUids(toDeleteUids)
        }

        // Mise à jour des leçons existantes ou ajout de nouvelles
        val updatedEntities = remoteLessons.mapNotNull { lesson ->
            val localEntity = localLessons.find { it.id == lesson.uid }

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
