package net.lab0.jetpackcomposeexplorer.business.course.data.kotlin.oddeven

import net.lab0.jetpackcomposeexplorer.business.domain.LessonImpl
import net.lab0.jetpackcomposeexplorer.business.domain.LessonPage
import net.lab0.jetpackcomposeexplorer.business.domain.KotlinCodeWithBlanks.Companion.placeholder as p

object Boolean : LessonImpl(
    id = "kotlin.oddeven.boolean",
    title = "Boolean",
    pages = listOf(
        LessonPage.CodeQuestionPage(
            title = "Make it true",
            question = """
Assign the value `true`.
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
            choices = listOf("true", "True", "1", """"true""""),
            answer = listOf("true"),
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
            choices = listOf("false", "False", "0", """"false"""", "\"\""),
            answer = listOf("false"),
        ),
        LessonPage.InfoPage(
            title = "That's it",
            """
There are only 2 boolean values, `true` and `false`.
"""
        )
    )
)