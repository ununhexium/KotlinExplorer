package net.lab0.kotlinexplorer.business.course.data.kotlin.positivenegative

import net.lab0.kotlinexplorer.business.course.data.kotlin.dollar
import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage

object Introduction : LessonImpl(
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
"""
    ),
    LessonPage.InfoPage(
      "Values",
      """
Values: what they're here for and how to use them.

```kotlin
val n = 116 // any integer
```
"""
    ),
    LessonPage.InfoPage(
      "Branching",
      """
Conditions: how to write and use it.

```kotlin
if (n > 0) "It's positive: " + n
else if(n < 0) "It's negative: ${dollar}n"
else "It's 0"
```
"""
    ),
    LessonPage.InfoPage(
      "String template",
      """
How to use string template.

```kotlin
"It's negative: ${dollar}n"
```
"""
    )
  ),
)
