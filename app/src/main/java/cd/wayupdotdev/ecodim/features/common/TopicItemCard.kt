package cd.wayupdotdev.ecodim.features.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cd.wayupdotdev.ecodim.R
import cd.wayupdotdev.ecodim.core.domain.model.Lesson
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun TopicCard(lesson: Lesson, onTopicItemClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onTopicItemClicked() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
        border = BorderStroke(0.5.dp, color = MaterialTheme.colorScheme.outline),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            MarkdownText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                markdown = lesson.content,
                maxLines = 10,
                fontResource = R.font.montserrat_medium,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 12.sp,
                    lineHeight = 10.sp,
                    textAlign = TextAlign.Justify,
                ),
            )

            if (lesson.week) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(topStart = 15.dp, bottomStart = 15.dp, bottomEnd = 15.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Semaine",
                        color = Color.White,
                        fontSize = 10.sp,
                    )
                }
            }
        }
    }
}
