package com.example.jetpackcomposeexplorer.ui.tutorial

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.ui.frame.*
import java.util.Locale
import kotlin.random.Random

@Composable
fun TutorialWrongNamePage(name: String?, userAnswer: () -> Unit) {
  val sourceCode = """println("Hello $placeholder!")""".trimMargin()

  Column {
    Text("Ok, mistakes can happen, let's try again:")
    DefaultVerticalSpacer()
    CodeBlock(sourceCode)
    DefaultVerticalSpacer()
    Text(text = "Now select your name:")
    DefaultVerticalSpacer()

    Row(Modifier.align(Alignment.CenterHorizontally)) {
      CodeButton(
          text = name ?: "",
          onClick = userAnswer
      )
    }
  }
}

@Preview
@Composable
fun PreviewTutorialWrongNamePage() {
  MaterialTheme {
    Surface {
      TutorialWrongNamePage("Foo") {}
    }
  }
}
