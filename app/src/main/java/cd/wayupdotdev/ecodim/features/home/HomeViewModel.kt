package cd.wayupdotdev.ecodim.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.wayupdotdev.ecodim.core.data.model.Lesson
import cd.wayupdotdev.ecodim.core.domain.repository.LessonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val lessonRepository: LessonRepository
) : ViewModel() {

    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    val lessons: List<Lesson>
        get() = (homeUiState.value as? HomeUiState.Success)?.lessons ?: emptyList()

    init {
        viewModelScope.launch {
            lessonRepository.getAll()
                .catch { e ->
                    _homeUiState.value = HomeUiState.Error(e.message.orEmpty())
                }
                .collect { lesson ->
                    _homeUiState.value = lesson?.let { HomeUiState.Success(it) }!!
                }
        }
    }
}