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

You will understand the code below.

```kotlin_lines
fun main() {
  val n = 116 // any integer
  print(
      if (n > 0) "It's positive: " + n
      else if(n < 0) "It's negative: ${dollar}n"
      else "It's 0"
  )
}
```

In particular, you will learn:

##### Line 2

What does `val` do?

##### Lines 4 to 6

What is this `if ... else ...` series?

Why is it inside the `print` function?

##### Line 5

What is that `$` variable thing?

---

All of this is explained in this chapter.

"""
        )
    ),
)
