package net.lab0.kotlinexplorer.framework.presentation.components.code

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle

interface CodeStyle<H> {
  val backgroundColor: Color
  val gutterBackgroundColor: Color
  val placeholderBackgroundColor: Color
  val activePlaceholderBackgroundColor: Color

  val foregroundColor: Color
  val gutterForegroundColor: Color
  val placeholderForegroundColor: Color
  val activePlaceholderForegroundColor: Color

  val textStyler: (H) -> SpanStyle
}
