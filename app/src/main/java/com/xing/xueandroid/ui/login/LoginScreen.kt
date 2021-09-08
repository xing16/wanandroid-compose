package com.xing.xueandroid.ui.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xing.xueandroid.ui.components.CleanableTextField

@Preview
@Composable
fun LoginScreen() {
    val isPasswordLogin = remember {
        mutableStateOf(false)
    }
    Column {
        LoginHeader(isPasswordLogin)
        LoginTitle(isPasswordLogin)
        LoginForm(isPasswordLogin)
        LoginThirdSection()
    }
}

@Composable
fun LoginThirdSection() {


}

@Composable
fun LoginForm(isPasswordLogin: MutableState<Boolean>) {
    val enableLogin = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
    ) {
        if (isPasswordLogin.value) {
            LoginPassword(enableLogin)
        } else {
            LoginVerifyCode(enableLogin)
        }
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
                .clip(RoundedCornerShape(23.dp)),
            enabled = enableLogin.value
        ) {
            Text(text = "登录")
        }

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "忘记密码？",
            modifier = Modifier
                .clickable { }
                .align(alignment = Alignment.End)
                .padding(10.dp)
        )
    }
}


@Composable
fun LoginTitle(isPasswordLogin: MutableState<Boolean>) {
    Text(
        text = if (isPasswordLogin.value) "密码登录" else "验证码登录",
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(
            top = 70.dp,
            start = 20.dp,
            end = 15.dp,
            bottom = 30.dp
        )
    )
}

@Composable
fun LoginHeader(isPasswordLogin: MutableState<Boolean>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Icon(
            Icons.Filled.Close,
            contentDescription = null,
            modifier = Modifier.padding(15.dp)
        )
        Text(
            text = if (isPasswordLogin.value) "验证码登录" else "密码登录",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(15.dp)
                .clickable {
                    isPasswordLogin.value = !isPasswordLogin.value
                }
        )
    }
}

@Composable
fun LoginPassword(enableLogin: MutableState<Boolean>) {
    val username = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val passwordVisible = remember {
        mutableStateOf(false)
    }
    enableLogin.value = username.value.isNotEmpty() && password.value.isNotEmpty()
    Column {
        CleanableTextField(value = username.value,
            onValueChange = { username.value = it },
            placeholder = "用户名",
            leadingIconVector = Icons.Filled.Person,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(20.dp)
                        .clickable {
                            username.value = ""
                        }
                )
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        CleanableTextField(
            value = password.value,
            onValueChange = { password.value = it },
            placeholder = "密码",
            leadingIconVector = Icons.Filled.Lock,
            trailingIcon = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(10.dp)
                            .size(20.dp)
                            .clickable {
                                password.value = ""
                            }
                    )
                    Divider(
                        modifier = Modifier
                            .height(20.dp)
                            .width(1.dp)
                    )

                    Icon(
                        imageVector = if (passwordVisible.value) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(10.dp)
                            .size(20.dp)
                            .clickable {
                                passwordVisible.value = !passwordVisible.value
                            }
                    )
                }
            },
            isPassword = passwordVisible.value
        )
    }
}


@Composable
fun LoginVerifyCode(enableLogin: MutableState<Boolean>) {
    val phone = remember {
        mutableStateOf("")
    }
    val verifyCode = remember {
        mutableStateOf("")
    }
    enableLogin.value = phone.value.isNotEmpty() && verifyCode.value.isNotEmpty() && verifyCode.value.length == 4
    Column {
        Row {
            TextField(
                value = phone.value,
                onValueChange = {
                    phone.value = it
                },
                modifier = Modifier
                    .height(50.dp)
                    .clip(RoundedCornerShape(topStart = 25.dp, bottomStart = 25.dp))
                    .weight(1f),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.PhoneIphone,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(10.dp)
                            .size(20.dp)
                    )
                },
                placeholder = {
                    Text(text = "手机号码")
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.035f),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
            )
            Button(
                onClick = { },
                modifier = Modifier
                    .height(50.dp)
                    .clip(RoundedCornerShape(topEnd = 25.dp, bottomEnd = 25.dp))
                    .padding(end = 0.dp),
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffeeeeee))
            ) {
                Text(
                    text = "获取验证码",
                    color = Color.Blue
                )
            }
        }

//        CleanableTextField(
//            value = phone.value,
//            onValueChange = {
//                phone.value = it
//            },
//            placeholder = "手机号码",
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Filled.PhoneIphone,
//                    contentDescription = null,
//                    modifier = Modifier
//                        .padding(10.dp)
//                        .size(20.dp)
//                )
//            },
//            trailingIcon = {
//                Button(
//                    onClick = { },
//                    modifier = Modifier
//                        .fillMaxHeight()
//                        .clip(RoundedCornerShape(topEnd = 25.dp, bottomEnd = 25.dp))
//                        .padding(end = 0.dp),
//                    shape = MaterialTheme.shapes.large,
//                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
//                ) {
//                    Text(
//                        text = "获取验证码"
//                    )
//                }
//            }
//        )

        Spacer(modifier = Modifier.height(20.dp))


    }

}







