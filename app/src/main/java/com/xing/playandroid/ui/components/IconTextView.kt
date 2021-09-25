package com.xing.playandroid.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IconTextView(icon: ImageVector, title: String, onClick: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .clickable {
                onClick()
            }
            .padding(horizontal = 13.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(color = Color.Black.copy(alpha = 0.09f))

        ) {
            Icon(imageVector = icon, contentDescription = "IconTextView icon")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = title, fontSize = 14.sp)
    }
}