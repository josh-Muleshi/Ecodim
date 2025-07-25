package cd.wayupdotdev.ecodim.features.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.wayupdotdev.ecodim.core.data_preferences.PreferenceManager
import cd.wayupdotdev.ecodim.core.domain.model.Comment
import cd.wayupdotdev.ecodim.core.domain.repository.CommentRepository
import cd.wayupdotdev.ecodim.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CommentViewModel(
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    // ────────────────────────────
    // UI STATE
    // ────────────────────────────
    private val _uiState = MutableStateFlow<CommentUiState>(CommentUiState.Loading)
    val uiState: StateFlow<CommentUiState> = _uiState.asStateFlow()

    private val _userInfo = MutableStateFlow<Pair<String?, String?>>(null to null)
    val userInfo: StateFlow<Pair<String?, String?>> = _userInfo.asStateFlow()

    // ────────────────────────────
    // INIT
    // ────────────────────────────
    init {
        getAllComments()
    }

    // ────────────────────────────
    // USER MANAGEMENT
    // ────────────────────────────
    private val _shouldAskUserName = MutableStateFlow(false)
    val shouldAskUserName: StateFlow<Boolean> = _shouldAskUserName.asStateFlow()

    fun initializeUser() {
        viewModelScope.launch {
            val savedUid = preferenceManager.getUserId()
            val savedName = preferenceManager.getUsername()

            if (savedUid != null && savedName != null) {
                _userInfo.value = savedName to savedUid
            } else {
                _shouldAskUserName.value = true
            }
        }
    }

    fun saveUser(userNameInput: String) {
        viewModelScope.launch {
            val user = userRepository.getOrCreateCurrentUser(userNameInput)
            preferenceManager.saveUserId(user.uid)
            preferenceManager.saveUsername(user.author)
            _userInfo.value = user.author to user.uid
            _shouldAskUserName.value = false
        }
    }

    fun cancelUserPrompt() {
        _shouldAskUserName.value = false
    }

    // ────────────────────────────
    // COMMENT OPERATIONS
    // ────────────────────────────
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
