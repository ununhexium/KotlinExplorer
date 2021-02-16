package net.lab0.kotlinexplorer.framework.presentation.components.frame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LessonListItem(
    lesson: LessonListItemData,
    onPlay: () -> Unit,
) {
  Row(
      modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 4.dp),
      horizontalArrangement = Arrangement.SpaceBetween
  ) {

    Row(
        modifier = Modifier.align(Alignment.CenterVertically),
        horizontalArrangement = Arrangement.Start
    ) {
      val fullyCompleted = lesson.progress != null && lesson.progress >= 1f
      val noProgressPossible = lesson.progress == null
      val iconColor = if (
          lesson.completed && (fullyCompleted || noProgressPossible)
      ) {
        MaterialTheme.colors.primary
      } else {
        Color.Gray
      }

      Box(
          Modifier
              .padding(4.dp)
              .align(Alignment.CenterVertically)
      ) {
        val progress = lesson.progress
        CircularProgressIndicator(
            when (progress) {
              null -> 0f
              1f -> 0f
              else -> progress
            },
            modifier = Modifier.align(Alignment.Center),
        )
        if (lesson.completed) {
          Icon(
              modifier = Modifier.align(Alignment.Center),
              imageVector = Icons.Default.CheckCircle,
              tint = iconColor,
          )
        }
      }

      Text(
          text = lesson.title,
          modifier = Modifier
              .padding(4.dp)
              .align(Alignment.CenterVertically),
          color = if (lesson.highlighted) MaterialTheme.colors.primary else Color.Gray,
      )
    }

    Button(onClick = onPlay) {
      Icon(Icons.Default.PlayArrow)
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
        LessonListItem(lesson1) {}
      }
    }
  }
}

@Preview
@Composable
fun LessonListItemPreview_list() {
  MaterialTheme {
    Surface(
        color = Color(0xFFeeeeee)
    ) {
      Column(
          modifier = Modifier.padding(20.dp)
      ) {
        LessonListItem(lesson1) {}
        LessonListItem(lesson2) {}
        LessonListItem(lesson3) {}
        LessonListItem(lesson3b) {}
        LessonListItem(lesson3c) {}
      }
    }
  }
}
