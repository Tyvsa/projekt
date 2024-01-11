package com.example.project

import KonwersacjeScreen
import UstawieniaScreen
import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
enum class ProjectScreen {
    First,
    Second,
    Third,
}


@Composable
fun ProjectApp ()
{
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ProjectScreen.First.name
    ) {
        composable(route = ProjectScreen.First.name)
        {
            SejwyScreen(navController)
        }
        composable(route = ProjectScreen.Second.name)
        {
            KonwersacjeScreen(navController)
        }
        composable(route = ProjectScreen.Third.name)
        {
            UstawieniaScreen(navController)
        }
    }

}


