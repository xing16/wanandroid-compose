package com.xing.wancompose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CleanableTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String?,
    leadingIconVector: ImageVector,
    trailingIcon: @Composable (() -> Unit)? = null,
    isPassword: Boolean = false,
    onFocusChanged: ((Boolean) -> Unit)? = null
) {
    var hasFocus by remember {
        mutableStateOf(false)
    }
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp))
            .onFocusChanged {
                hasFocus = it == FocusState.Active
                onFocusChanged?.invoke(hasFocus)
            },
        leadingIcon = {
            Icon(
                imageVector = leadingIconVector,
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .size(20.dp),
                tint = if (hasFocus) MaterialTheme.colors.primary else LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
            )
        },
        trailingIcon = if (hasFocus && value.isNotEmpty()) trailingIcon else null,
        singleLine = true,
        placeholder = {
            Text(text = placeholder ?: "")
        },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.035f),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
    )
}