package com.xing.wancompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.xing.wancompose.R
import com.xing.wancompose.entity.Article

@Composable
fun ArticleImageTextItem(article: Article) {
    Row(
        modifier = Modifier
            .height(90.dp)
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .height(70.dp)
                .weight(1f)
        ) {
            Text(
                text = article.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row {
                Text(
                    text = article.author ?: article.shareUser ?: ""
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = article.niceShareDate
                )
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        Image(
            modifier = Modifier
                .width(90.dp)
                .height(70.dp),
//            painter = rememberCoilPainter(
//                request = article.envelopePic,
//                fadeIn = true
//            ),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )


    }

}