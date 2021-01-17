package  com.example.jetpackcomposeexplorer.ui.frame

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.jetpackcomposeexplorer.R

@Composable
fun ExploreDrawer(
    nav: NavController,
    onSelection: () -> Unit,
) {
  Column {
    Text(
        "Tutorial",
        modifier = Modifier.clickable(
            onClick = {
              onSelection()
//                    nav.navigate(Destinations.tutorial)
            }
        ),
        style = MaterialTheme.typography.h2
    )
    Text(
        "Markdown",
        modifier = Modifier.clickable(
            onClick = {
              onSelection()
//                    nav.navigate(Destinations.markdownSample)
            }
        ),
        style = MaterialTheme.typography.h2
    )
  }
}

@Preview
@Composable
fun PreviewExploreDrawer() {
  MaterialTheme {
    Surface {
      Column {
        ExploreDrawer(
//                    rememberNavController(),
            onSelection = { }
        )
      }
    }
  }
}
