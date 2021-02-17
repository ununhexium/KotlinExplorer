package net.lab0.kotlinexplorer.framework.presentation.components.code.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.framework.presentation.components.code.Answer
import net.lab0.kotlinexplorer.framework.presentation.components.code.AnswerChip
import net.lab0.kotlinexplorer.framework.presentation.components.frame.StaggeredGrid

@Composable
fun CodeAnswerInput(
    onSelect: (Answer) -> Unit,
    canValidate: Boolean,
    answers: List<Answer>,
) {
  Surface {
    Column {
      Row(
          modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
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
}

@Preview
@Composable
fun CodeAnswerInputPreview() {
  MaterialTheme {
    Surface {
      Column {
        CodeAnswerInput(
            {},
            canValidate = false,
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
            canValidate = true,
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
            canValidate = true,
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
