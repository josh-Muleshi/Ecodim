package cd.wayupdotdev.ecodim.features.comments

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cd.wayupdotdev.ecodim.R
import cd.wayupdotdev.ecodim.core.domain.model.Comment
import org.koin.androidx.compose.koinViewModel
import java.util.Date
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentScreen(
    onBackBtnClicked: () -> Unit,
    viewModel: CommentViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val userInfo by viewModel.userInfo.collectAsState()
    var newComment by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var userName by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    val comments = when (uiState) {
        is CommentUiState.Success -> (uiState as CommentUiState.Success).comments
        else -> emptyList()
    }

    LaunchedEffect(userInfo) {
        val (name, id) = userInfo
        if (name == null || id == null) {
            showDialog = true
        } else {
            userName = name
        }
    }


    LaunchedEffect(comments.size) {
        if (comments.isNotEmpty()) {
            listState.animateScrollToItem(comments.lastIndex)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Commentaires") },
                navigationIcon = {
                    IconButton(onClick = onBackBtnClicked) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                    }
                },
                actions = {
                    UserNamePopupExample(
                        showDialog = showDialog,
                        onShowDialogChange = { showDialog = it },
                        userName = userName,
                        onUserNameChange = { userName = it },
                        onConfirm = {
                            viewModel.getOrCreateUser(userName)
                            showDialog = false
                        }
                    )
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 8.dp)
            ) {
                items(comments) { comment ->
                    MessageCard(
                        author = if (comment.userId == userInfo.second) "Moi" else comment.userName,
                        message = comment.text
                    )
                }
            }

            // Champ actif uniquement si user est défini
            if (userInfo.first != null && userInfo.second != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = newComment,
                        onValueChange = { newComment = it },
                        placeholder = { Text("Saisir un commentaire...") },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        shape = RoundedCornerShape(50.dp),
                        colors = TextFieldDefaults.colors(
                            cursorColor = MaterialTheme.colorScheme.onSurface,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        textStyle = LocalTextStyle.current.copy(fontSize = 16.sp)
                    )

                    Button(
                        onClick = {
                            if (newComment.isNotBlank()) {
                                val comment = Comment(
                                    uid = UUID.randomUUID().toString(),
                                    userId = userInfo.second ?: "Inconnu",
                                    userName = userInfo.first ?: "",
                                    text = newComment,
                                    createdAt = Date()
                                )
                                viewModel.sendComment(comment)
                                newComment = ""
                            }
                        },
                        modifier = Modifier.size(48.dp),
                        shape = CircleShape,
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Envoyer", tint = Color.White)
                    }
                }
            }
        }
    }
}


@Composable
fun MessageCard(author: String, message: String) {
    val isUserMe = author == "Moi"
    var isExpanded by remember { mutableStateOf(false) }

    val surfaceColor by animateColorAsState(
        if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        label = ""
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = if (isUserMe) Arrangement.End else Arrangement.Start
    ) {
        if (!isUserMe) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

        Column(
            horizontalAlignment = if (isUserMe) Alignment.End else Alignment.Start,
            modifier = Modifier
                .clickable { isExpanded = !isExpanded }
                .widthIn(max = 280.dp)
        ) {
            if (!isUserMe) {
                Text(author, style = MaterialTheme.typography.titleSmall)
            }

            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = if (isUserMe) MaterialTheme.colorScheme.primary else surfaceColor,
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    message,
                    modifier = Modifier.padding(8.dp),
                    color = if (isUserMe) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun UserNamePopupExample(
    showDialog: Boolean,
    onShowDialogChange: (Boolean) -> Unit,
    userName: String,
    onUserNameChange: (String) -> Unit,
    onConfirm: () -> Unit
) {

    IconButton(onClick = { onShowDialogChange(true) }) {
        Icon(imageVector = Icons.Default.Person, contentDescription = null)
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { /* Ne rien faire ici si obligatoire */ },
            title = { Text(text = "Entrez votre nom") },
            text = {
                TextField(
                    value = userName,
                    onValueChange = { onUserNameChange(it) },
                    placeholder = { Text("Nom d'utilisateur") }
                )
            },
            confirmButton = {
                TextButton(onClick = onConfirm) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { onShowDialogChange(false) }) {
                    Text("Annuler")
                }
            }
        )
    }
}

