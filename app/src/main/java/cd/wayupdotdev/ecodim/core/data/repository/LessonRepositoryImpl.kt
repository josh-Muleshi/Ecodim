package cd.wayupdotdev.ecodim.core.data.repository

import cd.wayupdotdev.ecodim.core.data.model.Lesson
import cd.wayupdotdev.ecodim.core.domain.repository.LessonRepository
import cd.wayupdotdev.ecodim.core.utils.FireBaseConstants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

class LessonRepositoryImpl(
    private val firestore: FirebaseFirestore
) : LessonRepository {

    override fun getAll(): Flow<List<Lesson>?> = callbackFlow {
        val listener = firestore.collection(FireBaseConstants.lesson)
            .orderBy(Lesson::createdAt.name, Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val products = value?.toObjects(Lesson::class.java).orEmpty()
                trySend(products).isSuccess
            }

        awaitClose { listener.remove() }
    }.flowOn(Dispatchers.IO)

    override fun getLessonByUid(uidLesson: String): Flow<Lesson?> = callbackFlow {
        val docRef = firestore.document("${FireBaseConstants.lesson}/$uidLesson")

        val listener = docRef.addSnapshotListener { value, error ->
            when {
                error != null -> close(error)
                value != null -> value.toObject<Lesson>()?.let { trySend(it) }
                else -> close(IllegalArgumentException("Document is null"))
            }
        }

        awaitClose { listener.remove() }
    }.catch { throwable ->
        throw throwable
    }.flowOn(Dispatchers.IO)
}