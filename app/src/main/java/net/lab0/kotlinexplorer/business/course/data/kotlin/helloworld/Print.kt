package net.lab0.kotlinexplorer.business.course.data.kotlin.helloworld

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as ph

object Print : LessonImpl(
  id = "kotlin.helloworld.print",
  title = "Printing",
  pages = listOf(
    LessonPage.CodeQuestionPage(
      title = "Use Print",
      question = """Call the `print` function""",
      snippet = """${ph(0)}("Kotlin")""",
      explanation =
      """
The `print` function name is case sensitive,
you must use the name exactly as it is declared: `print`.

Printing allows you to show text to the user.
""",
      answer = listOf("print"),
      confusion = listOf("Print", "PRINT"),
    ),

    LessonPage.CodeQuestionPage(
      title = "Print Kotlin",
      question = """Print `Kotlin`""",
      snippet = """print(${ph(0)})""",
      explanation =
      """
When printing text, the text must be between double quotes `"`.
""",
      answer = listOf(""""Kotlin""""),
      confusion = listOf("Kotlin"),
    ),

    LessonPage.CodeQuestionPage(
      title = "Call the print function",
      question = """Call the print function""",
      snippet = """print${ph(0)}"Hi! :)"${ph(1)}""",
      explanation =
      """
To call the `print` function, you must use `(` and `)`.
""",
      answer = listOf("(", ")"),
      confusion = listOf("{", "}"),
    ),

    LessonPage.CodeQuestionPage(
      title = "Finished",
      question = """Print `Finished!`""",
      snippet = """${ph(0)}${ph(1)}${ph(2)}${ph(3)}${ph(2)}${ph(4)}""",
      explanation =
      """
Remember this, it will be used a lot later ;).
""",
      answer = listOf("print", "(", "\"", "Finished!", ")"),
      confusion = listOf("'"),
    ),

    LessonPage.InfoPage(
      "Summary",
      """
Printing is the most primitive way to communicate with the user,
but that's the easiest way to start showing information.
"""
    )
  )
)
