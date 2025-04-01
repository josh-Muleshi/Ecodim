package cd.wayupdotdev.ecodim.features.topicDetail

import cd.wayupdotdev.ecodim.core.data.model.Lesson

sealed interface TopicDetailUiState {
    data object Uninitialized: TopicDetailUiState
    data object Loading: TopicDetailUiState
    data class Success(val lesson: Lesson): TopicDetailUiState
    data class Error(val message: String): TopicDetailUiState
}
