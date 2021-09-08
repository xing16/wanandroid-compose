package com.xing.xueandroid.ui.components

import android.text.TextUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.xing.xueandroid.R
import com.xing.xueandroid.entity.Article
import com.xing.xueandroid.ui.theme.XueandroidTheme

@Composable
fun ArticleImageTextItem(modifier: Modifier = Modifier, childModifier: Modifier = Modifier, article: Article) {
    Row(
        modifier = modifier
            .padding(15.dp)
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .height(90.dp)
                .weight(1f)
        ) {
            Text(
                modifier = childModifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = article.title,
                style = MaterialTheme.typography.h1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            if (!TextUtils.isEmpty(article.desc)) {
                Text(
                    modifier = childModifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    text = article.desc ?: "",
                    style = MaterialTheme.typography.body1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row {
                Text(
                    modifier = childModifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    text = article.author ?: article.shareUser ?: "",
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.secondary,
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    modifier = childModifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    text = article.niceShareDate ?: "today",
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.onSecondary
                )
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Image(
            modifier = childModifier
                .width(120.dp)
                .height(90.dp),
            painter = rememberCoilPainter(
                request = article.envelopePic,
                fadeIn = true
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}


@Preview(showBackground = true)
@Composable
fun cccc() {
    ArticleImageTextItem(
        modifier = Modifier,
        childModifier = Modifier.placeholder(
            visible = true,
            highlight = PlaceholderHighlight.shimmer()
        ),
        Article(title = "使用Jetpack Compose Theme为app轻松换肤", desc = "casdcasd")
    )
}