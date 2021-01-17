package com.example.jetpackcomposeexplorer.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.ui.frame.DefaultVerticalSpacer
import com.example.jetpackcomposeexplorer.ui.frame.KotlinCode
import com.example.jetpackcomposeexplorer.ui.frame.placeholder


@Composable
fun CodeQuestion(question:String, code:String) {
  Column(modifier = Modifier.fillMaxWidth()) {
    Text(question)
    DefaultVerticalSpacer()
    KotlinCode(code = code)
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
            code = """println("${placeholder()}")""",
        )
      }
    }
  }
}