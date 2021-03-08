package net.lab0.kotlinexplorer.framework.presentation.activity.lesson

import net.lab0.kotlinexplorer.business.domain.Lesson
import net.lab0.kotlinexplorer.business.domain.LessonPage

data class LessonViewState(
  var lesson: Lesson,
  val answers: Map<LessonPage, AnswerCorrectness> = mapOf(),
) {
  fun answersCount(correctness: AnswerCorrectness) =
    answers.values.count { it == correctness }
}
