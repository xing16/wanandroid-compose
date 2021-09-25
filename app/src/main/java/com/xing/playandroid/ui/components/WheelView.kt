package com.xing.playandroid.ui.components

import android.graphics.Path
import android.graphics.RectF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.min

@Composable
fun WheelView(modifier: Modifier = Modifier) {
    val list = listOf(20, 70)
    val context = LocalContext.current
    Canvas(modifier = modifier.fillMaxSize(), onDraw = {
        val width = size.width
        val height = size.height
        val radius = min(width, height) / 2f
        val circleStroke = 70f
        translate(width / 2f, height / 2f) {
            val paint = Paint().apply {
                color = Color.Red
            }
            drawCircle(
                brush = Brush.linearGradient(colors = listOf(Color.Red, Color.White, Color(0xffFF9800))),
                radius = radius - circleStroke / 2f,
                center = Offset.Zero,
                style = Stroke(width = circleStroke)
            )
            for (index in 0 until 24) {
                rotate(degrees = index * 15f, pivot = Offset.Zero) {
                    drawCircle(
                        color = Color(0xffFF9800),
                        radius = 16f,
                        center = Offset(x = 0f, y = radius - circleStroke / 2f),
                        style = Fill
                    )
                }
            }
            val sum = list.sumOf {
                it
            }
            var startAngle = 0f
            val colors = arrayListOf(Color(0xFFE91E63), Color(0xffFFA722))
            list.forEachIndexed { index, value ->
                val sweepAngle = value * 1.0f / sum * 360
                drawArc(
                    color = colors[index % colors.size],
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = true,
                    size = Size(
                        width = radius * 2 - 2 * circleStroke,
                        height = radius * 2 - 2 * circleStroke
                    ),
                    topLeft = Offset(circleStroke - radius, circleStroke - radius)
                )
                val nativePaint = android.graphics.Paint().apply {
                    textSize = 18.dp.toPx()
                    color = android.graphics.Color.BLUE
                }
                val path = Path()
                path.addArc(RectF(-radius * 2 / 3, -radius * 2 / 3, radius * 2 / 3, radius * 2 / 3), 0f, 90f)
                drawContext.canvas.nativeCanvas.drawTextOnPath(
                    "在家撸码",
                    path,
                    60f,
                    0f,
                    nativePaint
                )


//                drawLine(
//                    color = Color.Red,
//                    start = Offset(0f, 0f),
//                    end = Offset(0f, radius - circleStroke),
//                    strokeWidth = 2f
//                )
                startAngle += sweepAngle
            }

            drawCircle(color = Color.Red, radius = radius / 5, center = Offset.Zero)

        }


    })
}


@Preview(showBackground = true)
@Composable
fun ccc() {
    WheelView(modifier = Modifier.fillMaxSize())
}