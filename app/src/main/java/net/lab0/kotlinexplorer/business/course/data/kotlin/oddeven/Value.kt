package net.lab0.kotlinexplorer.business.course.data.kotlin.oddeven

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p


object Value : LessonImpl(
    id = "kotlin.oddeven.value",
    title = "Value",
    pages = listOf(
        LessonPage.CodeQuestionPage(
            title = "Memory",
            question = "Declare a value named number",
            snippet = """
${p(0)} number = 1
""",
            explanation = """
`val` declares a value.

The name of this value is `number`.

`let` is not a keyword in Kotlin.

A blank will not work. It's a syntax error.
""",
            answer = listOf("val"),
            confusion = listOf("let", "")
        ),
        LessonPage.CodeQuestionPage(
            title = "Hello value",
            question = """
Assign the string `hello` to the variable `word`.
""",
            snippet = """
val ${p(0)} = ${p(1)}
""",
            explanation = """
`"word"` is a string, not a value name.

`hello` is a value name, not a string.
""",
            answer = listOf("word", """"hello""""),
            confusion = listOf(""""word"""", "hello")
        ),
        LessonPage.CodeQuestionPage(
            title = "Good names",
            question = """
Let's say you want to give a name to the number of cats in a house.

Choose the best value name.
""",
            snippet = """
val ${p(0)} = 3
""",
            explanation = """
`n` is not a good name because it's too short.
When the program is longer, it's harder to remember what single letter values mean.
""",
            answer = listOf("numberOfCatsInTheHouse"),
            confusion = listOf("n", "nCatHouse")
        ),
        LessonPage.CodeQuestionPage(
            title = "Camel case",
            question = """
Let's say you want to give a name to the number of mice in a house.

Choose the best value name.
""",
            snippet = """
val ${p(0)} = 20
""",
            explanation = """
`number_of_mice_in_the_house` is snake case. This is technically a valid name, but Kotlin uses camel case.

Following the naming conventions will make it easier to read code later.
Looking at the naming will tell its nature (function, value, keyword, ...).
""",
            answer = listOf("numberOfMiceInTheHouse"),
            confusion = listOf("number_of_mice_in_the_house")
        ),
        LessonPage.InfoPage(
            title = "More value",
            """
Values are there to identify a result and reuse it later.

The better the name, the easier it is to understand what it contains.

They can contain integers, strings, booleans and many other kinds of data that you will discover later.
"""
        )
    )
)

// TODO: values can be assigned from another value
// TODO: copy/paste from one reference to another.
// val a = 1; val b = a; // references to the same object. If the value pointed by a changes, then the value pointed by be changes too.
