package  com.example.jetpackcomposeexplorer.ui.frame

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.ui.tooling.preview.Preview

@Composable
fun ExploreDrawer(
    onTutorialSelection: () -> Unit
) {
  Text(
      "Tutorial",
      modifier = Modifier.clickable(onClick = onTutorialSelection),
      style = MaterialTheme.typography.h2
  )
}

@Preview
@Composable
fun PreviewExploreDrawer() {
  MaterialTheme {
    Surface {
      Column {
        ExploreDrawer(
            onTutorialSelection = {

            }
        )
      }
    }
  }
}
