package com.example.jetpackcomposeexplorer.model.course

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
      val answer: String,
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
          answer: String,
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
