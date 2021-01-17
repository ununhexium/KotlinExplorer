package com.example.jetpackcomposeexplorer.presentation.components

import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.ui.frame.placeholder
import com.example.jetpackcomposeexplorer.ui.frame.preview.PreviewTopBar

@Composable
fun QuizPage(
    question: @Composable () -> Unit,
    answer: (@Composable () -> Unit)? = null,
    answerInput: @Composable () -> Unit,
) {
  ConstraintLayout(
      modifier = Modifier.fillMaxSize()
  ) {
    val (questionRef, answerRef, answerInputRef) = createRefs()

    Surface(
        modifier = Modifier.constrainAs(questionRef) {
          top.linkTo(parent.top)
          start.linkTo(parent.start)
          end.linkTo(parent.end)
        }
    ) {
      question()
    }

    answer?.let {
      Surface(
          modifier = Modifier.constrainAs(answerRef) {
            top.linkTo(questionRef.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
          }
      ) {
        answer()
      }
    }

    Surface(
        modifier = Modifier.constrainAs(answerInputRef) {
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
fun QuizPagePreview() {
  MaterialTheme {
    Scaffold(
        bodyContent = {
          QuizPage(
              question = {
                CodeQuestion(
                    question = "Why?",
                    code = """println("Because ${placeholder(0)}!")"""
                )
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

