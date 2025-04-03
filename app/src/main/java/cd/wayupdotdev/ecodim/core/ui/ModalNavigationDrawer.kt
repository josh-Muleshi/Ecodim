package cd.wayupdotdev.ecodim.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Comment
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cd.wayupdotdev.ecodim.R

@Composable
fun AppDrawer(
    onCloseDrawer: () -> Unit,
    onMenuItemClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f)
    ) {
        DrawerContent(
            onCloseDrawer = onCloseDrawer,
            onMenuItemClick = onMenuItemClick
        )
    }
}


@Composable
fun DrawerContent(
    onCloseDrawer: () -> Unit,
    onMenuItemClick: (String) -> Unit
) {
    val menuItems = listOf(
        "Accueil" to Icons.Outlined.Home,
        "Communautés" to Icons.AutoMirrored.Outlined.Comment,
        "Favoris" to Icons.Outlined.StarOutline,
        "Recherche" to Icons.Outlined.Search
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(70.dp)
                    .padding(6.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Ecodim",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "jmuleshi2@gmail.com",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        HorizontalDivider()

        menuItems.forEach { (text, icon) ->
            NavigationDrawerItem(
                label = { Text(text, style = MaterialTheme.typography.bodyLarge) },
                selected = false,
                icon = { Icon(icon, contentDescription = null) },
                onClick = {
                    onMenuItemClick(text)
                    onCloseDrawer()
                },
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Color.Transparent
                ),
                shape = RoundedCornerShape(0.dp)
            )
        }

        HorizontalDivider()

        NavigationDrawerItem(
            label = { Text("Paramètres", style = MaterialTheme.typography.bodyLarge) },
            selected = false,
            icon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
            badge = { Text("4") },
            onClick = {
                onMenuItemClick("Paramètres")
                onCloseDrawer()
            },
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.Transparent
            ),
            shape = RoundedCornerShape(0.dp)
        )

        NavigationDrawerItem(
            label = { Text("Aide & Support", style = MaterialTheme.typography.bodyLarge) },
            selected = false,
            icon = { Icon(Icons.AutoMirrored.Outlined.Help, contentDescription = null) },
            onClick = {
                onMenuItemClick("Aide & Support")
                onCloseDrawer()
            },
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.Transparent
            ),
            shape = RoundedCornerShape(0.dp)
        )
        Spacer(Modifier.height(12.dp))
    }
}

@Preview
@Composable
private fun ModalPrev() {
    AppDrawer(
        onCloseDrawer = {},
        onMenuItemClick = {}
    )
}