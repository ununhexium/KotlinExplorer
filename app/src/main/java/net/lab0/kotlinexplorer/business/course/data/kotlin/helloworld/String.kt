package net.lab0.kotlinexplorer.business.course.data.kotlin.helloworld

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object String : LessonImpl(
    id = "kotlin.helloworld.string",
    title = "String",
    pages = listOf(
        LessonPage.CodeQuestionPage(
            title = "Declaration",
            question = "Declare a string containing `hello`",
            snippet =
            """
${p(0)}hello${p(0)}
""",
            explanation = """
Double quotes `"` are for quoting strings.

Simple quotes `'` are for quoting single characters.
""",
            answer = listOf("\""),
            choices = listOf("\"", "'"),
        ),

        LessonPage.CodeQuestionPage(
            title = "Double quotes",
            question = """Declare a string containing just a double quote`"`""",
            snippet =
            """
${p(0)}${p(1)}${p(2)}
""",
            explanation = """
Inside strings, double quotes `"` must be escaped.

The escape character is backslash `\`.
""",
            answer = listOf("\"", """\"""", "\""),
            choices = listOf("\"", "\"", """\"""", """\""""),
        ),

        LessonPage.CodeQuestionPage(
            title = "Quotes",
            question = """Declare a string containing `He said "hello"`""",
            snippet =
            """
${p(0)}He said${p(1)}hello${p(1)}${p(0)}
""",
            explanation = """
Inside strings, double quotes `"` must be escaped.

The escape character is backslash `\`.
""",
            answer = listOf(""""""", """\""""),
            choices = listOf(""""""", """\""""),
        ),

        LessonPage.InfoPage(
            "Summary",
            """
Strings are an ubiquitous data type.

There are many other things to learn about them but they are not needed for the "Hello World" project.
"""
        )
    )
)