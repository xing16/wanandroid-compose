package com.xing.playandroid.ui.components

import android.text.TextUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.xing.playandroid.entity.Article

@Composable
fun ArticleImageTextItem(
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    article: Article,
    onItemClick: (Article) -> Unit = {}
) {
    Column(modifier = Modifier
        .clickable {
            onItemClick(article)
        }
        .padding(15.dp)) {
        Row(
            modifier = modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            Column(
                modifier = modifier
                    .height(90.dp)
                    .weight(1f)
            ) {
                Text(
                    modifier = childModifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    text = article.title,
                    style = MaterialTheme.typography.h2,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                if (!TextUtils.isEmpty(article.desc)) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        modifier = childModifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        text = article.desc ?: "",
                        style = MaterialTheme.typography.body1,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Spacer(modifier = Modifier.width(6.dp))
            Image(
                modifier = childModifier
                    .height(100.dp)
                    .aspectRatio(5 / 6f)
                    .background(color = Color(0xffdddddd)),
                painter = rememberCoilPainter(
                    request = article.envelopePic,
                    fadeIn = true
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = childModifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = "作者: ",
                style = MaterialTheme.typography.caption
            )
            Text(text = if (TextUtils.isEmpty(article.author)) article.shareUser ?: "-" else article.author ?: "-", style = MaterialTheme.typography.body2)
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