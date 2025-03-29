package cd.wayupdotdev.ecodim.features.comments

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentScreen(
    onBackClick: () -> Unit,
    darkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit
){
        // Liste de messages (peut être remplie par des données dynamiques)
        val messages = remember { mutableStateListOf<String>() }
        var newMessage by remember { mutableStateOf("") }
        val listState = rememberLazyListState()

        // Ajout de messages simulés
        LaunchedEffect(messages.size) {
            // Défilement automatique vers le dernier message
            listState.animateScrollToItem(messages.size)
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Commentaires", style = MaterialTheme.typography.bodyLarge) },
                )
            }
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                // Liste des messages
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(bottom = 8.dp)
                ) {
                    items(messages) { message ->
                        // Affichage des messages avec animation
                        AnimatedVisibility(
                            visible = true,
                            enter = slideInVertically(initialOffsetY = { -40 }),
                            exit = slideOutVertically(targetOffsetY = { 40 })
                        ) {
                            Card(
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = message,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }
                }

                // Champ de saisie de message et bouton "Envoyer"
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = newMessage,
                        onValueChange = { newMessage = it },
                        placeholder = { Text("Saisir un commentaire...") },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        maxLines = 1
                    )

                    Button(
                        onClick = {
                            if (newMessage.isNotBlank()) {
                                messages.add(newMessage)
                                newMessage = "" // Effacer le champ après envoi
                            }
                        },
                        shape = RoundedCornerShape(50),

                    ) {
                        Icon(Icons.Filled.Send, contentDescription = "Envoyer")
                    }
                }
            }
        }
    }
