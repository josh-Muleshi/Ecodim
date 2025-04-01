package cd.wayupdotdev.ecodim.features.topicDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.jeziellago.compose.markdowntext.MarkdownText
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicDetailScreen(
    topicUid: String,
    modifier: Modifier = Modifier,
    onBackBtnClicked: () -> Unit,
    onFavoriteBtnClicked: () -> Unit,
    viewModel: TopicDetailViewModel = koinViewModel()
) {
    val data by viewModel.data.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.getTopicDetail(topicUid)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                },
                navigationIcon = {
                    IconButton(onClick = onBackBtnClicked) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                    }
                },
                actions = {
                    IconButton(onClick = onFavoriteBtnClicked) {
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = "Ajouter au panier"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        if (data is TopicDetailUiState.Success) {
            val lesson = (data as TopicDetailUiState.Success).lesson
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                MarkdownText(
                    modifier = Modifier.padding(8.dp),
                    markdown = lesson.content,
                    style = TextStyle(
                        color = Color.Blue,
                        fontSize = 12.sp,
                        lineHeight = 10.sp,
                        textAlign = TextAlign.Justify,
                    ),
                )
            }
        }
    }
}