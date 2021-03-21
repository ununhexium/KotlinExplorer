package net.lab0.kotlinexplorer.business.course.data.kotlin.pocketcalculator

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object DoublePrecision : LessonImpl(
  id = "kotlin.pocketcalculator.doubleprecision",
  title = "Double Precision",
  pages = listOf(

    // just like floats
    LessonPage.CodeQuestionPage(
      title = "Float-like",
      question = """
`Double`s are just like `Float`s but with higher precision and a larger range.

Try stuff you learned with doubles.
""",
      snippet = """
val minimum = Double.${p(0)}
val maximum = Double.${p(1)}
val infinity = Double.${p(2)}
val notANumber = Double.${p(3)}
""",
      explanation = """
Works exactly like floats.
""",
      answer = listOf("MIN_VALUE", "MAX_VALUE", "POSITIVE_INFINITY", "NaN"),
      confusion = listOf(),
    ),

    // how to store 1e100 -> use double
    LessonPage.CodeQuestionPage(
      title = "Google",
      question = """
Declare a google (`1e100`).
""",
      snippet = """
val google: ${p(0)} = 1e100
""",
      explanation = """
A `Double` can contain up to `1e308` (`1.7976931348623157 * 10^308`).

Contrary to the `Float` that uses 32 bits for its storage,
the `Double` uses 64 bits and has a wider range of values.

If you're not sure, just use `Double`s.

A `Float` can only contain up to around `1e38`.
""",
      answer = listOf("Double"),
      confusion = listOf("Float"),
    ),

    // Declare different types
    LessonPage.CodeQuestionPage(
      title = "Float or Double",
      question = """
Declare the right type of number.
""",
      snippet = """
val float:Float = ${p(0)}
val double:Double = ${p(1)}
""",
      explanation = """
`f` indicates that it's a `Float`.

If nothing is specified, and it has a decimal point, it defaults to `Double`.
""",
      answer = listOf("1.0f", "1.0"),
      confusion = listOf(),
    ),

    // Type inference
    LessonPage.CodeQuestionPage(
      title = "Type inference",
      question = """
What's the type?
""",
      snippet = """
val b:${p(0)} = 1f
val a:${p(1)} = 1.0
""",
      explanation = """
`f` indicates that it's a `Float`.

If nothing is specified, it defaults to `Double`.
""",
      answer = listOf("Float", "Double"),
      confusion = listOf(),
    ),

    // Declare double PI
    LessonPage.CodeQuestionPage(
      title = "Declare π",
      question = """
Declare the value π with double precision.
""",
      snippet = """
val pi:Double = ${p(0)}
""",
      explanation = """
`3.141_592_653_589_793` is the closest decimal representation.

The other numbers are approximations.

When using a floating point number, try to be as precise as possible.

So why not using more digits?

Because the 64bit precision can't handle them.
They are accepted in the code but will be forgotten at execution.

Just like integers, floating point numbers can have underscores `_`
to be more readable.

Just like `3.1` is not good enough for PI, `3.1415927` is also not good enough for a `Double`.

`3.1415927` is enough precision for Floating point numbers.
""",
      answer = listOf("3.141_592_653_589_793"),
      confusion = listOf("3.1", "3.1415927"),
    ),

    // reassign to float
    LessonPage.MultipleChoice(
      title = "Precision comparison",
      question = """
What will happen?

```kotlin
val floatingPie: Float = 3.141592653589793f
val doublePie: Double = 3.141592653589793

print(floatingPie == doublePie)
```
""",
      explanation = """
The comparison operator `==` will refuse to compare data 
that is different, because it can lead to errors.
""",
      choices = listOf(
        "Some error: can't compare 2 types that are different",
        "false: they have a different precision",
        "true:they're both π"
      ),
      answer = setOf(0),
    ),

    // convert from float: loose precision
    LessonPage.MultipleChoice(
      title = "Type conversion",
      question = """
What will happen?

```kotlin
val pi = 3.141592653589793

val doublePie = pi.toDouble()
val floatingPie = pi.toFloat()

print(floatingPie.toDouble() == doublePie)
""",
      explanation = """
A float has 7 decimal digits of precision. A double has 15 decimal digits of precision.
 
Converting a number from `Double` to `Float` and back to double makes it loose precision.

On the float side, the value is `3.1415927`, which on the double side, the value is `3.141592653589793`

If these digits are important like in π, the converted number is different.
""",
      choices = listOf(
        "false: they have a different precision",
        "Some error: can't compare 2 types that are different",
        "true: they're both π",
      ),
      answer = setOf(0),
    ),

    // convert to float without precision loss
    LessonPage.MultipleChoice(
      title = "",
      question = """
What will happen?

```kotlin
val one = 1.0

val doubleOne = one.toDouble()
val floatingOne = one.toFloat()

print(floatingOne.toDouble() == doubleOne)
""",
      explanation = """
When converting, it's possible that there is no precision loss.

`1` can be stored without precision loss in a float.

So its value will not change when converting from a double to a float.

`1.0` == `1.00` == `1.000` ...
""",
      choices = listOf(
        "Print true: 1 didn't loose precision in the conversion",
        "Print false: we lost the end digits' precision in the conversion"
      ),
      answer = setOf(0),
    ),

    // summary
    LessonPage.InfoPage(
      title = "Summary",
      """
`Double` precision floating point numbers behave the same as floating point numbers.

Everything you know about floating points is true for doubles.

A `Double` can always contain what a `Float` contains without any precision loss.
The reverse is not true.

Comparisons can only happen between numbers of the same type.

Long decimal numbers can use `_` just like ints.
"""
    )
  )
)
