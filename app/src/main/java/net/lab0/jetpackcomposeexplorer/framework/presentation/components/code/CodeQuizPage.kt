package net.lab0.jetpackcomposeexplorer.framework.presentation.components.code

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import net.lab0.jetpackcomposeexplorer.model.code.DefaultCodeStyle
import net.lab0.jetpackcomposeexplorer.model.code.extractHighlightsAndAnnotate
import net.lab0.jetpackcomposeexplorer.framework.presentation.components.CorrectAnswer
import net.lab0.jetpackcomposeexplorer.framework.presentation.components.QuizPage
import net.lab0.jetpackcomposeexplorer.framework.presentation.components.WrongAnswer
import net.lab0.jetpackcomposeexplorer.framework.presentation.components.markdown.MDDocument
import net.lab0.jetpackcomposeexplorer.framework.presentation.fragment.lesson.CodeQuestionPageViewModel
import net.lab0.jetpackcomposeexplorer.framework.ui.frame.DefaultVerticalSpacer

@Composable
fun CodeQuizPage(
    model: CodeQuestionPageViewModel,
    codeColoration: Boolean = true,
    nextQuestion: () -> Unit,
) {
  QuizPage(
      question = {
        MDDocument(model.questionMarkdown)
        DefaultVerticalSpacer()
        KotlinCode(
            code = if (codeColoration) {
              extractHighlightsAndAnnotate(model.snippet, DefaultCodeStyle.textStyler)
            } else {
              AnnotatedString(model.snippet)
            },
            codeStyle = DefaultCodeStyle,
        )
      },
      answer = if (model.showAnswer.value) {
        {
          if (model.isCorrectAnswer()) {
            CorrectAnswer(
                explanation = {
                  MDDocument(document = model.explanationMarkdown)
                }
            )
          } else {
            WrongAnswer(
                explanation = {
                  MDDocument(document = model.explanationMarkdown)
                }
            )
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
            CodeQuestionPageViewModel(
                "Why?",
                "val i = 0",
                "Because",
                1,
                "a", "b", "c"
            ) {
              false
            }.also {
              it.select(it.answers.value.first())
            },
            codeColoration = false,
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
            CodeQuestionPageViewModel(
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
            },
            codeColoration = false,
        ) {}
      }
    }
  }
}
