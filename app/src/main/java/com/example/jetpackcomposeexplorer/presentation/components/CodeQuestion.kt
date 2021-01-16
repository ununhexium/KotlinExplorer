package com.example.jetpackcomposeexplorer.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.ui.frame.*
import com.example.jetpackcomposeexplorer.ui.frame.preview.PreviewTopBar

@Composable
fun CodeQuestion(
    question: @Composable () -> Unit,
    answerInput: @Composable () -> Unit,
) {
  ConstraintLayout(
      modifier = Modifier.fillMaxSize()
  ) {
    val (questionRef, answerRef) = createRefs()

    Surface(
        modifier = Modifier.constrainAs(questionRef) {
          top.linkTo(parent.top)
          start.linkTo(parent.start)
          end.linkTo(parent.end)
        }
    ) {
      question()
    }

    Surface(
        modifier = Modifier.constrainAs(answerRef) {
          bottom.linkTo(parent.bottom)
          start.linkTo(parent.start)
          end.linkTo(parent.end)
        }
    ) {
      answerInput()
    }
  }
}

@Preview
@Composable
fun CodeQuestionPreview() {
  MaterialTheme {
    Scaffold(
        bodyContent = {
          CodeQuestion(
              question = {
                Column(modifier = Modifier.fillMaxWidth()) {
                  Text("The first letter of the greek alphabet")
                  DefaultVerticalSpacer()
                  KotlinCode(code = """println("${placeholder()}")""")
                }
              },
              answerInput = {
                CodeAnswerInput(
                    onReset = { },
                    onUndo = { },
                    onValidate = { },
                    onSelect = { },
                    canValidate = true,
                    canUndoOrReset = true,
                    answers = listOf(
                        Answer(0, "alpha", false),
                        Answer(0, "beta", true),
                        Answer(0, "gamma", false),
                    ),
                )
              },
          )
        },
        topBar = { PreviewTopBar() },
    )
  }
}
