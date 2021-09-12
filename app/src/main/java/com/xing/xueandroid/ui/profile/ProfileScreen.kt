package com.xing.xueandroid.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.xing.xueandroid.R
import com.xing.xueandroid.ui.Screen

@Composable
fun ProfileScreen(navController: NavHostController) {

    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = null,
        modifier = Modifier
            .height(200.dp)
            .clickable {
                navController.navigate(Screen.Wheel.route)
            },
        contentScale = ContentScale.Crop,

        )


}