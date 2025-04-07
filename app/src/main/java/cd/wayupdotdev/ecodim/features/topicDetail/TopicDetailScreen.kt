package cd.wayupdotdev.ecodim.features.topicDetail

import android.util.Log
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cd.wayupdotdev.ecodim.R
import dev.jeziellago.compose.markdowntext.MarkdownText
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicDetailScreen(
    topicUid: String,
    modifier: Modifier = Modifier,
    onBackBtnClicked: () -> Unit,
    viewModel: TopicDetailViewModel = koinViewModel()
) {
    val data by viewModel.data.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.getTopicDetail(topicUid)
    }

    val scale = remember { mutableFloatStateOf(1f) }
    val offsetX = remember { mutableFloatStateOf(0f) }
    val offsetY = remember { mutableFloatStateOf(0f) }

    val state = rememberTransformableState { zoomChange, offsetChange, _ ->
        val newScale = (scale.floatValue * zoomChange).coerceAtLeast(1f)

        val maxOffsetX = (offsetChange.x * newScale).coerceIn(-500f, 500f)
        val maxOffsetY = (offsetChange.y * newScale).coerceIn(-500f, 500f)

        scale.floatValue = newScale
        offsetX.value += maxOffsetX
        offsetY.value += maxOffsetY
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackBtnClicked) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                    }
                },
                actions = {
                    if (data is TopicDetailUiState.Success) {
                        val lesson = (data as TopicDetailUiState.Success).lesson
                        val isFavorite = (data as TopicDetailUiState.Success).isFavorite

                        IconButton(onClick = {
                            viewModel.updateFavorite(lesson.id, !isFavorite)
                            Log.w("favorisAdd", "${ isFavorite }")
                        }) {
                            Icon(
                                imageVector = if (isFavorite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                                tint = if (isFavorite) Color.Red else Color.Gray,
                                contentDescription = if (isFavorite) "Retirer des favoris" else "Ajouter aux favoris"
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        if (data is TopicDetailUiState.Success) {
            val lesson = (data as TopicDetailUiState.Success).lesson
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .transformable(state = state)
                    .graphicsLayer(
                        scaleX = scale.floatValue,
                        scaleY = scale.floatValue,
                        translationX = offsetX.floatValue,
                        translationY = offsetY.floatValue
                    )
            ) {
                MarkdownText(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    markdown = lesson.content,
                    fontResource = R.font.montserrat_medium,
                    style = TextStyle(
                        color = Color.Blue,
                        fontSize = 14.sp,
                        lineHeight = 16.sp,
                        textAlign = TextAlign.Justify,
                    ),
                )
            }
        }
    }
}
