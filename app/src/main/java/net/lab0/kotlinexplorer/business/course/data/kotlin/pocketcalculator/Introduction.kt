package net.lab0.kotlinexplorer.business.course.data.kotlin.pocketcalculator

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage

object Introduction : LessonImpl(
  id = "kotlin.pocketcalculator.introduction",
  title = "Introduction",
  pages = listOf(
    LessonPage.InfoPage(
      title = "Introduction",
      markdown = """
Function creation and call.

External function call.

```kotlin_lines
// your own function
fun add(a: Int, b: Int): Int {
  return a + b
}

 // fancy notation
fun squareRoot(a: Int): Double =
  // calling external function
  kotlin.math.sqrt(a.toDouble())

fun main() {
  // fully qualified function call
  kotlin.io.println(add(2, 3))
  println(
    // chaining function calls
    squareRoot(add(3, add(3, 3)))
  )
}
```
"""
    )
  )
)
