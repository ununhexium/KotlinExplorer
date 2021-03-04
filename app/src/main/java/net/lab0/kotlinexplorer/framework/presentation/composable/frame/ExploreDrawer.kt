package  net.lab0.kotlinexplorer.framework.presentation.composable.frame

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ExploreDrawer(
  onProfile: () -> Unit,
  onLessonsSelected: () -> Unit,
) {
  Column {
    Text(
      "Profile",
      modifier = Modifier.clickable(
        onClick = {
          onProfile()
        }
      ),
      style = MaterialTheme.typography.h3,
      color = MaterialTheme.colors.onBackground,
    )
    Text(
      "Lessons",
      modifier = Modifier.clickable(
        onClick = {
          onLessonsSelected()
        }
      ),
      style = MaterialTheme.typography.h3,
      color = MaterialTheme.colors.onBackground,
    )

  }
}

@Preview
@Composable
fun PreviewExploreDrawer() {
  MaterialTheme {
    Surface {
      Column {
        ExploreDrawer({ }, {})
      }
    }
  }
}
