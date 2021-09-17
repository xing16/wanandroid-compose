package com.xing.playandroid.ui.webview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.xing.playandroid.ui.components.WebView
import java.net.URLDecoder

@Composable
fun WebViewScreen(navController: NavHostController, url: String?) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight
    val primary = MaterialTheme.colors.primary
    val webTitle = remember {
        mutableStateOf("")
    }
    // 设置状态栏颜色透明
    SideEffect {
        systemUiController.setStatusBarColor(primary, darkIcons = useDarkIcons)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.statusBarsPadding(),
                contentPadding = PaddingValues(horizontal = 0.dp)
            ) {
                Box(contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxHeight()
                        .clickable {
                            navController.popBackStack()
//                            systemUiController.setStatusBarColor(Color.Transparent, darkIcons = useDarkIcons)
                        }
                        .padding(horizontal = 15.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "close")
                }
                Text(text = webTitle.value, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    ) {
        url?.let {
            WebView(url = URLDecoder.decode(it)) { title ->
                webTitle.value = title
            }
        }
    }
}