package net.lab0.kotlinexplorer.framework.presentation.composable.lesson

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ReportProblem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LessonPageHeader(
    title: String,
    backAction: () -> Unit,
    reportMistakeAction: () -> Unit,
) {
  Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Button(
        onClick = backAction,
        shape = MaterialTheme.shapes.small.copy(
            topStart = CornerSize(0),
            topEnd = CornerSize(0),
            bottomStart = CornerSize(0),
        ),
    ) {
      Icon(
          imageVector = Icons.Default.ArrowBack,
          contentDescription = "Arrow Back",
      )
    }
    Text(
        text = title,
        style = MaterialTheme.typography.h6,
        modifier = Modifier.align(Alignment.CenterVertically),
        color = MaterialTheme.colors.primary
    )
    Button(
        onClick = reportMistakeAction,
        shape = MaterialTheme.shapes.small.copy(
            topStart = CornerSize(0),
            topEnd = CornerSize(0),
            bottomEnd = CornerSize(0),
        ),
        enabled = true,
    ) {
      Icon(
          imageVector = Icons.Default.ReportProblem,
          contentDescription = "Report Problem"
      )
    }
  }
}

@Preview
@Composable
fun LessonPageHeaderPreview() {
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
          LessonPageHeader(
              title = "Foo Bar",
              backAction = {},
              reportMistakeAction = {}
          )
        }
      }
    }
  }
}
