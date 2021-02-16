package net.lab0.kotlinexplorer.framework.presentation.components.code

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import net.lab0.kotlinexplorer.framework.presentation.components.CorrectAnswer
import net.lab0.kotlinexplorer.framework.presentation.components.QuizPage
import net.lab0.kotlinexplorer.framework.presentation.components.WrongAnswer
import net.lab0.kotlinexplorer.framework.presentation.components.markdown.MDDocument
import net.lab0.kotlinexplorer.framework.presentation.fragment.lesson.CodeQuestionPageViewModel
import net.lab0.kotlinexplorer.framework.ui.frame.DefaultVerticalSpacer
import net.lab0.kotlinexplorer.framework.ui.frame.SmallVerticalSpacer
import net.lab0.kotlinexplorer.model.code.DefaultCodeStyle
import net.lab0.kotlinexplorer.model.code.extractHighlightsAndAnnotate

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
            activeHighlight = model.nextBlank,
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
                  Column {
                    Text("Correct answer", style = MaterialTheme.typography.h6)
                    DefaultVerticalSpacer()
                    KotlinCode(
                        code = if (codeColoration) {
                          extractHighlightsAndAnnotate(
                              model.answerSnippet,
                              DefaultCodeStyle.textStyler
                          )
                        } else {
                          AnnotatedString(model.answerSnippet)
                        },
                        codeStyle = DefaultCodeStyle,
                        activeHighlight = model.nextBlank,
                    )
                    DefaultVerticalSpacer()
                    MDDocument(document = model.explanationMarkdown)
                  }
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
            answers = model.possibleAnswers.value,
        )
      }
  )
}

val modelSelected = CodeQuestionPageViewModel(
    "Why?",
    "val i = 0",
    "Because",
    listOf("a"),
    listOf("b", "c")
).also {
  it.select(it.possibleAnswers.value.first())
}

@Preview
@Composable
fun CodeQuestionQuizPagePreview_selectedAnswer() {
  MaterialTheme {
    Surface {
      Column {
        CodeQuizPage(
            modelSelected,
            codeColoration = false,
        ) {}
      }
    }
  }
}

val modelValidated = CodeQuestionPageViewModel(
    "Why?",
    "val i = 0",
    "Because",
    listOf("a"),
).also {
  it.select(it.possibleAnswers.value.first())
  it.validate()
}


@Preview
@Composable
fun CodeQuestionQuizPagePreview_validatedAnswer() {
  MaterialTheme {
    Surface {
      Column {
        CodeQuizPage(
            modelValidated,
            codeColoration = false,
        ) {}
      }
    }
  }
}
