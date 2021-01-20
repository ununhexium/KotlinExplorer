package com.example.jetpackcomposeexplorer.presentation.components.frame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CheckCircleOutline
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
    onPlay: (() -> Unit)? = null,
) {
  Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
  ) {

    val color = MaterialTheme.colors.primary

    Row(
        modifier = Modifier.align(Alignment.CenterVertically),
        horizontalArrangement = Arrangement.Start
    ) {
      if (lesson.completed) {
        Icon(
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.CenterVertically),
            imageVector = Icons.Default.CheckCircle,
            tint = color
        )
        Text(
            text = lesson.title,
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.CenterVertically),
            color = color
        )
      } else {
        val gray = Color.Gray
        Icon(
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.CenterVertically),
            imageVector = Icons.Default.CheckCircleOutline,
            tint = gray
        )
        Text(
            text = lesson.title,
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.CenterVertically)
        )
      }
    }

    onPlay?.let {
      Button(onClick = onPlay) {
        Icon(Icons.Default.PlayArrow)
      }
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
        LessonListItem(lesson1)
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
        LessonListItem(lesson2) {}
      }
    }
  }
}
