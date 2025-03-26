package cd.wayupdotdev.ecodim.features.comments

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CommentScreen(
    onBackClick: () -> Unit,
    darkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit
){
    Text(text = "Setting Screen")
}