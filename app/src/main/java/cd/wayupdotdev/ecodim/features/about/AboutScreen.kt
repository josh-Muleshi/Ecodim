package cd.wayupdotdev.ecodim.features.about

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AboutScreen(
    onBackClick: () -> Unit,
    darkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit
){
    Text(text = "Setting Screen")
}