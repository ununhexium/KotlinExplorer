package net.lab0.kotlinexplorer.business.course.data.kotlin.basics

import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder
import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage

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
fun main() {
  ${placeholder(0)}(${placeholder(1)}${placeholder(2)}${placeholder(1)})
}
""",
            // TODO: answer must be shown as markdown

            """
`print` to show the value on the terminal.

`"` to quote the string.

`Hello, World!` for the content.
""",
            listOf(
                "'", "\"", "Print", "print", "Hello, World!"
            ),
            listOf("print", "\"", "Hello, World!"),
        ),
    ),
)
