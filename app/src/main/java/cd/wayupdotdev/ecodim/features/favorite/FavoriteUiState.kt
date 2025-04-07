package cd.wayupdotdev.ecodim.features.favorite

import cd.wayupdotdev.ecodim.core.data.local.entity.LessonEntity

sealed class FavoriteUiState {
    data object Loading : FavoriteUiState()
    data class Success(val lessons: List<LessonEntity>) : FavoriteUiState()
    data class Error(val message: String) : FavoriteUiState()
}
