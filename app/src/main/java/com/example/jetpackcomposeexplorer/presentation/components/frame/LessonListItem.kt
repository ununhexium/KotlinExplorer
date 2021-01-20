package com.example.jetpackcomposeexplorer.presentation.components.frame

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LessonListItem(lesson: LessonListItemData) {
  Row(modifier = Modifier.fillMaxWidth()) {
    if (lesson.completed) {

      val color = MaterialTheme.colors.secondary

      Icon(
          modifier = Modifier.padding(4.dp),
          imageVector = Icons.Default.CheckCircle,
          tint = color
      )
      Text(lesson.title, modifier = Modifier.padding(4.dp), color = color)
    } else {
      val gray = Color.Gray
      Icon(
          modifier = Modifier.padding(4.dp),
          imageVector = Icons.Default.CheckCircleOutline,
          tint = gray
      )
      Text(lesson.title, modifier = Modifier.padding(4.dp))
    }
  }
}

@Preview
@Composable
fun LessonListItemPreview_completed() {
  MaterialTheme {
    Surface(
        color = Color(0xFFeeeeee)
    ) {
      Column(
          modifier = Modifier.padding(20.dp)
      ) {
        LessonListItem(
            LessonListItemData(
                "Lesson 1",
                true
            )
        )
      }
    }
  }
}

@Preview
@Composable
fun LessonListItemPreview_todo() {
  MaterialTheme {
    Surface(
        color = Color(0xFFeeeeee)
    ) {
      Column(
          modifier = Modifier.padding(20.dp)
      ) {
        LessonListItem(
            LessonListItemData(
                "Lesson 2",
                false
            )
        )
      }
    }
  }
}
