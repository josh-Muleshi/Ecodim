package cd.wayupdotdev.ecodim.features.favorite

import cd.wayupdotdev.ecodim.core.domain.model.Lesson

sealed class FavoriteUiState {
    data object Loading : FavoriteUiState()
    data class Success(val lessons: List<Lesson>) : FavoriteUiState()
    data class Error(val message: String) : FavoriteUiState()
}
