package com.example.jetpackcomposeexplorer.ui.tutorial

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.model.ServiceLocator

@Composable
fun TutorialHomePage(nav: NavHostController) {
  val tutoNav = rememberNavController()

  NavHost(tutoNav, startDestination = "introduction") {
    composable("introduction") {
      TutorialIntroduction { tutoNav.navigate("page1") }
    }
    composable("page1") {
      val (name, setName) = remember { mutableStateOf(ServiceLocator.viewModel.name) }
      TutorialPage1(
          name,
          setName,
          {
            ServiceLocator.viewModel.name = it
            tutoNav.navigate("page2")
          }
      )
    }
    composable("page2") {
      TutorialPage2(ServiceLocator.viewModel.name) {
        if (it) {
          tutoNav.navigate("correct")
        } else {
          tutoNav.navigate("wrong")
        }
      }
    }
    composable("wrong") {
      TutorialWrongNamePage(
          name = ServiceLocator.viewModel.name,
          userAnswer = { tutoNav.navigate("correct") }
      )
    }
    composable("correct") {
      TutorialCorrectNamePage(name = ServiceLocator.viewModel.name) {
        nav.navigate("home")
      }
    }
  }
}

@Preview
@Composable
fun PreviewTutorialHomePage() {
  MaterialTheme {
    Surface {
      TutorialHomePage(rememberNavController())
    }
  }
}
