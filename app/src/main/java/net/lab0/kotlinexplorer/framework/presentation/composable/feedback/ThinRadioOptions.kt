package net.lab0.kotlinexplorer.framework.presentation.composable.feedback

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.ThinButton

@Composable
fun <T> ThinRadioOptions(
  options: List<T>,
  selected: T,
  onSelection: (T) -> Unit,
) where T : Any {
  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.SpaceEvenly
  ) {
    options.forEach {
      Row(
        modifier = Modifier
          .padding(vertical = 4.dp)
          .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
      ) {
        ThinButton(
          text = it.toString(),
          onClick = { onSelection(it) },
          highlight = selected == it
        )
      }
    }
  }
}

@Preview
@Composable
fun RadioOptionsPreview() {
  MaterialTheme {
    Surface(
      color = Color(0xFF4CAF50)
    ) {
      Column(
        modifier = Modifier.padding(20.dp)
      ) {
        ThinRadioOptions(
          listOf("Alpha", "Beta", "Gamma"),
          "Beta",
        ) {}
      }
    }
  }
}
