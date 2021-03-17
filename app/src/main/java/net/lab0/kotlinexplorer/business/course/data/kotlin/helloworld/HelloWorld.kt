package net.lab0.kotlinexplorer.business.course.data.kotlin.helloworld

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object HelloWorld : LessonImpl(
  id = "kotlin.helloworld.helloworld",
  title = "Hello World Project",
  pages = listOf(

    // declare main
    LessonPage.CodeQuestionPage(
      title = "Main",
      question = """
Declare the main function.
""",
      snippet = """
${p(0)} main${p(1)}${p(2)} ${p(3)}
    // next steps
${p(4)}
""",
      explanation = "",
      answer = listOf("fun", "(", ")", "{", "}"),
      confusion = listOf("[", "]"),
    ),

    // add print
    LessonPage.CodeQuestionPage(
      title = "Print",
      question = "Call the print function",
      snippet = """
fun main() {
    ${p(0)}${p(1)}
        // next steps
    ${p(2)}
}
""",
      explanation = "",
      answer = listOf("print", "(", ")"),
      confusion = listOf("Print", "{", "}")
    ),

    // put hello world in a string
    LessonPage.CodeQuestionPage(
      title = "String",
      question = """
Declare a string that contains `Hello World!`.
""",
      snippet = """
fun main() {
  print(
      ${p(0)}${p(1)} ${p(2)}!${p(3)}
  )
}
""",
      explanation = "",
      answer = listOf("\"", "Hello ", "World", "\""),
      confusion = listOf("'", "\""),
    ),

    // done
    LessonPage.InfoPage(
      title = "Done!",
      """
You wrote your first program. Congratulations!

You can now keep learning for your next project.
"""
    )
  )
)
