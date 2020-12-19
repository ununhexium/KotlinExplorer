package com.example.jetpackcomposeexplorer.ui.tutorial

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.model.ServiceLocator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun TutorialHomePage(nav: NavHostController) {
  val tutoNav = rememberNavController()

  // pages
  val startDestination = "introduction"
  val page1 = "page1"
  val page2 = "page2"
  val correct = "correct"
  val wrong = "wrong"

  val name by ServiceLocator.viewModel.alias.observeAsState(ServiceLocator.viewModel.alias.value)

  NavHost(tutoNav, startDestination = startDestination) {
    composable(startDestination) {
      TutorialIntroduction { tutoNav.navigate(page1) }
    }
    composable(page1) {
      val (getName, setName) = remember { mutableStateOf(ServiceLocator.viewModel.alias.value) }
      TutorialPage1(
          getName,
          setName,
          {
            ServiceLocator.viewModel.setAlias(it)
            tutoNav.navigate(page2)
          }
      )
    }
    composable(page2) {
      TutorialPage2(name) {
        if (it) {
          tutoNav.navigate(correct)
        } else {
          tutoNav.navigate(wrong)
        }
      }
    }
    composable(wrong) {
      TutorialWrongNamePage(
          name = name,
          userAnswer = { tutoNav.navigate(correct) }
      )
    }
    composable(correct) {
      TutorialCorrectNamePage(name = name) {
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
