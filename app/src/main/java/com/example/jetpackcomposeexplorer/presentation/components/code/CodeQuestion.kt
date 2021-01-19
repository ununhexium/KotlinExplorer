package com.example.jetpackcomposeexplorer.presentation.components.code

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.model.KotlinCodeWithBlanks.Companion.placeholder
import com.example.jetpackcomposeexplorer.model.code.DefaultCodeStyle
import com.example.jetpackcomposeexplorer.model.code.extractHighlightsAndAnnotate
import com.example.jetpackcomposeexplorer.ui.frame.DefaultVerticalSpacer


@Composable
fun CodeQuestion(question: String, code: String) {
  Column(modifier = Modifier.fillMaxWidth()) {
    Text(question)
    DefaultVerticalSpacer()
    KotlinCode(
        code = extractHighlightsAndAnnotate(code, DefaultCodeStyle.textStyler),
        foregroundColor = DefaultCodeStyle.foregroundColor,
        backgroundColor = DefaultCodeStyle.backgroundColor
    )
  }
}

@Preview
@Composable
fun CodeQuestionPreview() {
  MaterialTheme {
    Surface {
      Column {
        CodeQuestion(
            question = "Why did the chicken cross the road?",
            code = """
              |fun main(){
              |  println("${placeholder()}")
              |}
            """.trimMargin(),
        )
      }
    }
  }
}
