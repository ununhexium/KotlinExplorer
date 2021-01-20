package com.example.jetpackcomposeexplorer.model.course.data.kotlin.basics

import com.example.jetpackcomposeexplorer.model.KotlinCodeWithBlanks.Companion.placeholder
import com.example.jetpackcomposeexplorer.model.course.LessonImpl
import com.example.jetpackcomposeexplorer.model.course.LessonPage.CodeQuestionPage
import com.example.jetpackcomposeexplorer.model.course.LessonPage.InfoPage

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
            answer = {
              """
The function name is case sensitive, 
you must the name exactly as it is declared: `print`.
"""
            },
            choices = listOf("printLn", "print", "println", "Print", "PrintLn"),
        ) {
          it == listOf("print")
        },

        CodeQuestionPage(
            title = "Try to print Kotlin",
            question = """Print `Kotlin`""",
            snippet = """print(${placeholder(0)})""",
            answer = {
              """
When printing a string, the content of the string must be between double quotes `"`.
"""
            },
            choices = listOf(""""Kotlin"""", "Kotlin", "'Kotlin'"),
        ) {
          it == listOf(""""Kotlin"""")
        },
    )
)