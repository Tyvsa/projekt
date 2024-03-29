import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.project.ProjectScreen
import com.example.project.ProjectViewModel
import com.example.project.lista1
import com.example.project.lista2


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun KonwersacjeScreen(
    navController: NavHostController,
    viewModel: ProjectViewModel
) {
    var messageText by remember { mutableStateOf("") }


    val listy by remember { viewModel.konwersacjeRelationList }
        .collectAsState(initial = null)

    var leftConversationList by remember {
        mutableStateOf<List<String>>(listy?.list1?.map { it.tresc } ?: emptyList())
    }
    var rightConversationList by remember {
        mutableStateOf<List<String>>(listy?.list2?.map { it.tresc } ?: emptyList())
    }

    val selectedValue by viewModel.selectedValue.collectAsState()

    val imiona by remember { viewModel.konwersacjeList }
        .collectAsState(initial = emptyList())


    val selectedImiona = imiona.firstOrNull { it.uid == selectedValue }

    val imie1 = selectedImiona?.imie1 ?: ""
    val imie2 = selectedImiona?.imie2 ?: ""

    leftConversationList = listy?.list1?.map { it.tresc } ?: emptyList()
    rightConversationList = listy?.list2?.map { it.tresc } ?: emptyList()


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

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .weight(0.6f)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Wyświetlanie lewej
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .weight(1f)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            leftConversationList.forEach { message ->
                                Text(
                                    text = message,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }

                    // Wyświetlanie prawej
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .weight(1f)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            rightConversationList.forEach { message ->
                                Text(
                                    text = message,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Przyciski z imionami
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    if (messageText.isNotBlank()) {
                        leftConversationList = leftConversationList + messageText
                        messageText = ""
                        val spaces = " ".repeat(messageText.length)
                        rightConversationList = rightConversationList + spaces
                        val lastMessage = leftConversationList.lastOrNull() ?: ""
                        viewModel.insertlista1(
                            listOf(
                                lista1(
                                    konwersacjaid = selectedValue.toInt(),
                                    tresc = lastMessage
                                )
                            )
                        )
                        viewModel.insertlista2(
                            listOf(
                                lista2(
                                    konwersacjaid = selectedValue.toInt(),
                                    tresc = spaces
                                )
                            )
                        )
                    }
                },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("$imie1")
            }

            Button(
                onClick = {
                    if (messageText.isNotBlank()) {
                        rightConversationList = rightConversationList + messageText
                        messageText = ""
                        val spaces = " ".repeat(messageText.length)
                        leftConversationList = leftConversationList + spaces
                        val lastMessage = rightConversationList.lastOrNull() ?: ""
                        viewModel.insertlista2(
                            listOf(
                                lista2(
                                    konwersacjaid = selectedValue.toInt(),
                                    tresc = lastMessage
                                )
                            )
                        )
                        viewModel.insertlista1(
                            listOf(
                                lista1(
                                    konwersacjaid = selectedValue.toInt(),
                                    tresc = spaces
                                )
                            )
                        )
                    }
                },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("$imie2")
            }
        }

        // Pole tekstowe
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
                        leftConversationList = leftConversationList + messageText
                        messageText = ""
                    }
                }
            )
        )
    }
}
