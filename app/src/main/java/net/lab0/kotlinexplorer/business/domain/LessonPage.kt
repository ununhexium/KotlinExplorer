package net.lab0.kotlinexplorer.business.domain

import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanksImpl

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
    val choices: List<String> = (answer + confusion).shuffled(),
    val validator: ((List<String>) -> Boolean)? = null
  ) : LessonPage(
    title
  ) {
    val answerSnippet = KotlinCodeWithBlanksImpl(snippet).fill(
      answer
        .mapIndexed { index, it -> index to it }
        .toMap()
    )

    companion object {
      val EMPTY = CodeQuestionPage(
        title = "",
        question = "",
        snippet = "",
        explanation = "",
        answer = listOf(),
        confusion = listOf(),
      )


      val EXAMPLE = CodeQuestionPage(
        title = "The title",
        question = "The question?",
        snippet = """fun main(){println("Hello World!"}""",
        explanation = "Because reasons.",
        answer = listOf("Bar"),
        confusion = listOf("Foo"),
      )
    }
  }

  class MultipleChoice(
    title: String,
    val question: String,
    val explanation: String,
    val choices: List<String>,
    val answer: Set<Int>,
  ) : LessonPage(title) {
    companion object {
      val EMPTY = MultipleChoice(
        title = "",
        question = "",
        explanation = "",
        choices = listOf(),
        answer = setOf(),
      )
    }
  }
}
