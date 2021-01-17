package com.example.jetpackcomposeexplorer.ui.tutorial

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.model.KotlinCodeWithBlanks.Companion.placeholder
import com.example.jetpackcomposeexplorer.presentation.components.KotlinCode
import com.example.jetpackcomposeexplorer.ui.frame.*
import java.util.Locale
import kotlin.random.Random

@Composable
fun TutorialPage2(name: String?, userAnswer: (Boolean) -> Unit) {
  val sourceCode = """
      |println("Hello ${placeholder(0)}!");
    """.trimMargin()

  Column {
    Text("In this app, you will answer code-related questions. Here is an example:")
    DefaultVerticalSpacer()
    KotlinCode(sourceCode)
    DefaultVerticalSpacer()
    Text(text = "Now select your name:")
    DefaultVerticalSpacer()

    Row(Modifier.align(Alignment.CenterHorizontally)) {
      CodeButton(
          text = name ?: "",
          onClick = {
            userAnswer(true)
          }
      )

      DefaultHorizontalSpacer()

      CodeButton(
          text = (0..6)
              .map {
                val codePoint = Random.nextInt('a'.toInt(), 'z'.toInt())
                codePoint.toChar()
              }
              .joinToString(separator = "")
              .capitalize(Locale.getDefault()),
          onClick = {
            userAnswer(false)
          }
      )
    }
  }
}

@Preview
@Composable
fun PreviewTutorialPage2() {
  MaterialTheme {
    Surface {
      TutorialPage2("Foo") {}
    }
  }
}
