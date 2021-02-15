package net.lab0.kotlinexplorer.framework.presentation.components.code.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.framework.presentation.components.code.Answer
import net.lab0.kotlinexplorer.framework.presentation.components.code.input.InputFieldMainAction.DISABLED
import net.lab0.kotlinexplorer.framework.presentation.components.code.input.InputFieldMainAction.NEXT
import net.lab0.kotlinexplorer.framework.presentation.components.code.input.InputFieldMainAction.VALIDATE

@Composable
fun MultipleChoiceAnswerInput(
    answers: List<Answer>,
    inputFieldMainAction: InputFieldMainAction,
    onValidate: () -> Unit,
    onNextPage: () -> Unit,
    toggle: (Answer) -> Unit,
) {
  Column {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.End,
    ) {
      Row {
        Button(
            onClick = when (inputFieldMainAction) {
              VALIDATE -> onValidate
              NEXT -> onNextPage
              else -> ({})
            },
            enabled = inputFieldMainAction != DISABLED,
        ) {
          Icon(
              imageVector = when (inputFieldMainAction) {
                DISABLED -> Icons.Default.Done
                VALIDATE -> Icons.Default.Done
                NEXT -> Icons.Default.ArrowForward
              },
          )
        }
      }
    }

    Column(
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
      answers.forEach { answer ->
        Row(
            modifier = Modifier
                .padding(4.dp)
                .clickable(onClick = { toggle(answer) })
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
          Icon(
              if (answer.used) {
                Icons.Default.CheckBox
              } else {
                Icons.Default.CheckBoxOutlineBlank
              },
              modifier = Modifier
                  .padding(horizontal = 4.dp)
                  .align(Alignment.CenterVertically),
          )

          Text(
              answer.text,
              modifier = Modifier.align(Alignment.CenterVertically),
              style = MaterialTheme.typography.body1,
          )
        }
      }

    }
  }
}

@Preview
@Composable
fun MultipleChoiceAnswerInputPreview() {
  MaterialTheme {
    Surface(
        color = Color(0xFF4CAF50)
    ) {
      Column(
          modifier = Modifier.padding(20.dp)
      ) {
        Surface(
            color = MaterialTheme.colors.surface
        ) {
          MultipleChoiceAnswerInput(
              answers = listOf(
                  Answer(0, "Alpha", false),
                  Answer(0, "Beta", true),
              ),
              inputFieldMainAction = VALIDATE,
              onValidate = {},
              onNextPage = {},
              toggle = {},
          )
        }
      }
    }
  }
}
