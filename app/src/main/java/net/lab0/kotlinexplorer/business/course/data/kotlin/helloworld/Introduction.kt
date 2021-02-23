package net.lab0.kotlinexplorer.business.course.data.kotlin.helloworld

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage

object Introduction : LessonImpl(
    id = "kotlin.helloworld.introduction",
    title = "Introduction",
    pages = listOf(
        LessonPage.MultipleChoice(
            "title",
            "Question?",
            "Explanation.",
            listOf("Alpha", "Beta", "Gamma"),
            setOf(1),
        ),
        LessonPage.InfoPage(
            title = "Introduction",
            """
In this chapter, you'll learn how to write the smallest possible 
program that prints `Hello "World"` to the user.

```kotlin
fun main() {
    println("Hello "World"")
}
```

Each of the concepts that you need are detailed on the following chapters.
"""
        )
    )
)
