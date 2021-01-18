package com.example.jetpackcomposeexplorer.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeexplorer.R

private val sourceCodePro = fontFamily(
    font(R.font.sourcecodepro_medium, FontWeight.Medium)
)

var sourceCodeFontFamily = sourceCodePro
