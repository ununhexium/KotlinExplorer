package net.lab0.kotlinexplorer.business.course.data.kotlin.positivenegative

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object Boolean : LessonImpl(
  id = "kotlin.positivenegative.boolean",
  title = "Boolean",
  pages = listOf(
    // boolean info
    LessonPage.InfoPage(
      title = "",
      """
**Boolean** values are the simplest data type.

They can be either `true` or `false`, nothing else.

They are used for logical operations.
"""
    ),

    // find true
    LessonPage.CodeQuestionPage(
      title = "Make it true",
      question = """
Assign the boolean value `true`.
""",
      snippet = """
val kotlinIsEasy = ${p(0)}
""",
      explanation = """
`true` is a keyword for the boolean value true.

As with everything else, Kotlin is case sensitive, therefore `True` is not equivalent to `true`.

`1` is an integer, not a boolean. `1` is not equivalent to `true`.

`"true"` is a string, not the `true` keyword.
""",
      answer = listOf("true"),
      confusion = listOf("True", "1", "\"true\""),
    ),

    // find false
    LessonPage.CodeQuestionPage(
      title = "Nope",
      question = """
Pretend that you don't like candies.
""",
      snippet = """
val iLikeCandies = ${p(0)}
""",
      explanation = """
The logic for `true` also applies to `false`.

The empty string `""` is not equivalent to `false`.
It's a string, not a boolean.
""",
      answer = listOf("false"),
      confusion = listOf("False", "0", "\"false\"", "\"\""),
    ),

    // not false
    LessonPage.CodeQuestionPage(
      title = "Not false",
      question = """
Make this print `true`.
""",
      snippet = """
print(${p(0)}false)
""",
      explanation = """
*Not* `!` is the negation operator. It inverts the value of a boolean.

Not true `!true` is `false`.

Not false `!false` is `true`.
""",
      answer = listOf("!"),
      confusion = listOf(),
    ),

    // not true
    LessonPage.MultipleChoice(
      title = "Not true",
      question = """
What will this print?

```kotlin
print(!true)
```
""",
      explanation = """
The negation of `true` is `false`
""",
      choices = listOf("false", "true"),
      answer = setOf(0),
    ),

    // equality operator
    LessonPage.CodeQuestionPage(
      title = "Comparison",
      question = """
Test for equality
""",
      snippet = """
// print true
print(
  true
)

// print true
print(
  true ${p(0)} true
)
""",
      explanation = """
`==` is the comparison operator.

It returns `true` if both sides are equal.

```kotlin
print(
  true ${p(0)} true
)
```

is evaluated to, as `true` is the same as `true`

```kotlin
print(
  true
)
```
""",
      answer = listOf("=="),
      confusion = listOf(),
    ),

    // false == false
    LessonPage.MultipleChoice(
      title = "Compare false",
      question = """
What will this print?

```kotlin
print(
  false == false
)
```
""",
      explanation = """
`false` is equal to `false`, therefore `false == false` is `true`.
""",
      choices = listOf("true", "false"),
      answer = setOf(0),
    ),

    // inequality operator
    LessonPage.CodeQuestionPage(
      title = "Not equal",
      question = """
Make this print `true`.
""",
      snippet = """
print(true ${p(0)} false)
""",
      explanation = """
`!=` is the inequality operator. It returns `true` when the 2 sides are different
""",
      answer = listOf("!="),
      confusion = listOf("=="),
    ),

    // dont use uppercase
    LessonPage.MultipleChoice(
      title = "Error",
      question = """
What's wrong with the code below?

```
val yes = True

// must print true
print(yes)
```
""",
      explanation = """
##### True is case sensitive

`true` is case sensitive and must be written exactly like that.

##### True is not defined anywhere

`True` can't be assigned to yes because it's not present anywhere.


##### Yes is a boolean value

`Yes` is not a boolean value, only `true` and `false` are.
""",
      choices = listOf(
        "True is case sensitive",
        "True is not defined anywhere",
        "Yes is a boolean value"
      ),
      answer = setOf(),
    ),

    // fix the previous code
    LessonPage.CodeQuestionPage(
      title = "Fix me",
      question = """
The previous code was not working. Add a line to make it work.
""",
      snippet = """
${p(0)}
val yes = True

// must print true
print(yes)
""",
      explanation = """
`val True = true` creates a variable named `True`. It can then be copied into `yes`

Note: this is a bad variable name.

`True = "true"` is missing the `val` keyword.

`print(true)` prints `true` but the code is still invalid. `True` is not declared.

`val True = yes` Can't use yes, it's not been defined yet.
""",
      answer = listOf("val True = true"),
      confusion = listOf("True = \"true\"", "print(true)", "val True = yes"),
    ),

    // summary
    LessonPage.InfoPage(
      title = "Summary",
      """
There are only 2 boolean values, `true` and `false`.

They can be compared with `==`, `!=` and negated with `!`.
"""
    )
  )
)
