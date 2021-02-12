package net.lab0.jetpackcomposeexplorer.framework.presentation.components.frame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
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
import java.text.NumberFormat

@Composable
fun ChapterCard(
    chapter: ChapterCardData,
) {
  Surface {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
      Box(
          modifier = Modifier.padding(8.dp),
          contentAlignment = Alignment.Center,
      ) {
        CircularProgressIndicator(
            progress = chapter.completion,
            modifier = Modifier
                .padding(8.dp)
                .preferredSize(64.dp)
        )
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

@Preview
@Composable
fun ChapterCardPreview() {
  MaterialTheme {
    Surface(
        color = Color(0xFF4CAF50)
    ) {
      Column(
          modifier = Modifier.padding(20.dp)
      ) {
        ChapterCard(
            dummyChapter1
        )
      }
    }
  }
}