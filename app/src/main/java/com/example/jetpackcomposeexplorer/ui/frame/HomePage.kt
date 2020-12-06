package  com.example.jetpackcomposeexplorer.ui.frame

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview
import androidx.navigation.compose.navigate

@Composable
fun HomePage(nav: NavHostController) {
  Column {
    Text("Home page")
    Button(onClick = {nav.navigate("tutorial")}) {
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

