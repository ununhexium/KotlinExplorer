package net.lab0.jetpackcomposeexplorer.framework.presentation.components.code

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import net.lab0.jetpackcomposeexplorer.business.domain.LessonPage
import net.lab0.jetpackcomposeexplorer.framework.presentation.components.CorrectAnswer
import net.lab0.jetpackcomposeexplorer.framework.presentation.components.QuizPage
import net.lab0.jetpackcomposeexplorer.framework.presentation.components.WrongAnswer
import net.lab0.jetpackcomposeexplorer.framework.presentation.components.markdown.MDDocument
import net.lab0.jetpackcomposeexplorer.framework.presentation.fragment.lesson.MultipleChoiceModel

@Composable
fun MultipleChoicePage(
    model: MultipleChoiceModel,
    codeColoration: Boolean = true,
    nextQuestion: () -> Unit,
) {
  QuizPage(
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
      answerInput = {
        MultipleChoiceAnswerInput(
            answers = model.choices,
            toggle = model::toggle
        )
      }
  )
}

@Preview
@Composable
fun MCCP_selectedAnswers() {
  MaterialTheme {
    Surface {
      Column {
        MultipleChoicePage(
            MultipleChoiceModel(
                LessonPage.MultipleChoice(
                    title = "Test",
                    question = "Why?",
                    "Because",
                    listOf("A", "B", "C"),
                    setOf(0, 2)
                )
            ).also {
              it.toggle(it.answers.value[1])
            },
            codeColoration = false,
        ) {}
      }
    }
  }
}

@Preview
@Composable
fun MCCP_validatedAnswer() {
  MaterialTheme {
    Surface {
      Column {
        MultipleChoicePage(
            MultipleChoiceModel(
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
            },
            codeColoration = false,
        ) {}
      }
    }
  }
}
