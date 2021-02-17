package net.lab0.kotlinexplorer.business.course.data.kotlin

import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object Standby {
  val a = LessonPage.CodeQuestionPage(
      title = "Mixed feelings",
      question = """
`b` is the result on a previous computation.
You don't know for sure if it's true or false. It could be either.
In this specific example, it is `true`.

What is the type of `unknown`?
""",
      snippet = """
val b = true // or false
val unknown: ${p(0)} =
    if (b) 1 else "false"
""",
      explanation = """
Here, `b` may either be `true` or `false`.

Even if in this specific case you see that `unknown` will be an `Int`,
it's not possible to know this in the general case.

Therefore `unknown` will have a type that is common to both `Int` and `String`.
This type is called `Any`.
""",
      answer = listOf("Any"),
      confusion = listOf("Int", "String"),
  )
}