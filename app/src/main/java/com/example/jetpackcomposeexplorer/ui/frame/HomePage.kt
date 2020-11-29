package  com.example.jetpackcomposeexplorer.ui.frame

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.ui.tooling.preview.Preview

@Composable
fun HomePage(modifier: Modifier = Modifier) {
  Text("Home page")
}

@Preview
@Composable
fun PreviewHomePage() {
  MaterialTheme {
    Surface {
      HomePage()
    }
  }
}
