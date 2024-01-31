package com.example.project

import KonwersacjeScreen
import UstawieniaScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch


class ProjectViewModel(private val repository: KonwersacjeRepository) : ViewModel() {

    val konwersacjeList: Flow<List<Konwersacje>> = repository.getAll()
    val selectedValue = MutableStateFlow(0L)
    val konwersacjeRelationList = repository.getAllListy().combine(selectedValue) { listy, id ->
        listy.find { it.konwersacje.uid == id }
    }

    fun insertKonwersacje(konwersacje: Konwersacje) {
        viewModelScope.launch {
            repository.insert(konwersacje)
        }
    }

    fun deleteKonwersacje(konwersacje: Konwersacje) {
        viewModelScope.launch {
            repository.delete(konwersacje)
        }
    }

    fun getKonwersacje(uid: Long) {
        viewModelScope.launch {
            repository.getById(uid)
        }
    }


    fun updateKonwersacje(konwersacje: Konwersacje) {
        viewModelScope.launch {
            repository.update(konwersacje)
            viewModelScope.launch {
                repository.update(konwersacje)
            }
        }
    }

    fun setSelectedValue(value: Long) {
        selectedValue.value = value
    }

    fun getAllListy(): Flow<List<KonwersacjeRelation>> {

        return repository.getAllListy()
    }

    fun insertlista1(value: List<lista1>) {
        viewModelScope.launch {
            repository.insertlista1(value)
        }
    }

    fun insertlista2(value: List<lista2>) {
        viewModelScope.launch {
            repository.insertlista2(value)
        }
    }

}

class ProjectViewModelFactory(private val repository: KonwersacjeRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProjectViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProjectViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

enum class ProjectScreen {
    First,
    Second,
    Third,
}


@Composable
fun ProjectApp() {
    val navController = rememberNavController()

    val database = KonwersacjeDb.getInstance(LocalContext.current)
    val repository = KonwersacjeRepository(database.KonwersacjeDAO(), database.ListyDAO())

    val viewModel: ProjectViewModel = viewModel(
        factory = ProjectViewModelFactory(repository),
        key = "ProjectViewModelKey"
    )

    NavHost(
        navController = navController,
        startDestination = ProjectScreen.First.name
    ) {
        composable(route = ProjectScreen.First.name) {
            SejwyScreen(navController, viewModel)
        }
        composable(route = ProjectScreen.Second.name) {
            KonwersacjeScreen(navController, viewModel)
        }
        composable(route = ProjectScreen.Third.name) {
            UstawieniaScreen(navController, viewModel)
        }
    }
}