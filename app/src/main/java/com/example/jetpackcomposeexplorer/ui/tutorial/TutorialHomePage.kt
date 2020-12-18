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

  // pages
  val startDestination = "introduction"
  val page1 = "page1"
  val page2 = "page2"
  val correct = "correct"
  val wrong = "wrong"

  NavHost(tutoNav, startDestination = startDestination) {
    composable(startDestination) {
      TutorialIntroduction { tutoNav.navigate(page1) }
    }
    composable(page1) {
      val (name, setName) = remember { mutableStateOf(ServiceLocator.viewModel.alias) }
      TutorialPage1(
          name,
          setName,
          {
            ServiceLocator.viewModel.setAlias(it)
            tutoNav.navigate(page2)
          }
      )
    }
    composable(page2) {
      TutorialPage2(ServiceLocator.viewModel.alias) {
        if (it) {
          tutoNav.navigate(correct)
        } else {
          tutoNav.navigate(wrong)
        }
      }
    }
    composable(wrong) {
      TutorialWrongNamePage(
          name = ServiceLocator.viewModel.alias,
          userAnswer = { tutoNav.navigate(correct) }
      )
    }
    composable(correct) {
      TutorialCorrectNamePage(name = ServiceLocator.viewModel.alias) {
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
