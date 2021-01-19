package com.example.jetpackcomposeexplorer.presentation.components.frame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeexplorer.model.course.data.kotlin.kotlin
import java.text.NumberFormat
import kotlin.random.Random


// design: https://www.designcrowd.de/design/15152008
@Composable
fun ChaptersList(
    chapters: List<ChapterData>,
) {
  Column {
    chapters.forEach { chapter ->
      Card(
          modifier = Modifier
              .fillMaxWidth()
              .padding(top = 8.dp, start = 8.dp, end = 8.dp),
          shape = MaterialTheme.shapes.medium,
          elevation = 4.dp,
      ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
          Box(
              modifier = Modifier.padding(8.dp),
              contentAlignment = Alignment.Center,
          ) {
            CircularProgressIndicator(chapter.completion, modifier = Modifier.preferredSize(64.dp))
            Text(
                NumberFormat.getPercentInstance().format(chapter.completion),
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.body1,
            )
          }
          Column(
              verticalArrangement = Arrangement.Center,
          ) {
            Text(
                text = chapter.title,
                style = MaterialTheme.typography.h6,
            )
            Text(
                text = "${chapter.lessons.size} LESSONS",
                style = MaterialTheme.typography.body1,
                color = Color.Gray,
            )
          }
        }
      }

    }
  }
}

@Preview
@Composable
fun ChaptersListPreview() {
  MaterialTheme {
    Surface {
      Column {
        ChaptersList(
            kotlin.map {
              ChapterData(
                  it.title,
                  Random.nextFloat(),
                  it.lessons.map { _ ->
                    LessonData("Foo")
                  }
              )
            }
        )
      }
    }
  }
}
