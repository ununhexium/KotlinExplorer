package  com.example.jetpackcomposeexplorer.ui.frame

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.model.ServiceLocator


@Composable
fun HomePage(/*nav: NavHostController*/) {
  val name by ServiceLocator.viewModel.alias.observeAsState()
  Column {
    Text("Home page")
    Text("Hello $name")
//    Button(onClick = { nav.navigate(Destinations.tutorial) }) {
//      Icon(Icons.Default.ArrowForward)
//    }
  }
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

