package com.example.jetpackcomposeexplorer.model.course.data.kotlin.basics

import com.example.jetpackcomposeexplorer.model.KotlinCodeWithBlanks.Companion.placeholder
import com.example.jetpackcomposeexplorer.model.course.LessonImpl
import com.example.jetpackcomposeexplorer.model.course.LessonPage
import com.example.jetpackcomposeexplorer.model.course.data.kotlin.basics.HelloWorld

object SmallestProgram : LessonImpl(
    id = "kotlin.basics.SmallestProgram",
    title = "The smallest program",
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
int main() {
  ${placeholder(0)}(${placeholder(1)}${placeholder(2)}${placeholder(1)})
}
""",
            // TODO: answer must be shown as markdown
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
