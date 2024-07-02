package cd.wayupdotdev.ecodim.features.home

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun HomeScreen(
    onTopicClick: (String) -> Unit,
    //onSettingClick: () -> Unit
) {
    val context = LocalContext.current

    BackHandler {
        (context as? Activity)?.finish()
    }
    
    Text(text = "Start Screen")
}