package  com.example.jetpackcomposeexplorer.ui.frame

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.model.homePage
import com.example.jetpackcomposeexplorer.model.notFoundPage

@Composable
fun Page(pageId: String) {
  when (pageId) {
    homePage -> HomePage()
    else -> PageNotFound(pageId)
  }
}

@Preview
@Composable
fun PreviewPage() {
  MaterialTheme {
    Surface {
      Page(pageId = notFoundPage)
    }
  }
}
