package net.lab0.kotlinexplorer.business.domain

sealed class LessonPage(
    val title: String,
) {
  class InfoPage(
      title: String,
      val markdown: String,
  ) : LessonPage(
      title
  )

  class CodeQuestionPage(
      title: String,
      val question: String,
      val snippet: String,
      val explanation: String,
      val answer: List<String>,
      val confusion: List<String> = listOf(),
  ) : LessonPage(
      title
  )

  class MultipleChoice(
      title: String,
      val question: String,
      val explanation: String,
      val choices: List<String>,
      val answer: Set<Int>,
  ) : LessonPage(title)

}
