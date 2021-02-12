package net.lab0.jetpackcomposeexplorer.framework.ui.theme

import androidx.compose.ui.graphics.Color
import kotlin.random.Random


val colors = listOf(
    Color.Black,
    Color.Red,
    Color.Green,
    Color.Blue,
    Color.Cyan,
    Color.Magenta,
    Color.Yellow,
    Color.Gray,
)

fun randomColor() =
    colors[Random.nextInt(colors.size)]

val IntRange.length: Int
  get() = this.last - this.first + 1

