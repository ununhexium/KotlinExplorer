package com.example.jetpackcomposeexplorer.framework.presentation.theme

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import com.example.jetpackcomposeexplorer.R

private val sourceCodePro = fontFamily(
    font(R.font.sourcecodepro_medium, FontWeight.Medium)
)

var sourceCodeFontFamily = sourceCodePro
