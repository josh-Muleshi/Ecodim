package cd.wayupdotdev.ecodim.features.topicDetail

import cd.wayupdotdev.ecodim.core.data.local.entity.LessonEntity

sealed interface TopicDetailUiState {
    data object Uninitialized: TopicDetailUiState
    data object Loading: TopicDetailUiState
    data class Success(val lesson: LessonEntity): TopicDetailUiState
    data class Error(val message: String): TopicDetailUiState
}
