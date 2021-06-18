package com.xing.wancompose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ArticleTextItem(text: String, author: String, time: String) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(
            text = "擦手抽筋啊说出来就擦拭擦手抽筋啊说出来就擦拭擦手抽筋啊说出来就擦拭擦手抽筋啊说出来就擦拭擦手抽筋啊说出来就擦拭",
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Text(
                text = "xing星火燎原"
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "2020-01",
            )
        }
    }
}