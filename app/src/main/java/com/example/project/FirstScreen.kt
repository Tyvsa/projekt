package com.example.project

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

data class Sejw(val imie1: String, val imie2: String, val nazwa: String)

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SejwyScreen(
    navController: NavHostController, viewModel: ProjectViewModel
) {
    var isAddingNewSejw by remember { mutableStateOf(false) }
    var newImie1 by remember { mutableStateOf("") }
    var newImie2 by remember { mutableStateOf("") }
    var newNazwa by remember { mutableStateOf("") }

    val selectedValue by viewModel.selectedValue.collectAsState()
    val sejwyList by viewModel.konwersacjeList.collectAsState(initial = emptyList())

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
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text("Dodaj nową konwersację")
                }
            }
        }

        // Nowa konwersacja
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
                    imeAction = ImeAction.Done, keyboardType = KeyboardType.Text
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
                    }, shape = RoundedCornerShape(8.dp)
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
                        viewModel.insertKonwersacje(
                            Konwersacje(
                                imie1 = newImie1, imie2 = newImie2, nazwa = newNazwa
                            )
                        )
                        newImie1 = ""
                        newImie2 = ""
                        newNazwa = ""
                        isAddingNewSejw = false
                    }, shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text("Dodaj konwersację")
                    }
                }
            }
        } else {
            //sejwy aka lisa
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
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(sejwyList) { konwersacje ->
                            SejwItem(sejw = Sejw(
                                imie1 = konwersacje.imie1,
                                imie2 = konwersacje.imie2,
                                nazwa = konwersacje.nazwa
                            ), onClick = {
                                navController.navigate(ProjectScreen.Second.name)
                                viewModel.setSelectedValue(konwersacje.uid)
                                Log.d(
                                    "ViewModel",
                                    "Zapisano wartość w ViewModel: $konwersacje.nazwa"
                                )
                            })
                        }
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
        Column {
            Text(text = sejw.imie1)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = sejw.imie2)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = sejw.nazwa, modifier = Modifier.weight(1f)
        )
    }
}
