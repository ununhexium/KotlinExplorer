package net.lab0.kotlinexplorer.framework.presentation.composable.frame

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LessonDrawer(
    chapter: String,
    lesson: String,
    lessonPages: List<String>,
    currentPage: String?,
) {
  Column {
    Text(
        text = chapter,
        style = MaterialTheme.typography.h5,
    )

    Indent {
      Text(
          text = lesson.toUpperCase(),
          style = MaterialTheme.typography.h6.copy(),
          color = Color.Gray,
          fontWeight = FontWeight.Bold,
      )
    }

    if (currentPage == null) {
      lessonPages.forEach { page ->
        Indent(2) {
          Text(
              text = lesson,
              style = MaterialTheme.typography.h6,
          )
        }
      }
    } else {
      lessonPages.takeWhile { it != currentPage }.forEach { page ->
        Indent(2){
          Text(
              text = page,
              style = MaterialTheme.typography.h6,
          )
        }
      }

      lessonPages.find { it == currentPage }?.let { page ->
        Indent(2) {
          Text(
              text = page,
              style = MaterialTheme.typography.h6,
              color = MaterialTheme.colors.primary,
          )
        }
      }

      lessonPages.takeLastWhile { it != currentPage }.forEach { lesson ->
        Indent(2) {
          Text(
              text = lesson,
              style = MaterialTheme.typography.h6,
              color = Color.Gray
          )
        }
      }
    }
  }
}

@Preview
@Composable
fun LessonDrawerPreview_chapter1() {
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
          // TODO: remove the use of XxxData classes and use the model directly?
          LessonDrawer(
              "Chapter 1",
              "Lesson 2",
              listOf(
                  "Page 1",
                  "Page 2",
                  "Page 3",
              ),
              "Page 2"
          )
        }
      }
    }
  }
}
