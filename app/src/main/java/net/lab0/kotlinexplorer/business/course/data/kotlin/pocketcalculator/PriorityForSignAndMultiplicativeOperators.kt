package net.lab0.kotlinexplorer.business.course.data.kotlin.pocketcalculator

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object PriorityForSignAndMultiplicativeOperators : LessonImpl(
  id = "kotlin.pocketcalculator.priorityforsignandmultiplicativeoperators",
  title = "Priority for +, -, *, /, %",
  pages = listOf(

    // declare negative number
    LessonPage.CodeQuestionPage(
      title = "Negative number",
      question = """
Declare a negative number.
""",
      snippet = """
val veryCold = ${p(0)}10
""",
      explanation = """
`-` in this context is just like the standard minus sign used in math.

In front of a number ot makes a negative number.
""",
      answer = listOf("-"),
      confusion = listOf("+", "--"),
    ),

    // declare positive number
    LessonPage.CodeQuestionPage(
      title = "Positive number",
      question = """
Declare an explicitly positive number.
""",
      snippet = """
val veryHot = ${p(0)}100 
""",
      explanation = """
A number like `100` is positive by default, but it's possible to use `+` to show it.
""",
      answer = listOf("+"),
      confusion = listOf("-", "++"),
    ),

    // prio 2
    LessonPage.InfoPage(
      title = "Priority #2",
      """
There are many operators (about 40) and only some of them will be shown here.

`+` and `-` are the operators with the second highest priority.

You didn't see the operator with the highest priority yet.

The operators in this chapter will be ordered from the highest priority to the lowest priority.
"""
    ),

    // multiplication
    LessonPage.CodeQuestionPage(
      title = "Multiply",
      question = """
Make this true.
""",
      snippet = """
3 ${p(0)} 2  // =6
2 ${p(0)} 3  // =6
""",
      explanation = """
The multiplication is commutative and has a lower priority than the prefixes `+` and `-`.

Important: just like in mathematics, the addition has a lower 
priority than the multiplication. Here `+` and `-` are the sign symbols,
 like in `+1` or `-1`
""",
      answer = listOf("*"),
      confusion = listOf("/", "x"),
    ),

    // division without remainder
    LessonPage.CodeQuestionPage(
      title = "Division",
      question = """
Divide and conquer.
""",
      snippet = """
100 ${p(0)} 5  // =20
""",
      explanation = """
A division between integers is *always* an integer.

```kotlin
100 / 5  // =20
```
""",
      answer = listOf("/"),
      confusion = listOf("*", "%", "\\"),
    ),

    // division with remainder
    LessonPage.CodeQuestionPage(
      title = "Division with remainder",
      question = """
Divide.
""",
      snippet = """
20 ${p(0)} 3  // =6 not 6.666
""",
      explanation = """
20 = 3 * `6` + 2

If the result of the division is not an integer, the result is truncated.

Note how the result above is not `7` but `6` event if the division's result is closed to 7.
""",
      answer = listOf("/"),
      confusion = listOf("%"),
    ),

    // truncate pi
    LessonPage.MultipleChoice(
      title = "Truncate π",
      question = """
Truncate this number: `3.1415`
""",
      explanation = """
Truncating drops everything after the decimal point.
""",
      choices = listOf("3", "4"),
      answer = setOf(0),
    ),

    // truncate golden ratio
    LessonPage.MultipleChoice(
      title = "Truncate φ",
      question = """
Truncate this number: `1.6180`
""",
      explanation = """
Truncating drops everything after the decimal point.

It doesn't round anything, so `truncate(0.99999999)` is 0.
""",
      choices = listOf("1", "2"),
      answer = setOf(0),
    ),

    // Big numbers divided
    LessonPage.MultipleChoice(
      title = "Not much left",
      question = """
What is the result?

```kotlin
99 / 100
```
""",
      explanation = """
```kotlin
99 / 100  // =0  99 is the remainder
```

99 / 100 = 0.99, truncated: `0`

It doesn't matter how big the remainder is, 
as long as the dividend is not at least equal to the divisor,
the result of the division is 0.

`Almost 1` doesn't work for integers. It's either `0` or `1`, nothing in between.

""",
      choices = listOf("Exactly 0", "Almost 1", "99"),
      answer = setOf(0),
    ),

    // Remainder
    LessonPage.CodeQuestionPage(
      title = "Remainder",
      question = """
Compute the remainder.
""",
      snippet = """
val remainder = 11 ${p(0)} 3
""",
      explanation = """
`%` is the operator to compute the remainder between 2 integers.

11 = 3 * 3 + `2`

11 % 3 = `2`

""",
      answer = listOf("%"),
      confusion = listOf("\\"),
    ),

    // Negative remainder
    LessonPage.CodeQuestionPage(
      title = "Negative remainder",
      question = """
Compute the remainder.
""",
      snippet = """
val remainder = -1 ${p(0)} 3
""",
      explanation = """
Notice the symmetry around 0.
        
3 = 1 * 3 + `0`
        
2 = 0 * 3 + `2`
        
1 = 0 * 3 + `1`

0 = 0 * 3 + `0`

-1 = 0 * 3 + `-1`

-2 = 0 * 3 + `-2`

-3 = -1 * 3 + `0`
""",
      answer = listOf("%"),
      confusion = listOf("\\"),
    ),

    // prio 4
    LessonPage.InfoPage(
      title = "Priority #4",
      """
`*`, `/` and `%` are the operators with the fourth highest priority.

So far we have:

1. *Secret*
2. `+`, `-`
3. *Secret*
4. `*`, `/`, `%`
"""
    ),

    // left to right
    LessonPage.CodeQuestionPage(
      title = "Left to right",
      question = """
Make this true.
""",
      snippet = """
8 ${p(0)} 5 ${p(1)} 4 == 10
""",
      explanation = """
When operators with the same priority are used in the same statement,
 like here, they are evaluated from left to right.

Here is an equivalent
 
8 * 5 = 40

40 / 4 = 10

or

8 * 5 / 4 = 10
""",
      answer = listOf("*", "/"),
      confusion = listOf(),
    ),

    // force the priority
    LessonPage.CodeQuestionPage(
      title = "Enforce the priority",
      question = """
Use parentheses to make this equal 8.
""",
      snippet = """
8 * ${p(0)} 5 / 4 ${p(1)}  // =8   not 10
""",
      explanation = """
The operations inside the parentheses are evaluated with a higher
precedence than the operations outside of the parentheses.

Here is an equivalent

8 * (5 / 4)

(5 / 4) * 8

5 / 4 * 8

1 * 8

8
""",
      answer = listOf("(", ")"),
      confusion = listOf("[", "]", "{", "}"),
    ),

    // prio 0
    LessonPage.InfoPage(
      title = "Priority #0",
      """
`()` force the order of the evaluation.

So far we have:

0. `()`
1. *Secret*
2. `+`, `-`
3. *Secret*
4. `*`, `/`, `%`
"""
    ),
  )
)
