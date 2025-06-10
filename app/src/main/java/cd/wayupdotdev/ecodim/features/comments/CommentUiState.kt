package cd.wayupdotdev.ecodim.features.comments

import cd.wayupdotdev.ecodim.core.domain.model.Comment

sealed interface CommentUiState {
    data object Loading : CommentUiState
    data class Success(val comments: List<Comment>) : CommentUiState
    data class Error(val message: String) : CommentUiState
}
