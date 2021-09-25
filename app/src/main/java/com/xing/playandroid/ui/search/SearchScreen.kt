package com.xing.playandroid.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.xing.playandroid.ui.Screen
import com.xing.playandroid.ui.components.CleanableTextField

@Composable
fun SearchScreen(appNavController: NavHostController) {
    val searchText = remember {
        mutableStateOf("")
    }
    var focused by remember {
        mutableStateOf(false)
    }
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            TopAppBar(
                modifier = Modifier.wrapContentHeight(),
                contentPadding = PaddingValues(
                    start = 13.dp,
                    end = 0.dp,
                    top = 6.dp,
                    bottom = 6.dp
                )
            ) {
                TextField(
                    value = searchText.value,
                    onValueChange = {
//                        text = it
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .padding(0.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .onFocusChanged {
                            focused = it.isFocused
                        },
                    placeholder = {
//                        Text(text = "输入关键字搜索", style = MaterialTheme.typography.body1)
                    },
                    textStyle = TextStyle(fontSize = 12.sp, lineHeight = 30.sp),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {}),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "search",
                            tint = LocalContentColor.current.copy(alpha = 0.5f)
                        )
                    },
                    trailingIcon = {
                        if (searchText.value.isNotEmpty()) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "clear",
                                tint = LocalContentColor.current.copy(alpha = 0.5f),
                                modifier = Modifier.clickable {
                                    searchText.value = ""
                                }
                            )
                        }
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.15f),
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    )
                )
                Button(
                    onClick = {
                        appNavController.popBackStack()
                    }, modifier = Modifier
                        .fillMaxHeight()
                        .background(color = Color.Red)
                ) {
                    Text(
                        text = "取消",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.button
                    )
                }
            }
        }
    ) {
        NavHost(navController = navController, startDestination = Screen.SearchHistory.route) {
            composable(route = Screen.SearchHistory.route) {
                SearchHotHistoryScreen(navController = navController, searchText = searchText)
            }
            composable(
                route = "${Screen.SearchResult.route}/{keyword}",
                arguments = listOf(navArgument("keyword") {
                    type = NavType.StringType
                    nullable = false
                    defaultValue = ""
                })
            ) {
                SearchResultScreen(
                    appNavController = appNavController,
                    keyword = it.arguments?.getString("keyword") ?: "",
                    searchText = searchText
                )
            }
        }
    }
}






