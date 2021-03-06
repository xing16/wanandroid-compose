package com.xing.playandroid.ui.components

import android.text.TextUtils
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xing.playandroid.entity.Article

@Composable
fun ArticleTextItem(
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    article: Article,
    onItemClick: (article: Article) -> Unit = {}
) {
    Column(modifier = Modifier
        .clickable {
            onItemClick(article)
        }
        .padding(13.dp)
    ) {
        Text(
            modifier = childModifier
                .fillMaxWidth()
                .wrapContentHeight(),
            text = article.title,
            style = MaterialTheme.typography.h2
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = childModifier
                .wrapContentSize()
        ) {
            Text(
                text = "作者: ",
                style = MaterialTheme.typography.caption
            )
            Text(text = if (TextUtils.isEmpty(article.author)) article.shareUser ?: "-" else article.author ?: "-", style = MaterialTheme.typography.caption)
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "分类: ",
                style = MaterialTheme.typography.caption
            )
            Text(text = article.chapterName ?: "-", style = MaterialTheme.typography.caption)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "时间: ", style = MaterialTheme.typography.caption)
            Text(text = article.niceShareDate ?: "-", style = MaterialTheme.typography.caption)
        }
    }
}
