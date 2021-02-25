package net.lab0.kotlinexplorer.framework.presentation.composable.frame

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.framework.presentation.composable.chapter.ChapterCard
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.LessonListItem
import net.lab0.kotlinexplorer.framework.presentation.composable.DefaultVerticalSpacer

@Composable
fun ExpansibleCard(
    card: @Composable RowScope.() -> Unit,
    expansion: @Composable ColumnScope.() -> Unit,
    expanded: Boolean = false,
    setExpanded: (Boolean) -> Unit,
) {
  CardWithExpansion(
      card = {
        Row(
            modifier = Modifier.clickable(
                onClick = { setExpanded(!expanded) }
            ),
        ) {
          card()
        }
      }
  ) {
    if (expanded) {
      Column {
        expansion()
        DefaultVerticalSpacer()
        BottomText("SHOW LESS", setExpanded, expanded)
      }
    } else {
      Column(modifier = Modifier.fillMaxWidth()) {
        BottomText("SHOW MORE", setExpanded, expanded)
      }
    }
  }
}

@Composable
private fun BottomText(text:String, setExpanded: (Boolean) -> Unit, expanded: Boolean) {
  Row(
      modifier = Modifier
          .fillMaxWidth()
          .clickable(
              onClick = { setExpanded(!expanded) }
          ),
      horizontalArrangement = Arrangement.Center,
  ) {
    Text(text, color = MaterialTheme.colors.secondary)
  }
}

@Preview
@Composable
fun ExpansibleCardPreview_collapsed() {
  MaterialTheme {
    Surface(
        color = Color(0xFF4CAF50)
    ) {
      Column(
          modifier = Modifier.padding(20.dp)
      ) {
        ExpansibleCard(
            { ChapterCard(chapter = dummyChapter1) },
            {
              LessonListItem(lesson = lesson1, {})
              LessonListItem(lesson = lesson2, {})
              LessonListItem(lesson = lesson3, {})
            },
            false,
            {},
        )
      }
    }
  }
}


@Preview
@Composable
fun ExpansibleCardPreview_expanded() {
  MaterialTheme {
    Surface(
        color = Color(0xFF4CAF50)
    ) {
      Column(
          modifier = Modifier.padding(20.dp)
      ) {
        ExpansibleCard(
            { ChapterCard(chapter = dummyChapter1) },
            {
              LessonListItem(lesson = lesson1, {})
              LessonListItem(lesson = lesson2, {})
              LessonListItem(lesson = lesson3, {})
            },
            true,
            {},
        )
      }
    }
  }
}
