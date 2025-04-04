package cd.wayupdotdev.ecodim.features.home

import cd.wayupdotdev.ecodim.core.data.remote.model.Lesson

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(val lessons: List<Lesson>) : HomeUiState
    data class Error(val errorMessage: String) : HomeUiState
}