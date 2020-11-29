package com.example.jetpackcomposeexplorer.ui.frame

import CodeBlock
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.ui.JetpackComposeExplorerTheme
import placeholder


@Composable
fun Body() {
  val state = rememberScaffoldState()

  JetpackComposeExplorerTheme {
    Surface(color = MaterialTheme.colors.background) {
      Scaffold(
          scaffoldState = state,
          topBar = {
            TopAppBar(
                navigationIcon = {
                  Box(
                      Modifier
                          .padding(8.dp)
                          .clickable(
                              onClick = {
                                if (state.drawerState.isOpen) {
                                  state.drawerState.close()
                                } else {
                                  state.drawerState.open()
                                }
                              }
                          )
                  ) {
                    Icon(asset = Icons.Default.Menu)
                  }
                },
                title = { Text("Jetpack Compose Explorer") },
            )
          },
          drawerContent = {
            Text("Drawer is here")
          }
      ) {
        CodeBlock(sourceCode = "Hello $placeholder!")
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewBody() {
  Body()
}