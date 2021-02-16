package net.lab0.kotlinexplorer.business.course.data.kotlin.basics

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage.CodeQuestionPage
import net.lab0.kotlinexplorer.business.domain.LessonPage.InfoPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder

object HelloWorld : LessonImpl(
    id = "kotlin.basics.HelloWorld",
    title = "Hello, World!",

    pages = listOf(
        InfoPage(
            title = "Introduction",
            markdown = """
### Introduction

#### Kotlin

Kotlin is a programming language widely used for Android development, for server-side and tooling development.

In this app, you will learn `Kotlin` using the fast path to become an Android developer.
"""
        ),

        InfoPage(
            title = "Hello, World!",
            markdown = """
A basic operation in a program is to display text, or `print` text.

```kotlin
print("Hello, World!")
```

`print` is a function, taking one parameter: 
`"Hello, World!"`, a string.

A string starts with double quotes `"`, 
followed by several characters,
and ends with a double quote `"`.
"""
        ),

        CodeQuestionPage(
            title = "Try to print",
            question = """Call the `print` function""",
            snippet = """${placeholder(0)}("Kotlin")""",
            explanation =
            """
The function name is case sensitive, 
you must the name exactly as it is declared: `print`.
""",
            choices = listOf("printLn", "print", "println", "Print", "PrintLn"),
            answer = listOf("print"),
        ),

        CodeQuestionPage(
            title = "Try to print Kotlin",
            question = """Print `Kotlin`""",
            snippet = """print(${placeholder(0)})""",
            explanation =
            """
When printing a string, the content of the string must be between double quotes `"`.
""",
            choices = listOf(""""Kotlin"""", "Kotlin", "'Kotlin'"),
            answer = listOf(""""Kotlin""""),
        ),
    )
)
