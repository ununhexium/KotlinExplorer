package net.lab0.kotlinexplorer.framework.presentation.composable.lesson

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.LessonPageHeader

@Composable
fun LessonPage(
    progress: Float,
    title: String,
    page: @Composable ColumnScope.() -> Unit,
) {
  Column(
      modifier = Modifier.fillMaxHeight(),
      verticalArrangement = Arrangement.SpaceBetween
  ) {
    LessonPageHeader(
        title = title,
        backAction = { /*TODO: back action to previous page (but no second chance for the test)*/ },
        reportMistakeAction = { /*TODO report mistake*/ }
    )
    page()
    LinearProgressIndicator(
        progress = progress,
        modifier = Modifier.fillMaxWidth().height(4.dp)
    )
  }
}

@Preview
@Composable
fun LessonPagePreview() {
  MaterialTheme {
    Surface {
      Column {
        LessonPage(
            0.116f,
            "Somewhere over the rainbow"
        ) {
          Surface(
              modifier = Modifier.fillMaxSize(),
              color = Color.Gray
          ) {

          }
        }
      }
    }
  }
}

@Preview
@Composable
fun LessonPagePreview_singleButton() {
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
          LessonPage(progress = 1.0f, title = "Foo") {
            Button(
                onClick = {},
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {
              Text("Finished")
            }
          }
        }
      }
    }
  }
}
