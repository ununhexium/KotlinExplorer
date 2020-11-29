package com.example.jetpackcomposeexplorer.ui.tutorial

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.model.ServiceLocator
import com.example.jetpackcomposeexplorer.model.tutorialCorrectNamePage
import com.example.jetpackcomposeexplorer.model.tutorialIntroductionPage
import com.example.jetpackcomposeexplorer.model.tutorialPage1
import com.example.jetpackcomposeexplorer.model.tutorialPage2
import com.example.jetpackcomposeexplorer.model.tutorialWrongNamePage

@Composable
fun TutorialHomePage(modifier: Modifier = Modifier) {
  val (name, setName) = remember { mutableStateOf(ServiceLocator.viewModel.name) }
  val (page, setPage) = remember { mutableStateOf(tutorialIntroductionPage) }

  when (page) {
    tutorialIntroductionPage -> TutorialIntroduction { setPage(tutorialPage1) }
    tutorialPage1 -> TutorialPage1(
        name,
        setName,
        {
          with(ServiceLocator.viewModel) {
            this.name = it
          }
          setPage(tutorialPage2)
        }
    )
    tutorialPage2 -> TutorialPage2(name) {
      if (it) {
        setPage(tutorialCorrectNamePage)
      } else {
        setPage(tutorialWrongNamePage)
      }
    }
    tutorialWrongNamePage -> TutorialWrongNamePage(
        name = name,
        userAnswer = {
          setPage(tutorialCorrectNamePage)
        }
    )
    tutorialCorrectNamePage -> TutorialCorrectNamePage(name)
  }
}

@Preview
@Composable
fun PreviewTutorialHomePage() {
  MaterialTheme {
    Surface {
      TutorialHomePage()
    }
  }
}
