package cd.wayupdotdev.ecodim.core.domain.repository

import cd.wayupdotdev.ecodim.core.domain.model.Comment
import kotlinx.coroutines.flow.Flow

interface CommentRepository {
    fun getAllComment(): Flow<List<Comment>?>
    suspend fun sendComment(comment: Comment)
    suspend fun deleteComment(idComment: String)
}