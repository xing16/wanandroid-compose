package com.xing.playandroid.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.ImeOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun VerifyCodeTextField(modifier: Modifier = Modifier, length: Int, onComplete: (String) -> Unit) {
    // 输入框的值
    val text by remember {
        mutableStateOf("")
    }
    val textInputService = LocalTextInputService.current
    val focusRequester by remember {
        mutableStateOf(FocusRequester())
    }
    Canvas(modifier = modifier
        .wrapContentSize()
        .focusRequester(focusRequester)
        .focusModifier()
        .pointerInput("key") {
            detectTapGestures(onTap = {
                focusRequester.requestFocus()

            })
        }
        .onFocusChanged {
            if (it.isFocused) {
                // 开启按键输入
                textInputService?.startInput(
                    value = TextFieldValue(""),
                    // 键盘按键类型
                    imeOptions = ImeOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                    onEditCommand = { commands ->
                        commands.forEach { command ->
                            when (command) {

                            }
                        }
                    },
                    // 右下角按键的事件
                    onImeActionPerformed = { imeAction ->
                        if (imeAction == ImeAction.Done) {
                            textInputService.hideSoftwareKeyboard()
                        }
                    }
                )
            }
        }
    ) {

        // 绘制界面
        drawIntoCanvas { canvas ->
            canvas.translate(-size.width / 2f, 0f)
            val painter = Paint()
            painter.color = Color.Red
            painter.style = PaintingStyle.Fill
            val width = 50.dp.toPx()
            val gap = width / 2f
            for (index in 0 until length) {
                canvas.drawRoundRect(
                    left = (width + gap) * index,
                    top = 0f,
                    right = (index + 1) * width + index * gap,
                    bottom = width,
                    radiusX = 10f,
                    radiusY = 10f,
                    paint = painter
                )
//                canvas.nativeCanvas.drawText("",)
            }


        }

    }


}


@Preview
@Composable
fun cccaa() {
    Canvas(modifier = Modifier.size(700.dp, 300.dp)) {
        drawIntoCanvas { canvas ->
            val painter = Paint()
            painter.color = Color.Red
            painter.style = PaintingStyle.Fill
            val width = 50.dp.toPx()
            val gap = width / 2f
            for (index in 0 until 5) {
                canvas.drawRoundRect(
                    left = (width + gap) * index,
                    top = 0f,
                    right = (index + 1) * width + index * gap,
                    bottom = width,
                    radiusX = 10f,
                    radiusY = 10f,
                    paint = painter
                )
//                canvas.nativeCanvas.drawText("",)
            }


        }
    }
}