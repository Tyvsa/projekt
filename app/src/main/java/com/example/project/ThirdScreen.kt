import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.example.project.Konwersacje
import com.example.project.ProjectScreen
import com.example.project.ProjectViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun UstawieniaScreen(
    navController: NavHostController, viewModel: ProjectViewModel
) {
    var newImie1 by remember { mutableStateOf("") }
    var newImie2 by remember { mutableStateOf("") }
    var newNazwa by remember { mutableStateOf("") }

    val selectedValue by viewModel.selectedValue.collectAsState()

    Log.d("Prosze", "Selected Value: $selectedValue")

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
                    navController.navigate(ProjectScreen.Second.name)
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
                    viewModel.updateKonwersacje(
                        Konwersacje(
                            uid = selectedValue,
                            imie1 = newImie1,
                            imie2 = newImie2,
                            nazwa = newNazwa
                        )
                    )
                    newImie1 = ""
                    newImie2 = ""
                    newNazwa = ""
                    navController.navigate(ProjectScreen.Second.name)
                },
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text("Zapisz")
                    Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                }
            }
        }

        TextField(
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

        TextField(
            value = newImie1,
            onValueChange = { newImie1 = it },
            label = { Text("Imię postaci 1") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        TextField(
            value = newImie2,
            onValueChange = { newImie2 = it },
            label = { Text("Imię postaci 2") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Button(
            onClick = {
                viewModel.deleteKonwersacje(
                    Konwersacje(
                        uid = selectedValue,
                        imie1 = newImie1,
                        imie2 = newImie2,
                        nazwa = newNazwa
                    )
                )
                navController.navigate(ProjectScreen.First.name)
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Usuń")
        }

        Button(
            onClick = {
                // przycisk do zapisu
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Wyeksportuj")
        }
    }
}
