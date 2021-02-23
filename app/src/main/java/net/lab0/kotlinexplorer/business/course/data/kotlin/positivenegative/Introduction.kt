package net.lab0.kotlinexplorer.business.course.data.kotlin.positivenegative

import net.lab0.kotlinexplorer.business.course.data.kotlin.dollar
import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage

object Introduction: LessonImpl(
    id = "kotlin.positivenegative.introduction",
    title = "Introduction",
    pages = listOf(
        LessonPage.InfoPage(
            title = "Positive or negative?",
            """
In this chapter you will see how to tell if a number is positive, negative or zero.

```kotlin
fun main() {
  val number = 116 // any integer
  print(
      if (number > 0) "It's positive: " + number
      else if(number < 0) "It's negative: ${dollar}number"
      else "It's 0"
  )
}
```

What does `val` do?

What is this `if ... else ...` series?

Why is it inside the `print` function?

What is that `$` variable thing?

All of this is explained in this chapter.

"""
        )
    ),
)