package com.example.jetpackcomposeexplorer.ui.frame

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.ui.markdown.MarkdownSample
import com.example.jetpackcomposeexplorer.ui.navigation.Destinations.home
import com.example.jetpackcomposeexplorer.ui.navigation.Destinations.markdownSample
import com.example.jetpackcomposeexplorer.ui.navigation.Destinations.tutorial
import com.example.jetpackcomposeexplorer.ui.tutorial.TutorialHomePage

@Composable
fun Body() {
    val state = rememberScaffoldState()
    val nav = rememberNavController()

    Scaffold(
        scaffoldState = state,
        bodyContent = { SimpleNav(nav) },
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Button(
                            onClick = { state.drawerState.open() }
                        ) {
                            Icon(Icons.Default.Menu)
                        }
                        Text(text = "Jetpack Explorer")
                    }
                }
            )
        },
        drawerContent = {
            ExploreDrawer(
                nav,
                onSelection = {
                    state.drawerState.close()
                }
            )
        }
    )
}

/**
 * The navController is created automatically by
 * the NavHost composable and is only available
 * inside the NavGraph using AmbientNavController.current
 */
@Composable
fun SimpleNav(nav: NavHostController) {
    NavHost(nav, startDestination = home) {
        composable(home) {
            HomePage(nav)
        }
        composable(tutorial) {
            TutorialHomePage(nav)
        }
        composable(markdownSample) {
            MarkdownSample(nav)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBody() {
    Body()
}
