package net.lab0.kotlinexplorer.framework.presentation.composable.lesson

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
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import net.lab0.kotlinexplorer.business.domain.Chapter
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.multiplechoice.MultipleChoiceViewModel
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.multiplechoice.mvi.MultipleChoiceUiState
import net.lab0.kotlinexplorer.framework.presentation.composable.code.input.ControlBar
import net.lab0.kotlinexplorer.framework.presentation.composable.code.input.MultipleChoiceAnswerInput
import net.lab0.kotlinexplorer.framework.presentation.composable.markdown.MDDocument
import net.lab0.kotlinexplorer.framework.presentation.composable.markdown.parseMD

@Composable
fun MultipleChoicePage(
  model: MultipleChoiceViewModel,
  state: MultipleChoiceUiState,
  onNextPage: () -> Unit,
) {
  LessonPageBody(
    question = {
      val markdown = remember(
        state.lessonPage.question
      ) {
        parseMD(state.lessonPage.question)
      }
      MDDocument(markdown)
    },
    answer = if (state.showAnswer) {
      {
        val markdown = remember(state.lessonPage.explanation) {
          parseMD(state.lessonPage.explanation)
        }
        if (state.isCorrectAnswer) {
          CorrectAnswer(
            explanation = {

              MDDocument(document = markdown)
            }
          )
        } else {
          WrongAnswer(
            explanation = {
              MDDocument(document = markdown)
            }
          )
        }
      }
    } else null,
    input = {
      if (!state.showAnswer) {
        MultipleChoiceAnswerInput(
          answers = state.choices,
          toggle = model::toggle,
        )
      } else {
        MultipleChoiceAnswerInput(
          answers = state.choices,
          toggle = {},
        )
      }
    },
    controlBar = {
      if (!state.showAnswer) {
        ControlBar {
          Row {
            Button(
              onClick = model::validate,
            ) {
              Icon(
                imageVector = Icons.Default.Done,
                contentDescription = "Done",
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
                contentDescription = "Arrow Forward",
              )
            }
          }
        }
      }
    }
  )
}

val multipleChoiceSample = LessonPage.MultipleChoice(
  title = "Test",
  question = "Why?",
  "Because",
  listOf("A", "BB", "CCC", "DDDD"),
  setOf(0, 2),
)
val multipleChoiceModelAnswer = MultipleChoiceViewModel(
  initialState = MultipleChoiceUiState(
    pageIndex = 0,
    lessonPage = multipleChoiceSample,
    chapter = Chapter.EMPTY,
    selectedAnswers = listOf(0, 1),
    locked = true,
  )
)

val multipleChoiceModelQuestion = MultipleChoiceViewModel(
  initialState = MultipleChoiceUiState(
    pageIndex = 0,
    lessonPage = multipleChoiceSample,
    chapter = Chapter.EMPTY,
    selectedAnswers = listOf(0, 1),
  )
)

@Preview
@Composable
fun MCCP_selectedAnswers() {
  MaterialTheme {
    Surface {
      Column {
        MultipleChoicePage(
          multipleChoiceModelAnswer,
          multipleChoiceModelAnswer.uiDataState.value,
        ) { }
      }
    }
  }
}

@Preview
@Composable
fun MCCP_question() {
  MaterialTheme {
    Surface {
      Column {
        MultipleChoicePage(
          multipleChoiceModelQuestion,
          multipleChoiceModelQuestion.uiDataState.value,
        ) { }
      }
    }
  }
}
