package com.example.jetpackcomposeexplorer.ui.frame

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.ui.tutorial.TutorialHomePage

@Composable
fun Body() {
    SimpleNav()
}

/**
 * The navController is created automatically by
 * the NavHost composable and is only available
 * inside the NavGraph using AmbientNavController.current
 */
@Composable
fun SimpleNav() {
    val nav = rememberNavController()
    NavHost(nav, startDestination = "home") {
        composable("home") {
            HomePage(nav)
        }
        composable("tutorial") {
            TutorialHomePage(nav)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBody() {
    Body()
}
