package net.lab0.kotlinexplorer.framework.presentation.composable.lesson

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder
import net.lab0.kotlinexplorer.framework.presentation.composable.code.Answer
import net.lab0.kotlinexplorer.framework.presentation.composable.code.KotlinCode
import net.lab0.kotlinexplorer.framework.presentation.composable.code.input.CodeAnswerInput
import net.lab0.kotlinexplorer.framework.presentation.composable.code.input.CodeInputControlBar
import net.lab0.kotlinexplorer.framework.presentation.composable.code.input.NextPageControlBar
import net.lab0.kotlinexplorer.framework.presentation.composable.DefaultVerticalSpacer

@Composable
fun LessonPageBody(
    question: @Composable () -> Unit,
    answer: (@Composable () -> Unit)? = null,
    input: (@Composable () -> Unit)? = null,
    controlBar: @Composable () -> Unit,
) {
  ScrollableColumn(
      modifier = Modifier
          .fillMaxSize()
          .padding(8.dp),
      verticalArrangement = Arrangement.SpaceBetween,
  ) {
    Column(modifier = Modifier.fillMaxWidth()) {
      question()
      Column(
          modifier = Modifier.padding(vertical = 8.dp)
      ) {
        answer?.invoke()
      }
    }

    Column(
        modifier = Modifier.padding(vertical = 8.dp),
    ) {
      Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceAround
      ) {
        input?.invoke()
      }
      controlBar()
    }
  }
}

@Preview
@Composable
fun LessonPageBodyPreview_input() {
  MaterialTheme {
    LessonPageBody(
        question = {
          Text("Why?")
          DefaultVerticalSpacer()
          KotlinCode(
              code = """println("Because ${placeholder(0)}!")"""
          )
        },
        answer = {
        },
        input = {
          CodeAnswerInput(
              onSelect = { },
              canValidate = true,
              answers = listOf(
                  Answer(0, "alpha", false),
                  Answer(0, "beta", true),
                  Answer(0, "gamma", false),
              ),
          )
        },
        controlBar = {
          Row(Modifier.padding(8.dp)) {
            CodeInputControlBar(
                canUndoOrReset = true,
                canValidate = true,
                onUndo = {},
                onReset = {},
                onValidate = {},
            )
          }
        }
    )
  }
}


@Preview
@Composable
fun LessonPageBodyPreview_answer() {
  MaterialTheme {
    LessonPageBody(
        question = {
          Text("Why?")
          DefaultVerticalSpacer()
          KotlinCode(
              code = """println("Because ${placeholder(0)}!")"""
          )
        },
        answer = {
          CorrectAnswer(explanation = { Text("That's why") })
        },
        controlBar = {
          NextPageControlBar(onNext = { })
        }
    )
  }
}


@Preview
@Composable
fun LessonPageBodyPreview_longAnswer() {
  MaterialTheme {
    LessonPageBody(
        question = {
          Text("Why?")
          DefaultVerticalSpacer()
          KotlinCode(
              code = """println("Because ${placeholder(0)}!")"""
          )
        },
        answer = {
          CorrectAnswer(
              explanation = {
                Text("That's why ".repeat(200))
              }
          )
        },
        controlBar = {
          NextPageControlBar(onNext = { })
        }
    )
  }
}
