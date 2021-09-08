package com.xing.xueandroid.ui.square

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

@Composable
fun SquareScreen(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "first_screen") {
        composable("first_screen") {
            FirstScreen(navController)
        }
        composable("second_screen") {
            SecondScreen(navController)
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
