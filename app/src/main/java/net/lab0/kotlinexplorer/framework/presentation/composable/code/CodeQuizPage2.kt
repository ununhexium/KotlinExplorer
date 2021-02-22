package net.lab0.kotlinexplorer.framework.presentation.composable.code

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import net.lab0.kotlinexplorer.business.domain.extractHighlightsAndAnnotate
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanksImpl
import net.lab0.kotlinexplorer.framework.presentation.composable.DefaultVerticalSpacer
import net.lab0.kotlinexplorer.framework.presentation.composable.code.input.CodeAnswerInput
import net.lab0.kotlinexplorer.framework.presentation.composable.code.input.CodeInputControlBar
import net.lab0.kotlinexplorer.framework.presentation.composable.code.input.NextPageControlBar
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.CorrectAnswer
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.LessonPageBody
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.WrongAnswer
import net.lab0.kotlinexplorer.framework.presentation.composable.markdown.MDDocument
import net.lab0.kotlinexplorer.framework.presentation.composable.markdown.parseMD
import net.lab0.kotlinexplorer.framework.presentation.fragment.lesson.CodeQuestionPageViewModel
import net.lab0.kotlinexplorer.framework.presentation.fragment.lessonpage.CodeQuestionPageFragmentViewModel
import org.commonmark.node.Node

@Composable
fun CodeQuizPage2(
    model: CodeQuestionPageFragmentViewModel,
    nextQuestion: () -> Unit,
    onSelect: (Answer) -> Unit,
    codeColoration:Boolean = true
) {
  val state = model.uiDataState.value

  LessonPageBody(
      question = {
        val markdown by remember(state.lessonPage.question) {
          mutableStateOf(parseMD(state.lessonPage.question))
        }
        CodeQuestionPart2(
            questionMarkdown = markdown,
            snippet = state.snippet,
            nextBlank = state.nextBlank,
            codeColoration = codeColoration
        )
      },
      answer = if (state.showAnswer) {
        {
          AnswerPart2(
              isCorrectAnswer = state.isCorrectAnswer,
              explanationMarkdown = state.lessonPage.explanation,
              answerSnippet = state.lessonPage.answerSnippetanswerSnippet,
              codeColoration = codeColoration
          )
        }
      } else null,
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
          NextPageControlBar(onNext = nextQuestion)
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
    answerSnippet: String,
    codeColoration: Boolean
) {
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
                code = if (codeColoration) {
                  extractHighlightsAndAnnotate(
                      answerSnippet,
                      DefaultCodeStyle.textStyler
                  )
                } else {
                  AnnotatedString(answerSnippet)
                },
                codeStyle = DefaultCodeStyle,
            )
            DefaultVerticalSpacer()
            MDDocument(document = markdown.value)
          }
        }
    )
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

val modelSelected2 = CodeQuestionPageViewModel(
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
fun CodeQuestionQuizPage2Preview_selectedAnswer() {
  MaterialTheme {
    Surface {
      Column {
        CodeQuizPage(
            modelSelected2,
            codeColoration = false,
        ) {}
      }
    }
  }
}

val modelValidated2 = CodeQuestionPageViewModel(
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
fun CodeQuestionQuizPage2Preview_validatedAnswer() {
  MaterialTheme {
    Surface {
      Column {
        CodeQuizPage(
            modelValidated2,
            codeColoration = false,
        ) {}
      }
    }
  }
}
