package net.lab0.kotlinexplorer.framework.presentation.composable.code

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.CorrectAnswer
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.LessonPageBody
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.WrongAnswer
import net.lab0.kotlinexplorer.framework.presentation.composable.code.input.ControlBar
import net.lab0.kotlinexplorer.framework.presentation.composable.code.input.MultipleChoiceAnswerInput
import net.lab0.kotlinexplorer.framework.presentation.composable.markdown.MDDocument
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.MultipleChoiceModel

@Composable
fun MultipleChoicePage(
    model: MultipleChoiceModel,
    onNextPage: () -> Unit,
) {
  LessonPageBody(
      question = {
        MDDocument(model.questionMarkdown)
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
      input = if (!model.showAnswer.value) {
        {
          MultipleChoiceAnswerInput(
              answers = model.answers.value,
              toggle = model::toggle,
          )
        }
      } else null,
      controlBar = {
        if (!model.showAnswer.value) {
          ControlBar {
            Row {
              Button(
                  onClick = model::validate,
              ) {
                Icon(
                    imageVector = Icons.Default.Done,
                )
              }
            }
          }
        } else {
          ControlBar {
            Row {
              Button(
                  onClick = onNextPage,
              ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                )
              }
            }
          }
        }
      }
  )
}

val multipleChoiceModel = MultipleChoiceModel(
    LessonPage.MultipleChoice(
        title = "Test",
        question = "Why?",
        "Because",
        listOf("A", "BB", "CCCCC"),
        setOf(0, 2)
    )
)

@Preview
@Composable
fun MCCP_selectedAnswers() {
  MaterialTheme {
    Surface {
      Column {
        MultipleChoicePage(
            multipleChoiceModel,
        ) { }
      }
    }
  }
}

val validatedModel = MultipleChoiceModel(
    LessonPage.MultipleChoice(
        title = "Test",
        question = "Why?",
        "Because",
        listOf("Alpha", "Beta", "Gamma"),
        setOf(0, 2)
    )
).also {
  it.toggle(it.answers.value[1])
  it.validate()
}

@Preview
@Composable
fun MCCP_validatedAnswer() {
  MaterialTheme {
    Surface {
      Column {
        MultipleChoicePage(
            validatedModel,
        ) {}
      }
    }
  }
}
