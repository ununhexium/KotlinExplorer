package com.example.jetpackcomposeexplorer.framework.presentation.components.frame

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeexplorer.framework.ui.frame.MediumVerticalSpacer

typealias PlayLessonById = (String) -> Unit

@Composable
fun ChapterList(
    chapters: List<ChapterCardData>,
    /**
     * Play the selected (lesson)
     */
    onPlay: PlayLessonById,
) {
  LazyColumn {
    itemsIndexed(chapters) { _, chapter ->
      ExpansibleCard(
          card = {
            ChapterCard(chapter = chapter)
          },
          expansion = {
            chapter.lessons.forEach { lesson ->
              LessonListItem(
                  lesson = lesson,
                  onPlay = { onPlay(lesson.id) }
              )
            }
          }
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
            chapters = listOf(dummyChapter1, dummyChapter2),
            onPlay = { }
        )
      }
    }
  }
}
