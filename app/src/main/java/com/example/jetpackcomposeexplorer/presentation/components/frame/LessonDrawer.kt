package com.example.jetpackcomposeexplorer.presentation.components.frame

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeexplorer.model.course.Chapter
import com.example.jetpackcomposeexplorer.model.course.Lesson
import com.example.jetpackcomposeexplorer.model.course.LessonPage
import com.example.jetpackcomposeexplorer.ui.frame.MediumHorizontalSpacer

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
        style = MaterialTheme.typography.h3
    )

    Indent {
      Text(
          text = lesson,
          style = MaterialTheme.typography.h4,
      )
    }

    if (currentPage == null) {
      lessonPages.forEach { page ->
        Indent(2) {
          Text(
              text = lesson,
              style = MaterialTheme.typography.h4,
          )
        }
      }
    } else {
      lessonPages.takeWhile { it != currentPage }.forEach { page ->
        Indent(2){
          Text(
              text = page,
              style = MaterialTheme.typography.h4,
          )
        }
      }

      lessonPages.find { it == currentPage }?.let { page ->
        Indent(2) {
          Text(
              text = page,
              style = MaterialTheme.typography.h4,
              color = MaterialTheme.colors.primary,
          )
        }
      }

      lessonPages.takeLastWhile { it != currentPage }.forEach { lesson ->
        Indent(2) {
          Text(
              text = lesson,
              style = MaterialTheme.typography.h4,
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
