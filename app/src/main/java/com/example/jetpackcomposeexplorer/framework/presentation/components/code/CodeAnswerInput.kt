package com.example.jetpackcomposeexplorer.framework.presentation.components.code

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.Undo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.framework.presentation.components.frame.StaggeredGrid

@Composable
fun CodeAnswerInput(
    onReset: () -> Unit,
    onUndo: () -> Unit,
    onValidate: () -> Unit,
    onSelect: (Answer) -> Unit,
    canValidate: Boolean,
    canUndoOrReset: Boolean,
    canDoNext: Boolean,
    answers: List<Answer>,
) {
  Column {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row {
        val secondaryButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondaryVariant,
            contentColor = MaterialTheme.colors.surface
        )
        Button(
            onClick = onUndo,
            enabled = canUndoOrReset,
            colors = secondaryButtonColors
        ) {
          Icon(
              imageVector = Icons.Default.Undo,
          )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            onClick = onReset,
            enabled = canUndoOrReset,
            colors = secondaryButtonColors
        ) {
          Icon(
              imageVector = Icons.Default.Replay,
          )
        }
      }
      Row {
        Button(
            onClick = onValidate,
            enabled = canValidate,
        ) {
          Icon(
              imageVector = if (canDoNext) {
                Icons.Default.ArrowForward
              } else {
                Icons.Default.Done
              },
          )
        }
      }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
      StaggeredGrid {
        answers
            .toList()
            .forEach { answer ->
              Row(
                  modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
              ) {
                AnswerChip(text = answer.text, !answer.used && !canValidate) {
                  onSelect(answer)
                }
              }
            }
      }
    }
  }
}

@Preview
@Composable
fun CodeAnswerInputPreview() {
  MaterialTheme {
    Surface {
      Column {
        CodeAnswerInput(
            {},
            {},
            {},
            {},
            canValidate = false,
            canUndoOrReset = true,
            canDoNext = false,
            answers = listOf(
                Answer(0, "alpha", false),
                Answer(1, "beta", true),
                Answer(2, "gamma", false),
            )
        )
      }
    }
  }
}

@Preview
@Composable
fun CodeAnswerInputPreview_manyChipsCanValidate() {
  MaterialTheme {
    Surface {
      Column {
        CodeAnswerInput(
            {},
            {},
            {},
            {},
            canValidate = true,
            canUndoOrReset = false,
            canDoNext = true,
            answers = listOf(
                Answer(0, "alpha", false),
                Answer(1, "beta", true),
                Answer(2, "gamma", false),
                Answer(3, "delta", true),
                Answer(4, "epsilon", false),
                Answer(5, "zeta", false),
                Answer(6, "eta", false),
                Answer(7, "theta", false),
                Answer(8, "iota", false),
                Answer(9, "kappa", false),
                Answer(10, "lambda", true),
            )
        )
      }
    }
  }
}


@Preview
@Composable
fun CodeAnswerInputPreview_longChips() {
  MaterialTheme {
    Surface {
      Column {
        CodeAnswerInput(
            {},
            {},
            {},
            {},
            canValidate = true,
            canUndoOrReset = false,
            canDoNext = true,
            answers = listOf(
                Answer(0, "Float.POSITIVE_INFINITY", false),
                Answer(1, "Float.NEGATIVE_INFINITY", true),
                Answer(2, "a", false),
                Answer(3, "b", true),
                Answer(4, "c", false),
                Answer(5, "d", false),
                Answer(6, "e", false),
                Answer(7, "f", false),
            )
        )
      }
    }
  }
}
