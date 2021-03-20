package net.lab0.kotlinexplorer.business.course.data.kotlin.pocketcalculator

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object FloatingPoint : LessonImpl(
  id = "kotlin.pocketcalculator.floatingpoint",
  title = "Floating Point",
  pages = listOf(
    LessonPage.InfoPage(
      title = "Notations",
      """
`^` indicates an exponent in the comments. This is not valid Kotlin syntax.

`100` = `10^2`

`∞` is the infinity.
"""
    ),

    // Declare 1
    LessonPage.CodeQuestionPage(
      title = "Declare 1",
      question = """
Declare `1` as a floating point number.
""",
      snippet = """
val one = ${p(0)}
""",
      explanation = """
```kotlin
1f
1F
1.0f
1.0F
1.00000f
1.00000F
```
are all the same declaration.
The `f` or `F` is mandatory to make the difference between integers and floating point numbers.
""",
      answer = listOf("1f"),
      confusion = listOf("1", "1.0"),
    ),

    // type inference
    LessonPage.CodeQuestionPage(
      title = "Explicit type",
      question = """
Declare the type of each variable.
""",
      snippet = """
val one: ${p(0)} = 1
val two: ${p(1)} = 2.0f
val three: ${p(2)} = 3f
""",
      explanation = """
`f` makes it a `Float`.

`.xxf` makes it a `Float`.

If nothing is specified, it's an `Int`.

Thanks to the type inference mechanism, each number's type will be inferred from the notation.
This make specifying types optional.

The code below states exactly the same.

```kotlin
val one = 1
val two = 2.0f
val three = 3f
```

""",
      answer = listOf("Int", "Float", "Float"),
      confusion = listOf("Int", "Int", "Float"),
    ),

    // doesn't auto convert from int to float
    LessonPage.MultipleChoice(
      title = "Conversion",
      question = """
What is the type of `f`?

```kotlin
val f: Float = 1
```
""",
      explanation = """
No automatic conversion is performed when the declared (`Float`)
and inferred (1 is `Int`) data types are different.
""",
      choices = listOf(
        "An error: incompatible types",
        "A Float: 1 is also a valid float: 1.0",
        "An Int: 1 enforces the data type"
      ),
      answer = setOf(0),
    ),

    // Declare float half
    LessonPage.CodeQuestionPage(
      title = "A half",
      question = """
Declare a half `0.5` as a floating point number.
""",
      snippet = """
val half: ${p(0)} = ${p(1)}
""",
      explanation = """
The `f` indicates that it's a floating point number and is mandatory to have a `Float` type.
""",
      answer = listOf("Float", "0.5f"),
      confusion = listOf(".5", "1 / 2"),
    ),

    // declare 100
    LessonPage.CodeQuestionPage(
      title = "100",
      question = """
Declare 100 as a float.
""",
      snippet = """
val hundred = ${p(0)}
""",
      explanation = """
`1e10f` or `1E10f` is 10 to the power of 2 or 10 squared or `10^2`.

The `e` replaced the exponent symbol.

`10 * 10` and `100` are `Int`s.
""",
      answer = listOf("1e10f"),
      confusion = listOf("10 * 10", "100"),
    ),

    // add 2 halves
    LessonPage.CodeQuestionPage(
      title = "2 halves",
      question = """
Compute 1/2 + 1/2
""",
      snippet = """
val one = 0.5f ${p(0)} 0.5f
""",
      explanation = """
The arithmetic operators `+`, `-` work with floating point numbers.
""",
      answer = listOf("+"),
      confusion = listOf("++", "*", "-"),
    ),

    // 0.5f + 0.5f type
    LessonPage.CodeQuestionPage(
      title = "Result type",
      question = """
What is the result type of an operation on 2 `Float`s ?
""",
      snippet = """
val one: ${p(0)} = 0.5f + 0.5f
""",
      explanation = """
An operation between 2 floats returns a float.

As aa general rule, an operation between any two identical
number types returns that same type of number type.
""",
      answer = listOf("Float"),
      confusion = listOf("Int"),
    ),

    // 0.3f - 0.2f rounding
    LessonPage.MultipleChoice(
      title = "0.3 - 0.2",
      question = """
What does this print?

```kotlin
print(0.3f - 0.2f)
```
""",
      explanation = """
Why?

Floating point number are not real numbers (in the mathematical sense).
They are approximations.

Floating point numbers are encoded in binary and some decimal
representations or operations don't have an exact result.

A decimal equivalent would be 1/3 + 2/3 = 1, but 0.333 + 0.666 = 0.999, not 1.

Rounding errors can also happen when converting between decimal and binary.
""",
      choices = listOf("It's a trap: 0.10000001", "0.1, obviously!"),
      answer = setOf(0),
    ),

    // -0.5
    LessonPage.CodeQuestionPage(
      title = "Negative half",
      question = """
Declare a negative half (-0.5)
""",
      snippet = """
val negativeHalf = ${p(0)}
""",
      explanation = """
`-0.5f` or `-.5f`

`-` indicates that the number is negative.

`1 / 2` is an integer and is equal to 0.
""",
      answer = listOf("-0.5f"),
      confusion = listOf("1 / 2"),
    ),

    // 100 halves
    LessonPage.CodeQuestionPage(
      title = "100 halves",
      question = """
Compute `100f * 0.5f`
""",
      snippet = """
val fifty: ${p(0)} = 100f ${p(1)} 0.5f
""",
      explanation = """
The arithmetic operators `*`, and `/` also work with floating point numbers.
""",
      answer = listOf("Float", "*"),
      confusion = listOf("**", "Int"),
    ),

    LessonPage.InfoPage(
      title = "Power",
      """
There is no symbol to write powers.

`3**3` is not 9

`3^3` is not 9
"""
    ),

    // remainder on floats
    LessonPage.MultipleChoice(
      title = "Float remainder",
      question = """
What is the result of this operation?

```kotlin
val one = 7.0f % 3.0f
```
""",
      explanation = """
The modulo operator `%` works on floating point numbers
 in a similar way to how it works on integers.
 
7.0 = 2.0 * 3.0 + `1.0`
""",
      choices = listOf("1.0f", "1", "An error?"),
      answer = setOf(0),
    ),

    // remainder on complicated floats
    LessonPage.MultipleChoice(
      title = "Float remainder 2",
      question = """
What is the result of this operation?

```kotlin
val one = 11.5f % 2.5f
```
""",
      explanation = """
The modulo operator `%` actually works on floating point numbers
even if they don't look like integers.
 
11.5 = 2.5 * 4 + `1.5`
""",
      choices = listOf("1.5f", "That doesn't make sense!", "0.5f"),
      answer = setOf(0),
    ),

    // Declare a big number
    LessonPage.CodeQuestionPage(
      title = "Max float",
      question = """
Declare the largest possible floating point number.
""",
      snippet = """
val maxFloat: Float = ${p(0)}
""",
      explanation = """
`1e99` is too big for a `Float`ing point number. It will be translated to `+∞`.

The max value for a floating point number is hard to remember,
especially in the decimal notation.
 
Its value is accessible via the `Float` *property* `MAX_VALUE`.

This is `0x1.fffffeP+127f` in binary or `3.4028235e+38f` (3.4028235 * 10^38) in decimal.

It's not possible to store anything bigger than `3.4028235e+38f` nor
anything smaller than `-3.4028235e+38f` in a `Float`.

If you try to do so, it will approximate it with `+∞` or `-∞`.

This is due to how `Float`s encode floating point numbers.
""",
      answer = listOf("Float.MAX_VALUE"),
      confusion = listOf("1e99"),
    ),

    // 2 words about properties
    LessonPage.InfoPage(
      title = "Properties?",
      """
`Properties` are related to `classes`.
You will see later in detail what these are exactly.

For the moment, you can see `classes` as a named group of elements.

These `classes` contain other named elements, they can be
 properties, functions, or other types of elements.
 
To access an element in these groups, the point `.` operator is used.

`Float.MAX_VALUE` means "Access the `MAX_VALUE` property inside the `Float` class"

The `Float` object looks a bit like

```kotlin
class Float {
  val MAX_VALUE: Float = 0x1.fffffeP+127f // 3.4028235e+38f
  // many other things.
}
```
"""
    ),

    // Declare an infinity
    LessonPage.CodeQuestionPage(
      title = "-∞ float",
      question = """
Declare a `Float` that is `+∞`.
""",
      snippet = """
val infinity = ${p(0)}
""",
      explanation = """
The constant for infinity is

```kotlin
Float.POSITIVE_INFINITY
```

```kotlin
val infinity = Float.POSITIVE_INFINITY
```

A google `1e100` will be replaced with infinity, but it is confusing.
When reading the code, you must remember which values are greater than
 the maximum allowed and translate them to infinity in your head.

`-∞` is not a valid floating point value.

`"-∞"` is a `String`, so not a valid floating point number.
""",
      answer = listOf("Float.POSITIVE_INFINITY"),
      confusion = listOf("1e100", "∞", "\"∞\""),
    ),

    // why is -1e99 confusing
    LessonPage.MultipleChoice(
      title = "To be or not to be",
      question = """
Guess the value

```kotlin
val what = -1e99f / 1e99f
```
""",
      explanation = """
```kotlin
-1e99f / 1e99f
-∞ / ∞
```

This is not a valid math operation and returns **Not a Number**: `Float.NaN`
""",
      choices = listOf("Not a Number", "-1: divide before assignment", "+∞", "∞"),
      answer = setOf(0),
    ),

    // Declare a negative infinity
    LessonPage.CodeQuestionPage(
      title = "-∞ float",
      question = """
Declare a `Float` that is `-∞`.
""",
      snippet = """
val negativeInf = ${p(0)}
""",
      explanation = """
The constant for negative infinity is

```kotlin
Float.NEGATIVE_INFINITY
```

It's also possible to use the positive infinity with a minus `-` sign.

```kotlin
val negInf = - Float.POSITIVE_INFINITY
```
""",
      answer = listOf("Float.NEGATIVE_INFINITY"),
      confusion = listOf("-1e100", "-∞", "\"-∞\""),
    ),

    // declare NaN
    LessonPage.CodeQuestionPage(
      title = "Declare NaN",
      question = """
Declare a `NaN` value.
""",
      snippet = """
val notANumber: Float = ${p(0)}
""",
      explanation = """
`Float.NaN` represents values that are the results of unknown calculations.

It indicates that there should be no more computation with this value.

Here is examples of calculations that return NaN

```kotlin
0f / 0f
val inf = Float.POSITIVE_INFINITY
inf / inf
inf ^ inf
inf % 1f
```
""",
      answer = listOf("Float.NaN", "NaN"),
      confusion = listOf("\"NaN\""),
    ),

    // insist with NaN computation
    LessonPage.MultipleChoice(
      title = "Use NaN",
      question = """
What is the value of `f`?

```kotlin
val f = Float.NaN * 0f
```
""",
      explanation = """
`NaN` used in any operation will return `NaN`.

`NaN` is a way to alert you that a computation went wrong.
""",
      choices = listOf(
        "NaN: any operation with NaN returns NaN",
        "0: any float * 0f is 0",
        "Some error?"
      ),
      answer = setOf(0),
    ),

    // NaN 1= NaN
    LessonPage.MultipleChoice(
      title = "NaN is NaN",
      question = """
```kotlin
Float.NaN == Float.NaN
```
""",
      explanation = """
Nothing is equal to `NaN`.

It's the only value that is not equal to itself.

To test if a float is `NaN`, you can either use `.isNaN()`
or test that the number is not equal to itself.

Example

```kotlin
val infinity = Float.POSITIVE_INFINITY

// make nan
val nan = infinity / infinity

// true
nan.isNaN()

// true
nan != nan
```
""",
      choices = listOf("false, NaN is a special value", "true, NaN is the same as NaN"),
      answer = setOf(0),
    ),

    // MAX value * 2
    LessonPage.MultipleChoice(
      title = "Twice the max",
      question = """
What will be the value in `f`?

```kotlin
val f = Float.MAX_VALUE * 2f
```
""",
      explanation = """
When exceeding the maximum possible value, floating point numbers are rounded to `+∞`.
""",
      choices = listOf(
        "Infinity",
        "The max value: we can't go higher",
        "Float.MIN_VALUE: it will loop like integers",
      ),
      answer = setOf(0),
    ),

    LessonPage.InfoPage(
      title = "Summary",
      """
Floating points represent non integer numbers.
 
They are approximations of real numbers.

They run on your computer and use a binary representation.

They may return strange results due to conversions between these 2 representations.

Special values are the *max* value, the *min* value, *infinity* and *NaN*.

`NaN` indicates that a computation went wrong.

It's possible to convert between `Int`s and `Float`s with `.toFloat()` and `.toInt()`.
"""
    )
  )
)
