package com.xing.wancompose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xing.wancompose.entity.Article

@Composable
fun ArticleItemView(article: Article) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
        Text(
            text = article.title,
            maxLines = 3,
            fontSize = 16.sp,
            overflow = TextOverflow.Ellipsis
        )
        Row {
            Text(text = article.shareUser?:"")
            Text(text = article.niceShareDate)
        }
    }
}