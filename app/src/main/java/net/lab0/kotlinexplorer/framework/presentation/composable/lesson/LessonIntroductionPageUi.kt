package net.lab0.kotlinexplorer.framework.presentation.composable.lesson

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController

@Composable
fun LessonIntroductionPageUi(navController: NavHostController, title: String) {
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.SpaceEvenly,
  ) {
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .align(Alignment.CenterHorizontally),
      text = title,
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.h3,
      color = MaterialTheme.colors.onBackground,
    )
    Button(
      modifier = Modifier.align(Alignment.CenterHorizontally),
      onClick = {
        navController.navigate(
          LessonScreen.LessonPage.route(0)
        )
      }
    ) {
      Icon(imageVector = Icons.Default.PlayArrow, "Play arrow")
      Text("Start!")
    }
  }
}

@Preview
@Composable
private fun IntroductionUiPreview() {
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
          LessonIntroductionPageUi(rememberNavController(), "Allgemeinmedizin")
        }
      }
    }
  }
}
