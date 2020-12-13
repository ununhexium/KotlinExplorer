package  com.example.jetpackcomposeexplorer.ui.frame

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.ui.navigation.Destinations

@Composable
fun ExploreDrawer(
    nav: NavHostController,
    onSelection: () -> Unit
) {
    Column {
        Text(
            "Tutorial",
            modifier = Modifier.clickable(
                onClick = {
                    onSelection()
                    nav.navigate(Destinations.tutorial)
                }
            ),
            style = MaterialTheme.typography.h2
        )
        Text(
            "Markdown",
            modifier = Modifier.clickable(
                onClick = {
                    onSelection()
                    nav.navigate(Destinations.markdownSample)
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
                    rememberNavController(),
                    onSelection = { }
                )
            }
        }
    }
}
