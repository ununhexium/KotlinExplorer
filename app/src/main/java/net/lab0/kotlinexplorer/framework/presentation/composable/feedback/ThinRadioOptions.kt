package net.lab0.kotlinexplorer.framework.presentation.composable.feedback

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.ThinButton

@Composable
fun RadioOptions(
    options: List<String>,
    onSelection: (Int) -> Unit,
) {
  Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
  ) {
    val (selectedIndex, setSelectedIndex) = remember { mutableStateOf(null as Int?) }
    options.forEachIndexed { index, it ->
      ThinButton(
          text = it,
          onClick = {
            setSelectedIndex(index)
            onSelection(index)
          },
          highlight = selectedIndex == index
      )
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
        RadioOptions(
            listOf("Alpha", "Beta", "Gamma"),
        ) {}
      }
    }
  }
}
