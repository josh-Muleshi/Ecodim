package cd.wayupdotdev.ecodim.features.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.wayupdotdev.ecodim.core.domain.repository.LessonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val lessonRepository: LessonRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<FavoriteUiState>(FavoriteUiState.Loading)
    val uiState: StateFlow<FavoriteUiState> = _uiState.asStateFlow()

    init {
        fetchFavoriteLessons()
    }

    private fun fetchFavoriteLessons() {
        viewModelScope.launch {
            _uiState.value = FavoriteUiState.Loading
            try {
                lessonRepository.getFavoriteLessons().collect { lessons ->
                    _uiState.value = FavoriteUiState.Success(lessons)
                }
            } catch (e: Exception) {
                _uiState.value = FavoriteUiState.Error(e.message ?: "Erreur inconnue")
            }
        }
    }
}
