package com.example.jetpackcomposeexplorer.business.course.data.kotlin.module1.basics

import com.example.jetpackcomposeexplorer.business.domain.KotlinCodeWithBlanks
import com.example.jetpackcomposeexplorer.business.course.LessonDataImpl
import com.example.jetpackcomposeexplorer.business.course.LessonPage

object HelloWorld : LessonDataImpl(
    id = Basics.id + ".hello_world",
    title = "Hello, World!",
    chapter = Basics,

    pages = listOf(
        LessonPage.InfoPage(
            title = "Introduction",
            markdown = """
### Introduction

#### Kotlin

Kotlin is a programming language widely used for Android development, for server-side and tooling development.

In this app, you will learn `Kotlin` using the fast path to become an Android developer.
"""
        ),

        LessonPage.InfoPage(
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

        LessonPage.CodeQuestionPage(
            title = "Try to print",
            question = """Call the `print` function""",
            snippet = """${KotlinCodeWithBlanks.placeholder(0)}("Kotlin")""",
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

        LessonPage.CodeQuestionPage(
            title = "Try to print Kotlin",
            question = """Print `Kotlin`""",
            snippet = """print(${KotlinCodeWithBlanks.placeholder(0)})""",
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