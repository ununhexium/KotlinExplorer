package com.example.jetpackcomposeexplorer.framework.presentation.components.code

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle

interface CodeStyle<H> {
  val backgroundColor: Color
  val foregroundColor: Color
  val textStyler: (H) -> SpanStyle
}
