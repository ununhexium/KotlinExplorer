package net.lab0.kotlinexplorer.framework.presentation.composable.form

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RadioSelection(
  choices: List<String>,
  modifier: Modifier = Modifier,
  radioState: RadioState = rememberRadioState(),
  textStyle: TextStyle = MaterialTheme.typography.body1,
) {
  val (get, set) = radioState

  Column(modifier = modifier) {
    choices.forEachIndexed { index, it ->
      Row(
        modifier = Modifier
          .clickable {
            set(index)
          }
          .padding(4.dp)
      ) {
        Icon(
          if (get == index) {
            Icons.Default.RadioButtonChecked
          } else {
            Icons.Default.RadioButtonUnchecked
          },
          contentDescription = "Radio select $it",
          modifier = Modifier.align(Alignment.CenterVertically),
        )

        Text(
          text = it,
          modifier = Modifier.align(Alignment.CenterVertically),
          style = textStyle
        )
      }
    }
  }
}

typealias RadioState = MutableState<Int?>

@Composable
fun rememberRadioState() = remember { mutableStateOf(null as Int?) }

@Preview
@Composable
fun RadioSelectionPreview() {
  MaterialTheme {
    Surface(
      color = Color(0xFF4CAF50)
    ) {
      Column(
        modifier = Modifier.padding(20.dp)
      ) {
        Surface(
          color = MaterialTheme.colors.background
        ) {
          RadioSelection(
            choices = listOf("Alpha", "Beta", "Gamma"),
            textStyle = MaterialTheme.typography.h4,
          )
        }
      }
    }
  }
}
