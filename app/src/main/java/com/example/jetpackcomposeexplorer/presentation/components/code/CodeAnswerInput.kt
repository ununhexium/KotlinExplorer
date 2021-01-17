package com.example.jetpackcomposeexplorer.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.presentation.components.code.Answer

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
        Icon(
            imageVector = Icons.Default.Undo,
            tint = if (canUndoOrReset) MaterialTheme.colors.secondary else Color.Gray,
            modifier = Modifier.clickable(onClick = onUndo)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Icon(
            imageVector = Icons.Default.Replay,
            tint = if (canUndoOrReset) MaterialTheme.colors.secondary else Color.Gray,
            modifier = Modifier.clickable(onClick = onReset)
        )
      }
      Row {
        Icon(
            imageVector = if (canDoNext) {
              Icons.Default.ArrowForward
            } else {
              Icons.Default.Done
            },
            tint = if (canValidate) MaterialTheme.colors.primary else Color.Gray,
            modifier = Modifier.clickable(onClick = onValidate),
        )
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