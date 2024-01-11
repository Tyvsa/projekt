import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.project.ProjectScreen


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun KonwersacjeScreen(
    navController: NavHostController
) {
    var messageText by remember { mutableStateOf("") }
    var leftConversationList by remember { mutableStateOf<List<String>>(emptyList()) }
    var rightConversationList by remember { mutableStateOf<List<String>>(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    navController.navigate(ProjectScreen.First.name)
                },
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    Text("Wróć")
                }
            }

            Button(
                onClick = {
                    navController.navigate(ProjectScreen.Third.name)
                },
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text("Ustawienia")
                    Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .weight(0.6f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                // Left Conversation List
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .weight(1f)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        items(leftConversationList) { message ->
                            Text(text = message, modifier = Modifier.padding(16.dp))
                        }
                    }
                }

                // Right Conversation List
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .weight(1f)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        items(rightConversationList) { message ->
                            Text(text = message, modifier = Modifier.padding(16.dp))
                        }
                    }
                }
            }
        }

        Card {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    placeholder = { Text("Wpisz wiadomość...") },
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            if (messageText.isNotBlank()) {
                                // Add logic for determining which list to update based on user action
                                // For now, let's assume it always goes to the left list
                                leftConversationList = leftConversationList + messageText
                                messageText = ""
                            }
                        }
                    )
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    if (messageText.isNotBlank()) {
                        leftConversationList = leftConversationList + messageText
                        messageText = ""
                    }
                },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Wyślij po lewej")
            }

            Button(
                onClick = {
                    if (messageText.isNotBlank()) {
                        rightConversationList = rightConversationList + messageText
                        messageText = ""
                    }
                },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Wyślij po prawej")
            }
        }
    }
}
