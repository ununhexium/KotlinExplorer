package net.lab0.kotlinexplorer.business.course.data.kotlin.pocketcalculator

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage

object Introduction : LessonImpl(
  id = "kotlin.pocketcalculator.introduction",
  title = "Introduction",
  pages = listOf(

    // intro
    LessonPage.InfoPage(
      title = "Introduction",
      markdown = """
In this chapter, you will use the Kotlin
number system for basic math.

```kotlin
fun square(a: Int): Int {
  return a * a
}

fun squareRoot(a: Int): Int {
  val aAsDouble = a.toDouble()
  return kotlin.math.sqrt(aAsDouble).toInt()
}

fun main() {
  kotlin.io.println(square(3))
  println(squareRoot(square(3)))
}
```
"""
    ),

    // Import
    LessonPage.InfoPage(
      title = "Import",
      """
Import function from other packages.
        
```kotlin
import kotlin.math.min
```
"""
    ),

    // your own functions
    LessonPage.InfoPage(
      title = "Declare your own functions",
      """
```kotlin
fun add(a: Int, b: Int): Int {
  return a + b
}
```
"""
    ),

    // call external function and number conversion
    LessonPage.InfoPage(
      title = "Call and convert",
      """
Call external functions.

Convert between number types.

```kotlin
fun squareRoot(a: Int): Int {
  val aAsDouble = a.toDouble()
  return kotlin.math.sqrt(aAsDouble).toInt()
}
```
"""
    ),

    // chain calls
    LessonPage.InfoPage(
      title = "Function calls chain",
      """
```kotlin
println(squareRoot(square(3)))
```
"""
    )
  )
)
