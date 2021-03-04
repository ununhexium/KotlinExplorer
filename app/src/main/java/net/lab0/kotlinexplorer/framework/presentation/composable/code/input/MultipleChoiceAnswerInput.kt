package net.lab0.kotlinexplorer.framework.presentation.composable.code.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.MultipleChoiceAnswer

@Composable
fun MultipleChoiceAnswerInput(
  answers: List<MultipleChoiceAnswer>,
  toggle: (Int) -> Unit,
) {
  Column {
    answers.forEach { answer ->
      Row(
        modifier = Modifier
          .padding(4.dp)
          .clickable(onClick = { toggle(answer.id) })
          .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceAround,
      ) {
        val color = when (answer.correct) {
          true -> MaterialTheme.colors.primary
          false -> MaterialTheme.colors.secondaryVariant
          null -> MaterialTheme.colors.onBackground
        }
        val icon = when (answer.used) {
          true -> Icons.Default.CheckBox
          false -> Icons.Default.CheckBoxOutlineBlank
        }
        Icon(
          icon,
          contentDescription =
          (if (answer.used) "Selected" else "Unselected") +
              when (answer.correct) {
                null -> " "
                true -> " correct"
                false -> " wrong"
              } +
              " choice",
          modifier = Modifier
            .padding(horizontal = 4.dp)
            .align(Alignment.CenterVertically),
          tint = color,
        )

        Text(
          answer.text,
          modifier = Modifier.align(Alignment.CenterVertically),
          style = MaterialTheme.typography.body1,
          color = color,
        )
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
              MultipleChoiceAnswer(0, "Checked unset", true, null),
              MultipleChoiceAnswer(1, "Unchecked unset", false, null),
              MultipleChoiceAnswer(2, "Checked correct", true, true),
              MultipleChoiceAnswer(2, "Unchecked correct", false, true),
              MultipleChoiceAnswer(3, "Checked incorrect", true, false),
              MultipleChoiceAnswer(3, "Unchecked incorrect", false, false),
            ),
            toggle = {},
          )
        }
      }
    }
  }
}
