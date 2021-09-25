package com.xing.playandroid

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.xing.playandroid.ui.Screen

@Composable
fun SplashScreen(navController: NavHostController) {
    val scale = remember {
        Animatable(0f)
    }
    var offset by remember {
        mutableStateOf(0.dp)
    }
    val aoaa by animateIntOffsetAsState(targetValue = IntOffset(0, 200))
    LaunchedEffect(key1 = true) {
        scale.animateTo(targetValue = 1.0f, animationSpec = tween(durationMillis = 1000))

//        delay(1000L)
        navController.navigate(Screen.Main.route)
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Spacer(modifier = Modifier.height(200.dp))
        Image(
            imageVector = Icons.Filled.Android,
            contentDescription = "Logo",
            modifier = Modifier
                .size(130.dp)
                .scale(scale.value),
            colorFilter = ColorFilter.tint(color = Color(0xff3ddb86))
        )
    }
}


