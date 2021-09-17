package com.xing.playandroid.ui.wheel

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xing.playandroid.ui.components.WheelView

@Composable
fun WheelScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()


//            .paint(
//                painter = painterResource(id = R.drawable.bg_wheel),
//                contentScale = ContentScale.FillBounds
//            )
    ) {


        WheelView(
            modifier = Modifier
                .width(300.dp)
                .height(300.dp)

        )

    }
}