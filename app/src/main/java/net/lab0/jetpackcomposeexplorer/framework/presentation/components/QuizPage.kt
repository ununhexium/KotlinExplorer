package net.lab0.jetpackcomposeexplorer.framework.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.jetpackcomposeexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder
import net.lab0.jetpackcomposeexplorer.framework.presentation.components.code.Answer
import net.lab0.jetpackcomposeexplorer.framework.presentation.components.code.CodeAnswerInput
import net.lab0.jetpackcomposeexplorer.framework.presentation.components.code.KotlinCode
import net.lab0.jetpackcomposeexplorer.framework.ui.frame.DefaultVerticalSpacer

@Composable
fun QuizPage(
    question: @Composable () -> Unit,
    answer: (@Composable () -> Unit)? = null,
    answerInput: @Composable () -> Unit,
) {
  ConstraintLayout(
      modifier = Modifier.fillMaxSize()
  ) {
    val (questionRef, answerRef, answerInputRef) = createRefs()

    Column(
        modifier = Modifier
            .padding(8.dp)
            .constrainAs(questionRef) {
              top.linkTo(parent.top)
              start.linkTo(parent.start)
              end.linkTo(parent.end)
            }
    ) {
      question()
    }

    answer?.let {
      Column(
          modifier = Modifier
              .padding(8.dp)
              .constrainAs(answerRef) {
                top.linkTo(questionRef.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
              }
      ) {
        Column {
          answer()
        }
      }
    }

    Column(
        modifier = Modifier.constrainAs(answerInputRef) {
          bottom.linkTo(parent.bottom, margin = 8.dp)
          start.linkTo(parent.start, margin = 8.dp)
          end.linkTo(parent.end, margin = 8.dp)
        }
    ) {
      answerInput()
    }
  }
}

@Preview
@Composable
fun QuizPagePreview() {
  MaterialTheme {
    QuizPage(
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
        answerInput = {
          CodeAnswerInput(
              onReset = { },
              onUndo = { },
              onValidate = { },
              onSelect = { },
              canValidate = true,
              canUndoOrReset = true,
              canDoNext = true,
              answers = listOf(
                  Answer(0, "alpha", false),
                  Answer(0, "beta", true),
                  Answer(0, "gamma", false),
              ),
          )
        },
    )
  }
}
