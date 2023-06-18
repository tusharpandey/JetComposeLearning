package com.example.jetcomposelearning

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun appNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomePage(navController = navController) }
        composable(
            "friendDetail/{userName}",
            arguments = listOf(navArgument("userName") {
                type = NavType.StringType
                defaultValue = ""
            })
        ) {
            var userName = it.arguments?.getString("userName") ?: ""
            FriendDetail(navController = navController, userName = userName)
        }
//        composable("friendDetail") { FriendDetail(navController = navController) }
    }
}