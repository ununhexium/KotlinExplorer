package com.example.jetpackcomposeexplorer.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.presentation.ui.codequestion.CodeQuestionViewModel

@Composable
fun CodeQuizPage(
    model: CodeQuestionViewModel,
    nextQuestion: () -> Unit,
) {
  QuizPage(
      question = {
        CodeQuestion(
            question = model.question,
            code = model.codeSample,
        )
      },
      answer = if (model.showAnswer.value) {
        {
          if (model.isCorrectAnswer()) {
            CorrectAnswer(explanation = model.explanation)
          } else {
            WrongAnswer(explanation = model.explanation)
          }
        }
      } else null,
      answerInput = {
        CodeAnswerInput(
            onReset = model::reset,
            onUndo = model::undo,
            onValidate = if (model.showAnswer.value) {
              { nextQuestion() }
            } else {
              { model.validate() }
            },
            onSelect = model::select,
            canValidate = model.canValidate,
            canUndoOrReset = model.canUndoOrReset,
            canDoNext = model.showAnswer.value,
            answers = model.answers.value,
        )
      }
  )
}

@Preview
@Composable
fun CodeQuestionQuizPagePreview_selectedAnswer() {
  MaterialTheme {
    Surface {
      Column {
        CodeQuizPage(
            CodeQuestionViewModel(
                "Why?",
                "val i = 0",
                "Because",
                1,
                "a", "b", "c"
            ) {
              false
            }.also {
              it.select(it.answers.value.first())
            }
        ) {}
      }
    }
  }
}

@Preview
@Composable
fun CodeQuestionQuizPagePreview_validatedAnswer() {
  MaterialTheme {
    Surface {
      Column {
        CodeQuizPage(
            CodeQuestionViewModel(
                "Why?",
                "val i = 0",
                "Because",
                1,
                "a", "b", "c"
            ) {
              false
            }.also {
              it.select(it.answers.value.first())
              it.validate()
            }
        ) {}
      }
    }
  }
}