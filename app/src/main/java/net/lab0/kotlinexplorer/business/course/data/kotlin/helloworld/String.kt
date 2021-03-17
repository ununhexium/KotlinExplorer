package net.lab0.kotlinexplorer.business.course.data.kotlin.helloworld

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object String : LessonImpl(
  id = "kotlin.helloworld.string",
  title = "Strings",
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

Strings are a series of characters.
They may contain most existing characters.
""",
      answer = listOf("\"", "\""),
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

    // "hello chicks"
    LessonPage.CodeQuestionPage(
      title = "Emojis",
      question = """
Strings contain almost any character.

This includes special characters like `!`, `~`, `^`, ... 
but also all the emojis.

Declare a string that contains chicks `🐣 🐣 cute!`.
""",
      snippet =
      """
${p(0)}${p(1)} ${p(2)} cute!${p(3)}
""",
      explanation = """
You can use most unicode characters.
""",
      answer = listOf("\"", "\uD83D\uDC23", "\uD83D\uDC23", "\""),
      confusion = listOf("'", "'"),
    ),

    // summary
    LessonPage.InfoPage(
      title = "Summary",
      """
That's it for strings, for the moment...


"""
    )
  )
)
