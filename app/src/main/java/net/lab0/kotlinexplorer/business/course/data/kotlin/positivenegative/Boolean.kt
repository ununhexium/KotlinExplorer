package net.lab0.kotlinexplorer.business.course.data.kotlin.positivenegative

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object Boolean : LessonImpl(
    id = "kotlin.positivenegative.boolean",
    title = "Boolean",
    pages = listOf(
        LessonPage.CodeQuestionPage(
            title = "Make it true",
            question = """
Assign the boolean value `true`.
""",
            snippet = """
val kotlinIsEasy = ${p(0)}
""",
            explanation = """
`true` is a keyword for the boolean true.

As with everything else, Kotlin is case sensitive, therefore `True` is not an equivalent of `True`.

`1` is an integer, not a boolean. `1` is not equivalent to `true`.

`"true"` is a string, not the `true` keyword.
""",
            answer = listOf("true"),
            confusion = listOf("True", "1", """"true""""),
        ),
        LessonPage.CodeQuestionPage(
            title = "Nope",
            question = """
Pretend that you don't like candies.
""",
            snippet = """
val iLikeCandies = ${p(0)}
""",
            explanation = """
The conventions logic of `true` also applies to `false`.

The empty string `""` is not equivalent to false. It's a string, not a boolean.
""",
            answer = listOf("false"),
            confusion = listOf("False", "0", """"false"""", "\"\""),
        ),
        LessonPage.InfoPage(
            title = "That's it",
            """
There are only 2 boolean values, `true` and `false`.
"""
        )
    )
)
