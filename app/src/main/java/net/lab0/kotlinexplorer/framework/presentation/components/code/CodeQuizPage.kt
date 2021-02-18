package net.lab0.kotlinexplorer.framework.presentation.components.code

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import net.lab0.kotlinexplorer.framework.presentation.components.CorrectAnswer
import net.lab0.kotlinexplorer.framework.presentation.components.LessonPageBody
import net.lab0.kotlinexplorer.framework.presentation.components.WrongAnswer
import net.lab0.kotlinexplorer.framework.presentation.components.code.input.CodeAnswerInput
import net.lab0.kotlinexplorer.framework.presentation.components.code.input.CodeInputControlBar
import net.lab0.kotlinexplorer.framework.presentation.components.code.input.NextPageControlBar
import net.lab0.kotlinexplorer.framework.presentation.components.markdown.MDDocument
import net.lab0.kotlinexplorer.framework.presentation.fragment.lesson.CodeQuestionPageViewModel
import net.lab0.kotlinexplorer.framework.ui.frame.DefaultVerticalSpacer
import net.lab0.kotlinexplorer.model.code.DefaultCodeStyle
import net.lab0.kotlinexplorer.model.code.extractHighlightsAndAnnotate

@Composable
fun CodeQuizPage(
    model: CodeQuestionPageViewModel,
    codeColoration: Boolean = true,
    nextQuestion: () -> Unit,
) {
  LessonPageBody(
      question = {
        CodeQuestionPart(model, codeColoration)
      },
      answer = if (model.showAnswer.value) {
        { AnswerPart(model, codeColoration) }
      } else null,
      input = {
        if (!model.showAnswer.value){
          CodeAnswerInput(
              onSelect = model::select,
              canValidate = model.canValidate,
              answers = model.possibleAnswers.value,
          )
        } else null
      },
      controlBar = {
        if (model.showAnswer.value) {
          NextPageControlBar(onNext = nextQuestion)
        } else {
          CodeInputControlBar(
              canUndoOrReset = model.canUndoOrReset,
              canValidate = model.canValidate,
              onUndo = model::undo,
              onReset = model::reset,
              onValidate = model::validate,
          )
        }
      }
  )
}

@Composable
private fun AnswerPart(
    model: CodeQuestionPageViewModel,
    codeColoration: Boolean
) {
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
            Text("Correct answer:", style = MaterialTheme.typography.body1)
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

@Composable
private fun CodeQuestionPart(
    model: CodeQuestionPageViewModel,
    codeColoration: Boolean
) {
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
