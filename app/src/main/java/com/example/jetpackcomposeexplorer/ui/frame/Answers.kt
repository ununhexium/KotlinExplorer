package com.example.jetpackcomposeexplorer.ui.frame

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.ui.frame.preview.PreviewTopBar
import kotlin.math.ceil
import kotlin.math.sqrt

@Composable
fun Answers(vararg answers: @Composable () -> Unit) {
  val size = ceil(sqrt(answers.size.toDouble())).toInt()

  Column {
    (0 until size).forEach { line ->
      Row(
          Modifier
              .weight(1f)
              .fillMaxWidth(1f)
      ) {
        (0 until size).forEach { column ->
          Box(
              Modifier
                  .weight(1f)
                  .fillMaxSize()
          ) {
            val index = line * size + column
            if (index < answers.size) {
              ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val composable = createRef()
                Surface(
                    elevation = 4.dp,
                    modifier = Modifier.constrainAs(composable) {
                      centerVerticallyTo(parent)
                      centerHorizontallyTo(parent)
                    }
                ) {
                  answers[index]()
                }
              }
            }
          }
        }
      }
    }
  }
}

@Preview
@Composable
fun PreviewAnswers() {
  MaterialTheme {
    Scaffold(
        bodyContent = {
          Answers(
              { Text("Hello") },
              { Icon(Icons.Default.Check) },
              { Text("3") },
          )
        },
        topBar = { PreviewTopBar() },
    )
  }
}
