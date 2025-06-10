package cd.wayupdotdev.ecodim.features.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.wayupdotdev.ecodim.core.domain.model.Comment
import cd.wayupdotdev.ecodim.core.domain.repository.CommentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CommentViewModel(
    private val commentRepository: CommentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CommentUiState>(CommentUiState.Loading)
    val uiState: StateFlow<CommentUiState> = _uiState.asStateFlow()

    init {
        getAllComments()
    }

    private fun getAllComments() {
        viewModelScope.launch {
            commentRepository.getAllComment()
                .catch { e ->
                    _uiState.value = CommentUiState.Error(e.message ?: "Erreur inconnue")
                }
                .collect { comments ->
                    _uiState.value = CommentUiState.Success(comments ?: emptyList())
                }
        }
    }

    fun sendComment(comment: Comment) {
        viewModelScope.launch {
            try {
                commentRepository.sendComment(comment)
            } catch (e: Exception) {
                _uiState.value = CommentUiState.Error(e.message ?: "Erreur lors de l'envoi")
            }
        }
    }

    fun deleteComment(commentId: String) {
        viewModelScope.launch {
            try {
                commentRepository.deleteComment(commentId)
            } catch (e: Exception) {
                _uiState.value = CommentUiState.Error(e.message ?: "Erreur lors de la suppression")
            }
        }
    }
}
