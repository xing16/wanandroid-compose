package com.xing.playandroid.ui.square

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun SquareScreen(appNavController: NavHostController) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "first_screen") {
        composable("first_screen") {
            FirstScreen(appNavController)
        }
        composable("second_screen") {
            SecondScreen(appNavController)
        }
    }
}

@Composable
fun FirstScreen(navController: NavHostController) {
    Button(onClick = {
        navController.navigate("second_screen")
    }) {
        Text(text = "second screen")
    }
}

@Composable
fun SecondScreen(
    navController: NavHostController
) {
    Scaffold {
        Box(
            modifier = Modifier
                .background(Color.Red)
                .fillMaxSize()
        ) {
            Text(text = "second_screen")
        }
    }

}
