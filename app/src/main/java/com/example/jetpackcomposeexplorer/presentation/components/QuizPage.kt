package com.example.jetpackcomposeexplorer.presentation.components

import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        modifier = Modifier
            .padding(8.dp)
            .constrainAs(questionRef) {
              top.linkTo(parent.top)
              start.linkTo(parent.start)
              end.linkTo(parent.end)
            }
    ) {
      question()
    }

    answer?.let {
      Surface(
          modifier = Modifier
              .padding(8.dp)
              .constrainAs(answerRef) {
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
          bottom.linkTo(parent.bottom, margin = 8.dp)
          start.linkTo(parent.start, margin = 8.dp)
          end.linkTo(parent.end, margin = 8.dp)
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
              answer = {
                CorrectAnswer(explanation = "That's why")
              },
              answerInput = {
                CodeAnswerInput(
                    onReset = { },
                    onUndo = { },
                    onValidate = { },
                    onSelect = { },
                    canValidate = true,
                    canUndoOrReset = true,
                    canDoNext = true,
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

