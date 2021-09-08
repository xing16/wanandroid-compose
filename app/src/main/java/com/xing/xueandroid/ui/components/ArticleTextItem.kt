package com.xing.xueandroid.ui.components

import android.text.TextUtils
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xing.xueandroid.entity.Article

@Composable
fun ArticleTextItem(modifier: Modifier = Modifier, childModifier: Modifier = Modifier, article: Article) {
    Column(modifier = Modifier.padding(13.dp)) {
        Text(
            modifier = childModifier
                .fillMaxWidth()
                .wrapContentHeight(),
            text = article.title,
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = childModifier
                .wrapContentSize()
        ) {
            Text(
                text = "作者: ",
                style = MaterialTheme.typography.body2
            )
            Text(text = if (TextUtils.isEmpty(article.author)) article.shareUser ?: "-" else article.author ?: "-", style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "分类: ",
                style = MaterialTheme.typography.body2
            )
            Text(text = article.chapterName ?: "-", style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "时间: ", style = MaterialTheme.typography.body2)
            Text(text = article.niceShareDate ?: "-", style = MaterialTheme.typography.body2)
        }
    }
}
