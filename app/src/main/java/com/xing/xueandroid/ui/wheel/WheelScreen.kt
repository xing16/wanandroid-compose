package com.xing.xueandroid.ui.wheel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.xing.xueandroid.R
import com.xing.xueandroid.ui.components.WheelView

@Composable
fun WheelScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
            }

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