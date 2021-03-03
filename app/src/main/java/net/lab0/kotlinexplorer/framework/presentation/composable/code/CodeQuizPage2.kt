package net.lab0.kotlinexplorer.framework.presentation.composable.code

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.business.domain.Chapter
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.extractHighlightsAndAnnotate
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.codequestion.CodeQuestionViewModel
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.codequestion.mvi.CodeQuestionUiState
import net.lab0.kotlinexplorer.framework.presentation.composable.DefaultVerticalSpacer
import net.lab0.kotlinexplorer.framework.presentation.composable.code.input.CodeAnswerInput
import net.lab0.kotlinexplorer.framework.presentation.composable.code.input.CodeInputControlBar
import net.lab0.kotlinexplorer.framework.presentation.composable.code.input.NextPageControlBar
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.CorrectAnswer
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.LessonPageBody
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.WrongAnswer
import net.lab0.kotlinexplorer.framework.presentation.composable.markdown.MDDocument
import net.lab0.kotlinexplorer.framework.presentation.composable.markdown.parseMD
import org.commonmark.node.Node

@Composable
fun CodeQuizPage2(
    model: CodeQuestionViewModel,
    nextQuestion: () -> Unit,
    onSelect: (Answer) -> Unit,
    codeColoration: Boolean = true,
) {
  val state = model.uiDataState.value

  LessonPageBody(
      question = {
        val markdown by remember(state.lessonPage.question) {
          mutableStateOf(parseMD(state.lessonPage.question))
        }
        CodeQuestionPart2(
            questionMarkdown = markdown,
            snippet = state.selectedAnswersSnippet,
            nextBlank = state.nextBlank,
            codeColoration = codeColoration
        )
      },
      answer = {
        if (state.showAnswer) {
          AnswerPart2(
              isCorrectAnswer = state.isCorrectAnswer,
              explanationMarkdown = state.lessonPage.explanation,
              answerSnippet = state.snippetWithFocusedMistakes,
          )
        }
      },
      input = {
        if (!state.showAnswer) {
          CodeAnswerInput(
              onSelect = onSelect,
              canValidate = state.canValidate,
              answers = state.choices,
          )
        }
      },
      controlBar = {
        if (state.showAnswer) {
          NextPageControlBar(
              onNext = nextQuestion
          )
        } else {
          CodeInputControlBar(
              canUndoOrReset = state.canUndoOrReset,
              canValidate = state.canValidate,
              onUndo = model::undo,
              onReset = model::reset,
              onValidate = model::validate,
          )
        }
      }
  )
}

@Composable
private fun AnswerPart2(
    isCorrectAnswer: Boolean,
    explanationMarkdown: String,
    answerSnippet: AnnotatedString,
) {
  Column {
    val markdown = remember { mutableStateOf(parseMD(explanationMarkdown)) }
    if (isCorrectAnswer) {
      CorrectAnswer(
          explanation = {
            MDDocument(document = markdown.value)
          }
      )
    } else {
      WrongAnswer(
          explanation = {
            Column {
              Text("Correct answer:", style = MaterialTheme.typography.body1)
              DefaultVerticalSpacer()
              KotlinCode(
                  code = answerSnippet,
                  codeStyle = DefaultCodeStyle,
              )
              DefaultVerticalSpacer()
              MDDocument(document = markdown.value)
            }
          }
      )
    }
  }
}

@Composable
private fun CodeQuestionPart2(
    questionMarkdown: Node,
    snippet: String,
    nextBlank: Int,
    codeColoration: Boolean
) {
  MDDocument(questionMarkdown)
  DefaultVerticalSpacer()
  KotlinCode(
      code = if (codeColoration) {
        extractHighlightsAndAnnotate(snippet, DefaultCodeStyle.textStyler)
      } else {
        AnnotatedString(snippet)
      },
      codeStyle = DefaultCodeStyle,
      activeHighlight = nextBlank,
  )
}

val modelSelected2 = CodeQuestionViewModel().also {
  it.select(0)
}

@Preview
@Composable
fun CodeQuestionQuizPage2Preview_selectedAnswer() {
  MaterialTheme {
    Surface {
      Column {
        CodeQuizPage2(
            model = modelSelected2,
            nextQuestion = {},
            onSelect = {},
            codeColoration = false
        )
      }
    }
  }
}

val modelValidated2 = CodeQuestionViewModel(
    initialState = CodeQuestionUiState(
        pageIndex = 0,
        lessonPage = LessonPage.CodeQuestionPage.EXAMPLE,
        chapter = Chapter.EMPTY,
        selectedAnswers = listOf(),
        locked = true
    )
).also {
  it.select(0)
  it.validate()
}

@Preview
@Composable
fun CodeQuestionQuizPage2Preview_validatedAnswer() {
  MaterialTheme {
    Surface {
      Column {
        CodeQuizPage2(
            model = modelValidated2,
            nextQuestion = {},
            onSelect = {},
            codeColoration = false,
        )
      }
    }
  }
}
