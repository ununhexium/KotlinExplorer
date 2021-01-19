package com.example.jetpackcomposeexplorer.presentation.components.code

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeexplorer.presentation.theme.sourceCodeFontFamily
import com.example.jetpackcomposeexplorer.ui.frame.DefaultVerticalSpacer

@Composable
fun Monospace(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
) {
  Text(
      text,
      modifier = modifier,
      fontFamily = sourceCodeFontFamily,
      fontSize = TextUnit.Companion.Sp(14),
      softWrap = false,
  )
}

@Composable
fun Monospace(
    text: String,
    modifier: Modifier = Modifier,
) {
  Monospace(
      AnnotatedString(text),
      modifier
  )
}

@Preview
@Composable
fun MonospacePreview() {
  MaterialTheme {
    Surface {
      Column {
        Monospace("Foo")
        DefaultVerticalSpacer()
        Monospace(
            buildAnnotatedString {
              pushStyle(SpanStyle(color = Color.Blue))
              append("F")
              pushStyle(SpanStyle(color = Color.White))
              append("o")
              pushStyle(SpanStyle(color = Color.Red))
              append("o")
            }
        )
        DefaultVerticalSpacer()
        Text("Foo", fontFamily = FontFamily.Monospace)
      }
    }
  }
}