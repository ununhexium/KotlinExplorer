package net.lab0.kotlinexplorer.framework.presentation.composable.code.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.MultipleChoiceAnswer

@Composable
fun MultipleChoiceAnswerInput(
  answers: List<MultipleChoiceAnswer>,
  toggle: (Int) -> Unit,
) {
  Row {
    val correctColor = MaterialTheme.colors.primary
    val wrongColor = MaterialTheme.colors.secondaryVariant
    val noCorrectness = MaterialTheme.colors.onBackground

    Column {
      answers.forEach { answer ->

        val color = getColor(answer, correctColor, wrongColor, noCorrectness)

        Row(
          modifier = Modifier.clickable {
            if (answer.correct == null) {
              toggle(answer.id)
            }
          },
        ) {
          if (answer.correct != null) {
            val goodAnswer = if (answer.correct) answer.used else !answer.used
            Checkbox(
              selected = goodAnswer,
              color = if (answer.correct == false) wrongColor else correctColor,
            )
          } else {
            Spacer(
              modifier = Modifier
                .width(Icons.Default.CheckBox.defaultWidth + 8.dp)
                .align(Alignment.CenterVertically),
            )
          }

          Checkbox(
            selected = answer.used,
            color = if (answer.correct == false) wrongColor else noCorrectness,
          )

          Text(
            answer.text,
            modifier = Modifier.align(Alignment.CenterVertically),
            style = MaterialTheme.typography.body1,
            color = if (answer.correct == false) wrongColor else noCorrectness,
          )
        }
      }
    }
  }
}

private fun getColor(
  answer: MultipleChoiceAnswer,
  correctColor: Color,
  wrongColor: Color,
  noCorrectness: Color
): Color {
  return when (answer.correct) {
    true -> correctColor
    false -> wrongColor
    null -> noCorrectness
  }
}

@Composable
private fun RowScope.Checkbox(
  selected: Boolean,
  color: Color
) {
  Icon(
    if (selected) {
      Icons.Default.CheckBox
    } else {
      Icons.Default.CheckBoxOutlineBlank
    },
    contentDescription = if (selected) "Selected" else "Unselected",
    modifier = Modifier
      .padding(horizontal = 4.dp)
      .align(Alignment.CenterVertically),
    tint = color,
  )
}

@Composable
private fun RowScope.CorrectnessLabel(text: String) {
  Text(
    text = text,
    modifier = Modifier.align(Alignment.CenterVertically),
    color = Color.Gray,
    style = MaterialTheme.typography.body2,
    fontFamily = FontFamily.Serif,
    fontStyle = FontStyle.Italic,
  )
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
              MultipleChoiceAnswer(
                6,
                "Unchecked non validated. But this one is really long ans spans over multiple line to check that it's all align nicely.",
                false,
                null
              ),
              MultipleChoiceAnswer(0, "Checked correct", true, true),
              MultipleChoiceAnswer(1, "Unchecked correct", false, true),
              MultipleChoiceAnswer(2, "Checked incorrect", true, false),
              MultipleChoiceAnswer(3, "Unchecked incorrect", false, false),
              MultipleChoiceAnswer(4, "Checked non validated", true, null),
              MultipleChoiceAnswer(5, "Unchecked non validated", false, null),
            ),
            toggle = {},
          )
        }
      }
    }
  }
}
