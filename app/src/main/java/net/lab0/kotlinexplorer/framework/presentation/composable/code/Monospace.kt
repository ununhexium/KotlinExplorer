package net.lab0.kotlinexplorer.framework.presentation.composable.code

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.sp
import net.lab0.kotlinexplorer.framework.presentation.composable.DefaultVerticalSpacer
import net.lab0.kotlinexplorer.framework.ui.theme.sourceCodeFontFamily

@Composable
fun Monospace(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
) {
  Text(
      text,
      modifier = modifier,
      fontFamily = sourceCodeFontFamily,
      fontSize = 14.sp,
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
        Monospace("Wii")
        DefaultVerticalSpacer()
        Monospace(
            buildAnnotatedString {
              pushStyle(SpanStyle(color = Color.Blue))
              append("W")
              pushStyle(SpanStyle(color = Color.White))
              append("i")
              pushStyle(SpanStyle(color = Color.Red))
              append("i")
            }
        )
        DefaultVerticalSpacer()
        Text("Wii", fontFamily = FontFamily.Monospace)
      }
    }
  }
}