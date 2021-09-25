package com.xing.playandroid.ui.webview

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.xing.playandroid.ui.components.IconTextView
import com.xing.playandroid.ui.components.PlayAppBar
import com.xing.playandroid.ui.components.WebView
import com.xing.playandroid.ui.theme.dividerColorDark
import kotlinx.coroutines.launch
import java.net.URLDecoder

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun WebViewScreen(navController: NavHostController, url: String?) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight
    val primary = MaterialTheme.colors.primary
    var webTitle by remember {
        mutableStateOf("")
    }
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    // 设置状态栏颜色透明
    SideEffect {
        systemUiController.setStatusBarColor(primary, darkIcons = useDarkIcons)
    }
    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            PlayAppBar(
                title = webTitle,
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .clickable {
                                navController.popBackStack()
                            }
                            .padding(horizontal = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(imageVector = Icons.Filled.Close, contentDescription = "close")
                    }
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .clickable {
                                coroutineScope.launch {
                                    sheetState.show()
                                }
                            },
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "more")
                    }
                }
            )
        }
    ) {
        ModalBottomSheetLayout(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            sheetState = sheetState,
            sheetShape = RoundedCornerShape(6.dp),
            sheetContent = {
                ModalBottomSheetContent()
            }
        ) {
            url?.let {
                WebView(url = URLDecoder.decode(it)) { title ->
                    webTitle = title
                }
            }
        }
    }
}


@ExperimentalFoundationApi
@Composable
fun ModalBottomSheetContent() {
    val list = listOf(
        Icons.Filled.Favorite to "收藏",
        Icons.Filled.Share to "分享",
        Icons.Filled.ThumbUp to "喜欢",
        Icons.Filled.OpenInBrowser to "浏览器打开",
        Icons.Filled.Refresh to "刷新",
        Icons.Filled.Link to "复制链接",
        Icons.Filled.TextFormat to "文字大小",
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 30.dp),
        verticalArrangement = Arrangement.Center
    ) {
        LazyRow(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(count = list.size) { index: Int ->
                IconTextView(icon = list[index].first, title = list[index].second)
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Divider(startIndent = 20.dp, thickness = 0.5.dp)
        Spacer(modifier = Modifier.height(15.dp))
        LazyRow(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(count = list.size) { index: Int ->
                IconTextView(icon = list[index].first, title = list[index].second) {

                }
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
    }
}



