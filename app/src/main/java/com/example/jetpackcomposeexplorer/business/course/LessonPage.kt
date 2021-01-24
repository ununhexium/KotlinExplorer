package com.example.jetpackcomposeexplorer.business.course

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
      val answer: (List<String>) -> String,
      val choices: List<String>,
      val answerValidator: (List<String>) -> Boolean,
  ) : LessonPage(
      title
  ) {
    companion object {
      fun singleChoice(
          title: String,
          question: String,
          snippet: String,
          answer: (List<String>) -> String,
          choice: String,
      ) = CodeQuestionPage(
          title = title,
          question = question,
          snippet = snippet,
          answer = answer,
          choices = listOf(choice)
      ) {
        true
      }
    }
  }
}
