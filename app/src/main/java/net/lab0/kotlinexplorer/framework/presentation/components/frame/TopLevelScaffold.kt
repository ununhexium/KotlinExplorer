package net.lab0.kotlinexplorer.framework.presentation.components.frame

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopLevelScaffold(
    title:String,
    scaffoldState: ScaffoldState,
    onProfileSelected: () -> Unit,
    bodyContent: @Composable (PaddingValues) -> Unit,
) {
  Scaffold(
      scaffoldState = scaffoldState,
      drawerContent = {
        ExploreDrawer(
            onProfile = {
              scaffoldState.drawerState.close()
              onProfileSelected()
            },
        )
      },
      topBar = {
        TopAppBar(
            title = {
              Text(
                  text = title,
                  style = MaterialTheme.typography.h4,
              )
            },
            navigationIcon = {
              IconButton(
                  onClick = {
                    scaffoldState.drawerState.open()
                  }
              ) {
                Icon(Icons.Default.Menu)
              }
            },
            elevation = 4.dp,
        )
      },
      bodyContent = bodyContent
  )
}


@Preview
@Composable
fun TopLevelScaffoldPreview() {
  val state = rememberScaffoldState()

  MaterialTheme {
    Surface(
        color = Color(0xFF4CAF50)
    ) {
      Column(
          modifier = Modifier.padding(20.dp)
      ) {
        Surface(
            color = MaterialTheme.colors.surface
        ) {
          TopLevelScaffold(
              "The Section",
              state,
              {},
          ){
            Text("Body")
          }
        }
      }
    }
  }
}
