package cd.wayupdotdev.ecodim.core.data.repository

import cd.wayupdotdev.ecodim.core.data.remote.model.RemoteComment
import cd.wayupdotdev.ecodim.core.data.remote.model.RemoteLesson
import cd.wayupdotdev.ecodim.core.data.remote.model.toDomain
import cd.wayupdotdev.ecodim.core.domain.model.Comment
import cd.wayupdotdev.ecodim.core.domain.repository.CommentRepository
import cd.wayupdotdev.ecodim.core.utils.FireBaseConstants
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class CommentRepositoryImpl(
    private val firestore: FirebaseFirestore
) : CommentRepository {

    override fun getAllComment(): Flow<List<Comment>?> = callbackFlow {
        val listenerRegistration = firestore.collection(FireBaseConstants.comments)
            .orderBy(RemoteComment::createdAt.name, Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val remoteComment = snapshot?.toObjects(RemoteComment::class.java).orEmpty()
                val comments = remoteComment.mapNotNull { it.toDomain() }

                trySend(comments)
            }

        awaitClose { listenerRegistration.remove() }
    }

    override suspend fun sendComment(comment: Comment) {
        firestore.collection(FireBaseConstants.comments)
            .document(comment.uid)
            .set(comment)
            .await()
    }

    override suspend fun deleteComment(idComment: String) {
        firestore.collection(FireBaseConstants.comments)
            .document(idComment)
            .delete()
            .await()
    }
}
