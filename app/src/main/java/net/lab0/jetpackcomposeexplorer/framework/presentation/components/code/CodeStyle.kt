package net.lab0.jetpackcomposeexplorer.framework.presentation.components.code

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle

interface CodeStyle<H> {
  val backgroundColor: Color
  val gutterBackgroundColor: Color
  val placeholderBackgroundColor: Color

  val foregroundColor: Color
  val gutterForegroundColor: Color
  val placeholderForegroundColor: Color

  val textStyler: (H) -> SpanStyle
}