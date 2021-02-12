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
        1f * chapter.lessons
            .mapNotNull { lesson ->
              lessonsInProgress.firstOrNull { it.lessonId == lesson.id }
            }
            .filter { lesson ->
              lesson.failureCount == 0
            }
            .count() / chapter.lessons.size
      }

  val lessonCompletionCache = mutableMapOf<String, Float?>()

  fun getLessonCompletion(lessonId: String): Float? {
    return lessonCompletionCache.computeIfAbsent(lessonId) {
      lessonsInProgress.firstOrNull { it.lessonId == lessonId }?.let { lessonProgress ->
        if (lessonProgress.successCount + lessonProgress.failureCount == 0) {
          null
        } else {
          1f * lessonProgress.successCount / (lessonProgress.successCount + lessonProgress.failureCount)
        }
      }
    }
  }
}
