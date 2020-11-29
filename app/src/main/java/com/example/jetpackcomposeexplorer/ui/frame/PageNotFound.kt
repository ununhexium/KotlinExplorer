package  com.example.jetpackcomposeexplorer.ui.frame

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.model.ServiceLocator
import com.example.jetpackcomposeexplorer.model.homePage

@Composable
fun PageNotFound(notFound: String) {
  Column {
    val modifier = Modifier.align(Alignment.CenterHorizontally)
    Text(
        "Woops",
        modifier,
        style = MaterialTheme.typography.h1,
        color = Color.DarkGray
    )
    Spacer(modifier = Modifier.height(30.dp))
    Text(
        """
          |The clever monkey that developed this app misused its own code. 🤦
          |
          |Maybe that will be fixed later... who knows...
          |
          |Until then, here is a button to go back to the home page.
        """.trimMargin(),
        modifier,

        )
    Spacer(modifier = Modifier.height(30.dp))
    Button(onClick = { ServiceLocator.state.goToPage(homePage) }, modifier) {
      Text("Go home or go drunk!")
    }
    Spacer(modifier = Modifier.height(30.dp))
    Text(
        "Technically, there is no page for the id '$notFound'",
        style = MaterialTheme.typography.body2,
        color = Color.Gray
    )
  }
}

@Preview
@Composable
fun PreviewPageNotFound() {
  MaterialTheme {
    Surface {
      PageNotFound("somePageId")
    }
  }
}
