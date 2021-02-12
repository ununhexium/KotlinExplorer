package net.lab0.jetpackcomposeexplorer.business.course.data.kotlin.helloworld

import net.lab0.jetpackcomposeexplorer.business.domain.LessonImpl
import net.lab0.jetpackcomposeexplorer.business.domain.LessonPage
import net.lab0.jetpackcomposeexplorer.business.domain.KotlinCodeWithBlanks.Companion.placeholder as p

object Main : LessonImpl(
    id = "kotlin.helloworld.main",
    title = "Main",
    pages = listOf(

        LessonPage.CodeQuestionPage(
            title = "The Main Function",
            question = """
Declare a function named `main`.
""",
            snippet = """
${p(0)} ${p(1)}(){}
""",
            explanation = """
`fun` declares a function.

The name of the declared function in between `fun ` and the opening parenthesis `(`.

`Main` and `main` are two different identifiers.
""",
            choices = listOf("function", "fun", "main", "Main"),
            answer = listOf("fun", "main")
        ),

        LessonPage.CodeQuestionPage(
            title = "Print in the main",
            question = """
Make the main function print `hello`.
""",
            snippet = """
fun main() ${p(0)}
    ${p(1)}
${p(2)}
""",
            explanation = """
The body of the function goes between curly brackets. It starts at `{` and ends at `}`.

To make the function more readable, the body inside the function is indented. This is optional.
""",
            choices = listOf(
                "(", ")", "{", """print("hello")""", "}", "[", "]"
            ),
            answer = listOf(
                "(", ")", "{", """print("hello")""", "}"
            )
        ),

        LessonPage.InfoPage(
            title = "Summary",
            """
The `main` function is the entry point of a program.

This is where it will start its execution.

It will execute the content in its body in the order it is written.
"""
        )
    )
)

