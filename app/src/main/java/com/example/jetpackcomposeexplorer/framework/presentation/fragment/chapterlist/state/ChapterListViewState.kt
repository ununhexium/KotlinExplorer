package com.example.jetpackcomposeexplorer.framework.presentation.fragment.chapterlist.state

import com.example.jetpackcomposeexplorer.business.domain.Chapter
import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.mvi.UiData

data class ChapterListViewState(
    val chapters: List<Chapter>,
    val lessonsInProgress: List<LessonProgress>,
) : UiData {
  val completionCache = mutableMapOf<Chapter, Float>()

  fun getChapterCompletion(chapter: Chapter): Float =
      completionCache.computeIfAbsent(chapter) {
        val lessonIdsInSelectedChapter = chapter.lessons.map { it.id }

        1f * lessonsInProgress.filter {
          it.lessonId in lessonIdsInSelectedChapter
        }.size / chapter.lessons.size
      }
}
