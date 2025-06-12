package cd.wayupdotdev.ecodim.core.data.repository

import cd.wayupdotdev.ecodim.core.data.remote.model.RemoteUser
import cd.wayupdotdev.ecodim.core.data.remote.model.toDomain
import cd.wayupdotdev.ecodim.core.domain.model.User
import cd.wayupdotdev.ecodim.core.domain.model.toRemote
import cd.wayupdotdev.ecodim.core.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.Date

class UserRepositoryImpl(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) : UserRepository {

    override suspend fun getOrCreateCurrentUser(author: String): User {
        val currentUser = auth.currentUser ?: auth.signInAnonymously().await().user!!

        val userDoc = firestore.collection("users").document(currentUser.uid)
        val snapshot = userDoc.get().await()

        return if (snapshot.exists()) {
            snapshot.toObject(RemoteUser::class.java)!!.toDomain()
        } else {
            val newUser = User(
                uid = currentUser.uid,
                author = author,
                createdAt = Date()
            )
            userDoc.set(newUser.toRemote()).await()
            newUser
        }
    }
}
