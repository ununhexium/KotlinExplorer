package com.example.jetpackcomposeexplorer.ui

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
