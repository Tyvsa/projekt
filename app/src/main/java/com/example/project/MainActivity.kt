package com.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.project.ui.theme.ProjectTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SejwyScreen()
                }
            }
        }
    }
}

// Dodana klasa do przechowywania danych sejwów
data class Sejw(val imie1: String, val imie2: String, val nazwa: String)

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SejwyScreen() {
    var selectedSejw by remember { mutableStateOf<Sejw?>(null) }
    var newImie1 by remember { mutableStateOf("") }
    var newImie2 by remember { mutableStateOf("") }
    var newNazwa by remember { mutableStateOf("") }
    var isAddingNewSejw by remember { mutableStateOf(false) }

    val sejwyList = remember { mutableStateListOf<Sejw>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        // Przycisk do dodawania nowej konwersacji
        if (!isAddingNewSejw) {
            Button(
                onClick = {
                    isAddingNewSejw = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(50.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text("Dodaj nową konwersację")
                }
            }
        }

        // Nowa konwersacja - wyświetlana tylko gdy isAddingNewSejw jest true
        if (isAddingNewSejw) {
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = newImie1,
                onValueChange = { newImie1 = it },
                label = { Text("Imię postaci 1") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = newImie2,
                onValueChange = { newImie2 = it },
                label = { Text("Imię postaci 2") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = newNazwa,
                onValueChange = { newNazwa = it },
                label = { Text("Nazwa zapisu") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        isAddingNewSejw = false
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text("Anuluj")
                    }
                }

                Button(
                    onClick = {
                        sejwyList.add(Sejw(newImie1, newImie2, newNazwa))
                        newImie1 = ""
                        newImie2 = ""
                        newNazwa = ""
                        isAddingNewSejw = false
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text("Dodaj konwersację")
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(sejwyList) { sejw ->
                        SejwItem(
                            sejw = sejw,
                            onClick = { selectedSejw = sejw }
                        )
                    }
                }
            }
        }

        }
    }


@Composable
fun SejwItem(sejw: Sejw, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Lewa kolumna
        Column {
            Text(text = sejw.imie1)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = sejw.imie2)
        }

        // Prawa kolumna
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = sejw.nazwa,
            modifier = Modifier.weight(1f)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SejwyListPreview() {
    ProjectTheme {
        SejwyScreen()
    }
}
