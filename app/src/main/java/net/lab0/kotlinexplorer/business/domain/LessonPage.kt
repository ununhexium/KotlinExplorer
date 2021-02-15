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
      val choices: List<String>,
      val answer: List<String>,
  ) : LessonPage(
      title
  ) {
    companion object {
      fun singleChoice(
          title: String,
          question: String,
          snippet: String,
          answer: String,
          choice: String,
      ) = CodeQuestionPage(
          title = title,
          question = question,
          snippet = snippet,
          explanation = answer,
          choices = listOf(choice),
          answer = listOf(choice),
      )
    }
  }

  class MultipleChoice(
      title: String,
      val question: String,
      val explanation: String,
      val choices: List<String>,
      val answer: Set<Int>,
  ) : LessonPage(title)

}
