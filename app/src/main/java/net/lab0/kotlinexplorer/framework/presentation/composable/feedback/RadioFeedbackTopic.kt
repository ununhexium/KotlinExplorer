package net.lab0.kotlinexplorer.framework.presentation.composable.feedback

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.framework.presentation.composable.SmallVerticalSpacer

@Composable
fun RadioFeedbackTopic(
    topic: String,
    options: List<String>,
    onSelection: (Int?) -> Unit,
) {
  Column(
      modifier = Modifier.fillMaxWidth()
  ) {
    Text(
        modifier = Modifier.fillMaxWidth(0.5f),
        text = topic,
        style = MaterialTheme.typography.body1
    )
    SmallVerticalSpacer()
    ThinRadioOptions(
        options = options,
        onSelection = onSelection
    )
  }
}

@Preview
@Composable
fun FeedbackTopicPreview() {
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
          Column {
            RadioFeedbackTopic(
                "Water temperature",
                listOf("Red", "Green", "Blue")
            ){}

            RadioFeedbackTopic(
                "Foo",
                listOf("Alpha", "Beta", "Gamma")
            ){}
          }
        }
      }
    }
  }
}
