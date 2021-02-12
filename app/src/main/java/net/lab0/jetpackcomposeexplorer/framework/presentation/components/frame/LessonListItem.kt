package net.lab0.jetpackcomposeexplorer.framework.presentation.components.frame

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
      modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 4.dp),
      horizontalArrangement = Arrangement.SpaceBetween
  ) {

    Row(
        modifier = Modifier.align(Alignment.CenterVertically),
        horizontalArrangement = Arrangement.Start
    ) {
      if (lesson.completed) {
        val color = when {
          lesson.progress == null -> Color.Gray
          lesson.progress < 1f -> Color.Gray
          else -> MaterialTheme.colors.primary
        }


        Box(
            Modifier
                .padding(4.dp)
                .align(Alignment.CenterVertically)
        ) {
          lesson.progress?.let {
            CircularProgressIndicator(
                it,
                modifier = Modifier.align(Alignment.Center),
            )
          }
          Icon(
              modifier = Modifier.align(Alignment.Center),
              imageVector = Icons.Default.CheckCircle,
              tint = color,
          )
        }

        Text(
            text = lesson.title,
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.CenterVertically),
            color = MaterialTheme.colors.primary,
        )
      } else {
        val gray = Color.Gray
        Box(
            Modifier
                .padding(4.dp)
                .align(Alignment.CenterVertically)
        ) {
          // always shows 0, jst a placeholder to align with the cases where progress is shown
          CircularProgressIndicator(
              0f,
              modifier = Modifier.align(Alignment.Center),
          )
          Icon(
              modifier = Modifier.align(Alignment.Center),
              imageVector = Icons.Default.CheckCircleOutline,
              tint = gray,
          )
        }
        Text(
            text = lesson.title,
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.CenterVertically),
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
      }
    }
  }
}
