package net.lab0.jetpackcomposeexplorer.framework.presentation.fragment.chapterlist.state

import net.lab0.jetpackcomposeexplorer.business.domain.Chapter
import net.lab0.jetpackcomposeexplorer.business.domain.LessonProgress
import net.lab0.jetpackcomposeexplorer.mvi.UiData

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
