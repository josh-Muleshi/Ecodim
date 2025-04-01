package cd.wayupdotdev.ecodim.features.topicDetail

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
                _data.value = lesson?.let { TopicDetailUiState.Success(it) }!!
            }
        } catch (e: Exception) {
            _data.value = TopicDetailUiState.Error(e.message.toString())
        }
    }
}