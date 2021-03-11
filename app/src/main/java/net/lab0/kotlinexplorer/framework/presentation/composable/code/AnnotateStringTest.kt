package net.lab0.kotlinexplorer.framework.presentation.composable.code

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnnotateStringTest() {
  Text(
    buildAnnotatedString {
      pushStyle(SpanStyle(color = Color.Red, background = Color.Black))
      append("foo")
      pop()
      addStyle(SpanStyle(color = Color.Black, background = Color.Red), 0, 3)
    }
  )
}

@Preview
@Composable
fun AnnotateStringTestPreview() {
  MaterialTheme {
    Surface(
      color = Color(0xFF4CAF50)
    ) {
      Column(
        modifier = Modifier.padding(20.dp)
      ) {
        Surface(
          color = MaterialTheme.colors.surface
        ) {
          AnnotateStringTest()
        }
      }
    }
  }
}