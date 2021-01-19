package com.example.jetpackcomposeexplorer.presentation.components.frame

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.model.course.Chapter

@Composable
fun ChaptersList(
    chapters: List<Chapter>
) {

}

@Preview
@Composable
fun ChaptersListPreview() {
  MaterialTheme {
    Surface {
      Column {
        ChaptersList(

        )
      }
    }
  }
}
