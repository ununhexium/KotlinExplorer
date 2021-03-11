package net.lab0.kotlinexplorer.framework.presentation.composable.chapter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.framework.presentation.composable.MediumVerticalSpacer
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.ExpansibleCard
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.dummyChapter1
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.dummyChapter2
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.LessonListItem

@Composable
fun ChapterList(
  modifier: Modifier = Modifier,
  chapters: List<ChapterCardData>,
  /**
   * Play the selected (chapter, lesson)
   */
  onPlay: (String, String) -> Unit,
) {
  Column {
    chapters.forEach { chapter ->
      val (expanded, setExpanded) = remember { mutableStateOf(false) }
      expanded to setExpanded

      ExpansibleCard(
        card = {
          ChapterCard(chapter = chapter)
        },
        expansion = {
          chapter.lessons.forEach { lesson ->
            LessonListItem(
              lesson = lesson,
              onPlay = { onPlay(chapter.id, lesson.id) }
            )
          }
        },
        expanded = expanded,
        setExpanded = setExpanded,
      )
      MediumVerticalSpacer()
    }
  }
}

@Preview
@Composable
fun ChapterListPreview() {
  MaterialTheme {
    Surface(
      color = Color(0xFF4CAF50)
    ) {
      Column(
        modifier = Modifier.padding(20.dp)
      ) {
        ChapterList(
          chapters = listOf(
            dummyChapter1,
            dummyChapter2,
            dummyChapter1,
            dummyChapter2,
            dummyChapter1,
            dummyChapter2,
            dummyChapter1,
            dummyChapter2,
          ),
          onPlay = { _: String, _: String -> }
        )
      }
    }
  }
}
