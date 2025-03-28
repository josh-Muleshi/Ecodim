package cd.wayupdotdev.ecodim.features.home

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cd.wayupdotdev.ecodim.R
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onSearchBtnClicked: () -> Unit,
    onShopCardBtnClicked: () -> Unit,
    onProductItemClicked: (String) -> Unit,
    onCategoryClicked: () -> Unit,
    onSettingClicked: () -> Unit,
    onProfileClicked: () -> Unit,
   // viewModel: HomeViewModel = koinViewModel()
) {
    //val homeUiState by viewModel.homeUiState.collectAsState()
    val context = LocalContext.current

    BackHandler {
        (context as? Activity)?.finish()
    }

    val scrollState = rememberScrollState()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                drawerShape = RoundedCornerShape(0.dp)
            ) {
                DrawerContent(
                    onCloseDrawer = {
                        coroutineScope.launch { drawerState.close() }
                    },
                    onMenuItemClick = { menuItem ->
                        when (menuItem) {
                            "Accueil" -> println("Navigation vers Accueil")
                            "Catégories" -> onCategoryClicked()
                            "Panier" -> onShopCardBtnClicked()
                            "Recherche" -> onSearchBtnClicked()
                            "Profil" -> onProfileClicked()
                            "Paramètres" -> onSettingClicked()
                            "Aide & Support" -> println("Navigation vers Aide & Support")
                        }
                    }
                )
            }
        }
    ) {
        Scaffold(
            modifier = modifier,
            topBar = {
                TopAppBar(
                    modifier = Modifier,
                    title = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.avd_eco_orange_anim),
                                contentDescription = "logo",
                                modifier = Modifier.size(70.dp)
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                coroutineScope.launch { drawerState.open() }
                            },
                            modifier = modifier,
                            colors = IconButtonDefaults.filledIconButtonColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = null
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = { onProfileClicked() },
                            modifier = modifier,
                            colors = IconButtonDefaults.filledIconButtonColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .wrapContentHeight()
                    .verticalScroll(scrollState)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 8.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(4.dp))
                            .border(
                                1.dp,
                                MaterialTheme.colorScheme.outline,
                                RoundedCornerShape(4.dp)
                            )
                            .background(MaterialTheme.colorScheme.surface)
                            .clickable { onSearchBtnClicked() }
                            .padding(16.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = "Search",
                                tint = Color.Gray
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Rechercher...",
                                color = Color.Gray,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }

                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.surface)
                ) {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                        border = BorderStroke(0.5.dp, color = MaterialTheme.colorScheme.outline),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column (
                            modifier = Modifier
                                .clickable { onProductItemClicked("") }
                                .padding(24.dp)
                        ){
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Top
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "Une chaussure unique",
                                        style = MaterialTheme.typography.headlineSmall,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Text(
                                        text = "Nike Air Max 270",
                                        modifier = Modifier.padding(top = 8.dp),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }

                                Image(
                                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                    contentDescription = "Nike Dunk",
                                    modifier = Modifier
                                        .size(150.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Voir plus",
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = "Flèche",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductCard(onProductItemClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .width(130.dp)
            .height(200.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onProductItemClicked() }
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
//            SubcomposeAsyncImage(
//                modifier = Modifier
//                    .size(100.dp)
//                    .clip(RoundedCornerShape(8.dp)),
//                model = product.imageUrl,
//                contentDescription = null,
//                contentScale = ContentScale.Crop
//            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "",
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
//            Text(text = "${product.devise} ${product.price}", color = Color.Gray)
        }
    }
}

@Composable
fun DrawerContent(
    onCloseDrawer: () -> Unit,
    onMenuItemClick: (String) -> Unit
) {
    val menuItems = listOf(
        "Accueil" to Icons.Outlined.Home,
        "Catégories" to Icons.Outlined.Category,
        "Panier" to Icons.Outlined.ShoppingCart,
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

        NavigationDrawerItem(
            label = { Text("Profil", style = MaterialTheme.typography.bodyLarge) },
            selected = false,
            icon = { Icon(Icons.Outlined.PersonOutline, contentDescription = null) },
            onClick = {
                onMenuItemClick("Profil")
                onCloseDrawer()
            },
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.Transparent
            ),
            shape = RoundedCornerShape(0.dp)
        )

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
private fun HomePrev() {
    HomeScreen(
        onSearchBtnClicked = {},
        onShopCardBtnClicked = {},
        onProductItemClicked = {},
        onCategoryClicked = {},
        onSettingClicked = {},
        onProfileClicked = {}
    )
}