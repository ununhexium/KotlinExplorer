package com.example.jetpackcomposeexplorer.business.course.data.kotlin.module1.basics

import com.example.jetpackcomposeexplorer.business.domain.KotlinCodeWithBlanks
import com.example.jetpackcomposeexplorer.business.course.LessonDataImpl
import com.example.jetpackcomposeexplorer.business.course.LessonPage

object SmallestProgram : LessonDataImpl(
    id = Basics.id + ".smallest_program",
    title = "The smallest program",
    chapter = Basics,

    dependencies = listOf(HelloWorld),
    pages = listOf(
        LessonPage.InfoPage(
            title = "Smallest program",
            markdown = """
The smallest possible program starts, does nothing and stops.
The entry point to a program is the `main()` function.

```kotlin
fun main() {
// your code goes here
}
```

`fun` declares a function named `main`.

The `()` tells that the function expects no value.

The function starts at `{` and ends at `}`.
"""
        ),
        LessonPage.CodeQuestionPage(
            "Tiny hello",
            """
Print `Hello, World!`.
""",
            """
fun main() {
  ${KotlinCodeWithBlanks.placeholder(0)}(${KotlinCodeWithBlanks.placeholder(1)}${
              KotlinCodeWithBlanks.placeholder(2)
            }${KotlinCodeWithBlanks.placeholder(1)})
}
""",
            {
              """
`print` to show the value on the terminal.

`"` to quote the string.

`Hello, World!` for the content.
"""
            },
            listOf(
                "'", "\"", "Print", "print", "Hello, World!"
            )
        ) {
          it == listOf("print", "\"", "Hello, World!")
        },
    ),
)