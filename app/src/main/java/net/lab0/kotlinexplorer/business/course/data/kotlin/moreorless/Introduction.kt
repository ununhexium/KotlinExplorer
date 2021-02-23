package net.lab0.kotlinexplorer.business.course.data.kotlin.moreorless

import net.lab0.kotlinexplorer.business.course.data.kotlin.dollar
import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage

object Introduction : LessonImpl(
    id = "kotlin.moreorless.introduction",
    title = "Introduction",
    pages = listOf(
        LessonPage.InfoPage(
            "More or less",
            """
In this chapter we will see how to implement a game when the user 
has to guess a number.

The user will try to find the number until it guesses the right one.

To help the player, the computer will tell if the target number 
is bigger or smaller than the proposed number.

```kotlin_lines
import kotlin.random.Random

fun main() {
  // generates a number between 0 and 99
  val target = Random.nextInt(100)

  var tries = 0
  var proposition:Int? = null

  while (proposition != target) {
    proposition = readLine()!!.toInt()
    tries++

    print(
        when {
          proposition == target -> "You win in ${dollar}tries!"
          proposition > target -> "Too big!"
          else -> "Too small!"
        }
    )
  }
}
```

In this project you will see.

##### Line 1

What is this `import` statement?

##### Line 5

What is `.nextInt()` there and why is it possible to write that?

##### Line 7

What is `var`?

##### Line 8

What is this question mark `?`

What is `null`?

##### Line 10

How does `while` work?

##### Line 10

Where does `readLine()` come from?
Why do we use `!!`?
Why can we do `.toInt()` on the result? Where does it come from?

##### Line 11

`++` on a variable and nothing else?

##### Line 14

What does `when` do here? Why are we not using `if`/`else`?
"""
        )
    )
)