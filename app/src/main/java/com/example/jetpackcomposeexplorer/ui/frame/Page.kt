package  com.example.jetpackcomposeexplorer.ui.frame

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.model.PageID
import com.example.jetpackcomposeexplorer.model.homePage
import com.example.jetpackcomposeexplorer.model.notFoundPage
import com.example.jetpackcomposeexplorer.model.tutorialHomePage
import com.example.jetpackcomposeexplorer.ui.tutorial.TutorialHomePage

@Composable
fun Page(page: PageID) {
  var topPage = page

  while(topPage.parentPageID != null) {
    topPage = topPage.parentPageID!!
  }

  when (topPage) {
    homePage -> HomePage()
    tutorialHomePage -> TutorialHomePage()
    else -> PageNotFound(page)
  }
}

@Preview
@Composable
fun PreviewPage() {
  MaterialTheme {
    Surface {
      Page(page = notFoundPage)
    }
  }
}
