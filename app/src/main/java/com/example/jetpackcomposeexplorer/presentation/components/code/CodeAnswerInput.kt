package com.example.jetpackcomposeexplorer.presentation.components.code

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.Undo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

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
          Icon(C
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
      answers
          .toList()
          .chunked(3)
          .forEach { chunk ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
              chunk.forEach { answer ->
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