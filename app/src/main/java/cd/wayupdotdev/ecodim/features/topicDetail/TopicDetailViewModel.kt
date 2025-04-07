package cd.wayupdotdev.ecodim.features.topicDetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.wayupdotdev.ecodim.core.domain.repository.LessonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TopicDetailViewModel(
    private val lessonRepository: LessonRepository
) : ViewModel() {
    private val _data = MutableStateFlow<TopicDetailUiState>(TopicDetailUiState.Uninitialized)
    val data: StateFlow<TopicDetailUiState> = _data

    fun getTopicDetail(topicUid: String) = viewModelScope.launch {
        _data.value = TopicDetailUiState.Loading
        try {
            lessonRepository.getLessonByUid(topicUid).collect { lesson ->
                lesson?.let {
                    _data.value = TopicDetailUiState.Success(it, it.isFavorite)
                } ?: run {
                    _data.value = TopicDetailUiState.Error("Leçon non trouvée.")
                }
            }
        } catch (e: Exception) {
            _data.value = TopicDetailUiState.Error(e.message.toString())
        }
    }

    fun updateFavorite(lessonId: String, isFavorite: Boolean) = viewModelScope.launch {
        lessonRepository.updateFavorite(lessonId, isFavorite)
        Log.w("favorisView", "${ lessonId } ${ isFavorite }")
        val currentState = _data.value
        if (currentState is TopicDetailUiState.Success) {
            _data.value = currentState.copy(isFavorite = isFavorite)
        }
    }
}
