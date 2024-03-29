package net.lab0.kotlinexplorer.framework.presentation.composable.frame.topbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun KTopAppBar(
  scaffoldState: ScaffoldState,
  title:String,
) {
  val coroutineScope = rememberCoroutineScope()

  val color = MaterialTheme.colors.onBackground

  TopAppBar(
    title = {
      Text(
        text = title,
        style = MaterialTheme.typography.h4,
        color = color,
      )
    },
    navigationIcon = {
      IconButton(
        onClick = {
          coroutineScope.launch {
            scaffoldState.drawerState.open()
          }
        },
      ) {
        Icon(
          Icons.Default.Menu,
          contentDescription = "Menu",
          tint = color,
        )
      }
    },
    elevation = 4.dp,
  )
}

@Preview
@Composable
private fun TopAppBarPreview() {
  MaterialTheme {
    Surface(
      color = Color(0xFF4CAF50)
    ) {
      Column(
        modifier = Modifier.padding(20.dp)
      ) {
        Surface(
          color = MaterialTheme.colors.background
        ) {
          KTopAppBar(
            rememberScaffoldState(),
            "Hello"
          )
        }
      }
    }
  }
}