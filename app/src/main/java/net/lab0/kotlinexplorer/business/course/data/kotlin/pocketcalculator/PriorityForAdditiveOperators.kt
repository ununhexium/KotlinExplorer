package net.lab0.kotlinexplorer.business.course.data.kotlin.pocketcalculator

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object PriorityForAdditiveOperators : LessonImpl(
  id = "kotlin.pocketcalculator.priorityforadditiveoperators",
  title = "Priority for +, -",
  pages = listOf(

    // addition
    LessonPage.CodeQuestionPage(
      title = "Addition",
      question = """
First grader math.
""",
      snippet = """
1 ${p(0)} 2 == 3
""",
      explanation = """
Does this require an explanation? 🧐
""",
      answer = listOf("+"),
      confusion = listOf("-"),
    ),

    // subtraction
    LessonPage.CodeQuestionPage(
      title = "Subtraction",
      question = """
First grader *advanced* math.
""",
      snippet = """
2 ${p(0)} 1 == 1
""",
      explanation = """
2 - 1 = 1  🤷
""",
      answer = listOf("-"),
      confusion = listOf("+"),
    ),

    // prefix + VS addition +
    LessonPage.MultipleChoice(
      title = "Highest priority +",
      question = """
`+` and `-` can have different priorities.
On which line does `+` have the highest priority?

```kotlin
val a = +2
val b = 1 + 1
```
""",
      explanation = """
`+` used as a prefix has a higher priority than `+` used as the addition operator.
""",
      choices = listOf("Line 1", "Line 2"),
      answer = setOf(0),
    ),

    // prefix - VS subtraction -
    LessonPage.MultipleChoice(
      title = "Highest priority -",
      question = """
On which line does `-` have the highest priority?

```kotlin
val a = -1 + 1
val b = 1 - 1
val c = 1 + -1
```
""",
      explanation = """
`-` used as a prefix has a higher priority than `-` used as the subtraction operator.
""",
      choices = listOf("Line 1", "Line 3", "Line 2"),
      answer = setOf(0, 1),
    ),

    // addition of negative
    LessonPage.MultipleChoice(
      title = "+-",
      question = """
What is the result?

```kotlin
1+-1
```
""",
      explanation = """
```kotlin
1+-1
```

Is the same as

```kotlin
1 + -1
```
""",
      choices = listOf("This is 1 + -1 = 0", "Some error"),
      answer = setOf(0),
    ),

    // subtraction of positive
    LessonPage.MultipleChoice(
      title = "-+",
      question = """
What is the value of result?

```kotlin
1-+1
```
""",
      explanation = """
```kotlin
1-+1
```

Is the same as

```kotlin
1 - +1
1 - 1  // preferred
1-1
```
""",
      choices = listOf("This is 1 - +1 = 0", "Some error"),
      answer = setOf(0),
    ),

    // increment
    LessonPage.MultipleChoice(
      title = "++",
      question = """
```kotlin
1++1
```
""",
      explanation = """
`++` is actually an operator in Kotlin. More about that later.

The above expression must be written with a space between the `+` signs.
""",
      choices = listOf("Some error", "This is 1 + +1 = 2"),
      answer = setOf(0),
    ),

    // add positive
    LessonPage.MultipleChoice(
      title = "+ +",
      question = """
```kotlin
1  +  +  1
```
""",
      explanation = """
Because of the spaces, this is seen as separate `+` symbols.

It doesn't matter how many spaces are in front of a `+` prefix,
only the relative position matters.

The expression is the same as

```kotlin
1 + +1
1 + 1  // preferred
1+1
```
""",
      choices = listOf("This is 1 + +1 = 2", "Some error"),
      answer = setOf(0),
    ),

    // subtraction of negative
    LessonPage.MultipleChoice(
      title = "- -",
      question = """
```kotlin
1 - - 1
```
""",
      explanation = """
Because of the spaces, this is seen as separate `-` symbols.
""",
      choices = listOf("This is 1 - -1 = 1 + 1 = 2", "Some error"),
      answer = setOf(0),
    ),

    // decrement
    LessonPage.MultipleChoice(
      title = "--",
      question = """
```kotlin
1--1
```
""",
      explanation = """
`--` is actually an operator in Kotlin. More about that later.

The above expression must be written with a space between the `-` sings.

At least

```kotlin
1- -1
```

or better

```kotlin
1 - -1
```
""",
      choices = listOf("Some error", "This is 1 - -1 = 1 + 1 = 2"),
      answer = setOf(0),
    ),

    // mix + and /
    LessonPage.MultipleChoice(
      title = "=15",
      question = """
Which equations are equal to 15?
""",
      explanation = """
##### `3 (+ 2) * 6`

Is invalid. It could works with an extra `+`

3 `+` (+ 2) * 6

##### `(3 + 2) * 6`

5 * 6 = 30

Everything else is 15.

`(3 + (2 * 6))`

The outer parentheses don't matter.

The inner parentheses match the actual execution order.

What is inside is executed with the regular operators priority.

= 3 + (2 * 6)

= 3 + 2 * 6

= 3 + (6 * 2)

= 3 + (12) = 15
""",
      choices = listOf(
        "3 + 2 * 6",
        "3 + (2 * 6)",
        "(3 + (2 * 6))",
        "(3 + 2 * 6)",
        "(3 + 2) * 6",
        "3 (+ 2) * 6",
      ),
      answer = setOf(0, 1, 2, 3),
    ),

    // all arithmetic operators at once
    LessonPage.MultipleChoice(
      title = "Mix everything",
      question = """
What's the result?

```kotlin
9 / 7 + 6 - 4 * -5
```
""",
      explanation = """
```
9 / 7 + 6 - 4 * -5
(9 / 7) + 6 - (4 * -5)
(1) + 6 - (-20)
1 + 6 + 20
27
```
""",
      choices = listOf("27", "-15", "-13", "0?"),
      answer = setOf(0),
    ),

    // declare average function
    LessonPage.CodeQuestionPage(
      title = "Average function",
      question = """
Write a function to compute the average between 2 values.

If the average is not a whole number, truncate the result.

```kotlin
// 2 + 4 = 6
// 6 / 2 = 3
average(2, 4)

// 1 + 2 = 3
// 3 / 2 = 1
average(1, 2)
```

Note the average of 2 numbers `a` and `b` is the sum of these 2 numbers, divided by 2.
""",
      snippet = """
fun average(a:${p(0)}, b:${p(0)}): ${p(1)} {
  return ${p(2)}
}
""",
      explanation = """
`a + b / 2` is `a + (b / 2)`

One must divide the sum, not a single number. Use parentheses to force that behaviour.

`(a + b) / 2`

Dividing each number by 2 doesn't work. Example:

```kotlin
average(3,3) == 3
```

But:

`3 / 2 + 3 / 2`

`1 + 1`

`2`

All the data types are `Int`s.
""",
      answer = listOf("Int", "Int", "(a + b) / 2"),
      confusion = listOf("String", "a + b / 2", "a / 2 + b / 2"),
    ),

    // prio 5
    LessonPage.InfoPage(
      title = "Priority #1 and #5",
      """
`++`, `--` have priority 1.

`+`, `-` have priority 5.

So far we have:

1. `++`, `--`
2. Prefix `+`, `-`
3. *Secret*
4. `*`, `/`, `%`
5. `+`, `-`

"""
    ),
  )
)
