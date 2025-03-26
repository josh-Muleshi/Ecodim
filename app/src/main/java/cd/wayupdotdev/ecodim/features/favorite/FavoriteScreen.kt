package cd.wayupdotdev.ecodim.features.favorite

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun FavoriteScreen(
    onBackClick: () -> Unit,
    darkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit
){
    Text(text = "Setting Screen")
}