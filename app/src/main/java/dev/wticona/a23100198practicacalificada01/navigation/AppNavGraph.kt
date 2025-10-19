package dev.wticona.a23100198practicacalificada01.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.wticona.a23100198practicacalificada01.presentation.auth.LoginScreen
import dev.wticona.a23100198practicacalificada01.presentation.home.HomeScreen

@Composable
fun AppNavGraph(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login")
    {
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen() }
    }
}
