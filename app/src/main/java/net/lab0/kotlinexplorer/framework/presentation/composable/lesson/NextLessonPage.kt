package net.lab0.kotlinexplorer.framework.presentation.composable.lesson

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.business.domain.Lesson
import net.lab0.kotlinexplorer.framework.presentation.composable.BigVerticalSpacer

@Composable
fun NextLessonPage(
  goToChapters: () -> Unit,
  nextLesson: Lesson?,
  goToNextLesson: (() -> Unit)?,
) {
  Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
    Button(
      modifier = Modifier.align(Alignment.CenterHorizontally),
      onClick = goToChapters,
    ) {
      Text("Back to Chapters")
    }

    BigVerticalSpacer()

    if (nextLesson != null && goToNextLesson != null) {
      Button(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        onClick = goToNextLesson,
      ) {
        Text("Next: ${nextLesson.title}")
      }
    }
  }
}

@Preview
@Composable
fun NextLessonPagePreview() {
  MaterialTheme {
    Surface(
      color = Color(0xFF4CAF50)
    ) {
      Column(
        modifier = Modifier.padding(20.dp)
      ) {
        Surface(
          color = MaterialTheme.colors.surface
        ) {
          NextLessonPage(
            {},
            null,
            null
          )
        }
      }
    }
  }
}