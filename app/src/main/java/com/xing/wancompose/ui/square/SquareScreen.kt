package com.xing.wancompose.ui.square

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.xing.wancompose.R

@Composable
fun SquareScreen() {
    val navController = rememberNavController()
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
fun FirstScreen(navController: NavController) {
    Button(onClick = {
        val navOptions = navOptions {
            anim {
                enter = R.anim.anim_enter
                exit = R.anim.anim_exit
            }
        }
        navController.navigate("second_screen", navOptions = navOptions)
    }) {
        Text(text = "second screen")
    }
}

@Composable
fun SecondScreen(navController: NavController) {
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
