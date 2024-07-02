package cd.wayupdotdev.ecodim.features.setting

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SettingScreen(
    onBackClick: () -> Unit,
    darkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit
){
    Text(text = "Setting Screen")
}