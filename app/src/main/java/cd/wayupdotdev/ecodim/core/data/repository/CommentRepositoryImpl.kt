package cd.wayupdotdev.ecodim.core.data.repository

import cd.wayupdotdev.ecodim.core.domain.model.Comment
import cd.wayupdotdev.ecodim.core.domain.repository.CommentRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class CommentRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val commentsCollection: CollectionReference = firestore.collection("comments")
) : CommentRepository {

    override fun getAllComment(): Flow<List<Comment>?> = callbackFlow {
        val listenerRegistration = commentsCollection
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val comments = snapshot?.toObjects(Comment::class.java)
                trySend(comments)
            }

        awaitClose { listenerRegistration.remove() }
    }

    override suspend fun sendComment(comment: Comment) {
        commentsCollection
            .document(comment.uid)
            .set(comment)
            .await()
    }

    override suspend fun deleteComment(idComment: String) {
        commentsCollection
            .document(idComment)
            .delete()
            .await()
    }
}
