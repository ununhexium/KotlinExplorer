package  com.example.jetpackcomposeexplorer.ui.frame

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.ui.navigation.Destinations

@Composable
fun HomePage(nav: NavHostController) {
  Column {
    Text("Home page")
    Button(onClick = { nav.navigate(Destinations.tutorial) }) {
      Icon(Icons.Default.ArrowForward)
    }
  }
}


@Preview
@Composable
fun PreviewHomePage() {
  MaterialTheme {
    Surface {
      HomePage(rememberNavController())
    }
  }
}

