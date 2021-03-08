package net.lab0.kotlinexplorer.business.course.data.kotlin.helloworld

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object String : LessonImpl(
  id = "kotlin.helloworld.string",
  title = "String",
  pages = listOf(
    // "hello"
    LessonPage.CodeQuestionPage(
      title = "Declaration",
      question = "Declare a string containing `hello`",
      snippet =
      """
${p(0)}hello${p(1)}
""",
      explanation = """
Double quotes `"` are for quoting strings.

Simple quotes `'` are for quoting single characters.
""",
      answer = listOf("\"", "\""),
      confusion = listOf("'", "'"),
    ),

    // "hello world"
    LessonPage.CodeQuestionPage(
      title = "Spaces",
      question = "Strings can contain several words. Declare a `Hello World` string.",
      snippet =
      """
${p(0)}${p(1)}${p(2)}${p(3)}${p(4)}
""",
      explanation = """

""",
      answer = listOf("\"", "Hello", " ", "World", "\""),
      confusion = listOf("'", "'"),
    ),

    // "hello world"
    LessonPage.CodeQuestionPage(
      title = "Emojis",
      question = "Strings contain almost any character. Declare a string `\uD83D\uDC14 ➡ \uD83E\uDD5A !`.",
      snippet =
      """
${p(0)}${p(1)} ➡ ${p(2)} !${p(0)}
""",
      explanation = """
You can use most unicode characters.
""",
      answer = listOf("\"", "🐔", "🥚"),
      confusion = listOf("'", "'"),
    ),
  )
)
