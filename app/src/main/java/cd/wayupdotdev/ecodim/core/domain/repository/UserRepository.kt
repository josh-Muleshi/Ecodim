package cd.wayupdotdev.ecodim.core.domain.repository

import cd.wayupdotdev.ecodim.core.domain.model.User

interface UserRepository {
    suspend fun getOrCreateCurrentUser(author: String): User
}
