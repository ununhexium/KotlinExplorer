package com.example.jetpackcomposeexplorer.ui.frame

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.model.ServiceLocator
import com.example.jetpackcomposeexplorer.model.tutorialPage1
import com.example.jetpackcomposeexplorer.ui.JetpackComposeExplorerTheme


@Composable
fun Body() {
  val state = rememberScaffoldState()

  JetpackComposeExplorerTheme {
    Surface(color = MaterialTheme.colors.background) {
      Scaffold(
          scaffoldState = state,
          topBar = { ExploreTopAppBar(state) },
          drawerContent = {
            ExploreDrawer(
                onTutorialSelection = {
                  state.drawerState.close()
                  ServiceLocator.viewModel.goToPage(tutorialPage1)
                }
            )
          }
      ) {
        Page(ServiceLocator.viewModel.page)
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewBody() {
  Body()
}
