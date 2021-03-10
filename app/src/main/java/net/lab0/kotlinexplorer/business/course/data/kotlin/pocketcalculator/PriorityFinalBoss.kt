package net.lab0.kotlinexplorer.business.course.data.kotlin.pocketcalculator

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage

object PriorityFinalBoss: LessonImpl(
  id = "kotlin.pocketcalculator.priorityfinalboss",
  title = "Final Boss",
  pages = listOf(

    // complex assignment
    LessonPage.MultipleChoice(
      title = "Final boss",
      question = """
What does x contain?

```kotlin
val x=false==10+8>=(1- -1)+2*9
```
""",
      explanation = """
First, you will never see expressions like these, it's just an exercise.

Here are the steps to understand this kind of expression.

##### Add space

This will make the expression more readable.

Identify the operators and add a space before and after them.
You can also add a new line after them, but never before them.
Indentation can also help.

The operators are

val x`=`false`==`10`+`8`>=`(1`-` `-`1)`+`2`*`9

```kotlin
val x=false==10+8>=(1- -1)+2*9
```

becomes

```kotlin
val x = false == 10 + 8 >= (1 - -1) + 2 * 9
```

or 

```kotlin
val x = 
  false == 
    10 + 
      8 >= 
        (1 -
          -1) + 
            2 * 
              9
```

##### Add parentheses

The evaluation of what's inside parentheses comes first.

Let's add some to show the priorities.

1. `++`, `--`
2. `+`, `-`
3. *Secret*
4. `*`, `/`, `%`
5. `+`, `-`
6. *Secret*
7. *Secret*
8. *Secret*
9. *Secret*
10. `<`, `>`, `<=`, `>=`
11. `==`, `!=`


##### Prio 1: `++` or `--`

We don't have that here.

##### Prio 2: `+` prefix

```kotlin
val x =
 false == 10 + 8 >= (1 - -1) + 2 * 9
```

becomes

```kotlin
val x =
 false == 10 + 8 >= (1 - (-1)) + 2 * 9
```

##### Priority 4: `*`

```kotlin
val x =
 false == 10 + 8 >= (1 - (-1)) + (2 * 9)
```

##### Priority 5: `+` and `-`

Make shorter lines

```kotlin
val x =
 false == 10 + 8 >=
  (1 - (-1)) + (2 * 9)
```

```kotlin
val x =
 false == (10 + 8) >=
  (1 - (-1)) + (2 * 9)
```

```kotlin
val x =
 false == (10 + 8) >=
  ((1 - (-1)) + (2 * 9))
```

##### Priority 10: `>=`

```kotlin
val x =
 false == (
    (10 + 8) >= 
    ((1 - (-1)) + (2 * 9))
 )
```

##### Priority 11: `==`

```kotlin
val x =
  (
    false == (
       (10 + 8) >= 
       ((1 - (-1)) + (2 * 9))
    )
  )
```

##### Priority 15: `=`

Nothing to change.

Now we can evaluate the expression from the innermost 
parentheses pair to the outermost parentheses pair and from left to right.

We will focus on the evaluation of the inner part.

false == ( (10 + 8) >= ((1 - `(-1)`) + (2 * 9)))

On this step there is no change. `-1` is already as simple as possible.

false == ( (10 + 8) >= ((1 - `-1`) + (2 * 9)))

false == ( `(10 + 8)` >= ((1 - -1) + (2 * 9)))

false == ( `18` >= ((1 - -1) + (2 * 9)))

false == ( 18 >= (`(1 - -1)` + (2 * 9)))

false == ( 18 >= (`(1 + 1)` + (2 * 9)))

false == ( 18 >= (`2` + (2 * 9)))

false == ( 18 >= (2 + `(2 * 9)`))

false == ( 18 >= (2 + `18`))

false == ( 18 >= `(2 + 18)`)

false == ( 18 >= `20`)

false == ( `18 >= 20` )

false == ( `false` )

false == `( false )`

false == `false`

`false == false`

`true`

Therefore

```kotlin
// val x = true
val x=false==10+8>=(1- -1)+2*9
```

In the real world, such an expression is split in several parts like so:

```kotlin
val firstResult = 10 + 8

val secondResult = (1 - -1) + 2 * 9

val comparison = 
  firstResult >= secondResult
  
val result = false == equality
```
""",
      choices = listOf("A Boolean: true", "A Boolean: false", "Some error"),
      answer = setOf(0)
    ),

    // summary
    LessonPage.InfoPage(
      title = "Summary",
      """
Operator priorities can become confusing.
If you are not sure, use `()` to enforce the priority.

1. `++`, `--`
2. `+`, `-`
3. *Secret*
4. `*`, `/`, `%`
5. `+`, `-`
6. *Secret*
7. *Secret*
8. *Secret*
9. *Secret*
10. `<`, `>`, `<=`, `>=`
11. `==`, `!=`
12. *Secret*
13. *Secret*
14. *Secret*
15. `=`

"""
    )
  )
)
