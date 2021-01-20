package com.example.jetpackcomposeexplorer.model.course.data.kotlin.datatypes

import com.example.jetpackcomposeexplorer.model.KotlinCodeWithBlanks.Companion.placeholder
import com.example.jetpackcomposeexplorer.model.course.LessonImpl
import com.example.jetpackcomposeexplorer.model.course.LessonPage.CodeQuestionPage
import com.example.jetpackcomposeexplorer.model.course.LessonPage.InfoPage

object Numbers : LessonImpl(
    id = "kotlin.datatypes.numbers",
    title = "Numbers",
    dependencies = listOf(
        Introduction
    ),
    pages = listOf(
        // numbers
        InfoPage(
            "Numbers",
            """
The numbers in Kotlin can use different representations 
depending on the *precision* and *type* of number.

### Integers

Whole numbers like 1, 2, 42, -1.

### Floating point

Floating point numbers are numbers like `1.234e^89`, an approximation of pi (3.1415...).
"""
        ),
        // Int
        CodeQuestionPage.singleChoice(
            "Integer",
            """
Declare a value with type integer.
""",
            """val one:${placeholder(0)} = 1""",
            """
`Int` accepts integers in the range (${Int.MIN_VALUE}, ${Int.MAX_VALUE}), that is (2^31, 2^31-1).

`0` is in the middle of this range.
""",
            "Int"
        ),
        // Float
        CodeQuestionPage.singleChoice(
            "Floating point",
            """
Declare a value with type float.
""",
            """val pi:${placeholder(0)} = 3.1415f""",
            """
`Float` is for floating point numbers in the range (${Float.MIN_VALUE}, ${Float.MAX_VALUE}).

Note the `f` letter in the end of the number. This is to specify that the number is a `f`loat.
""",
            "Float"
        ),
        // Infinity
        CodeQuestionPage(
            "Infinity ∞",
            """
Assign the value +∞ (infinity) to the value `inf`.
""",
            """
val infinity:Float = ${placeholder(0)}
""",
            """
              Floating point numbers have special values for 
              * infinity
              * -infinity
            """,
            choices = listOf(
                "Float.POSITIVE_INFINITY",
                "Float.NEGATIVE_INFINITY"
            )
        ) {
          it == listOf("Float.POSITIVE_INFINITY")
        },

        // Long
        CodeQuestionPage.singleChoice(
            "Long integers",
            """
9876543210 is too big to fit in an integer.

An `Int`'s max value is around 2^31 (${Int.MAX_VALUE})
""",
            """
val tooBigForInt: ${placeholder(0)} = 9876543210
""",
            """
Use a long integer (`Long`) to store a number in the range (${Long.MIN_VALUE}, ${Long.MAX_VALUE}), that is (2^63, 2^63-1).
""",
            "Long"
        ),

        // Double
        CodeQuestionPage.singleChoice(
            "Double precision",
            """
10^99 is too big to fit in an single precision floating point number.
A `Float`'s max value is around 3e38 (${Float.MAX_VALUE})
""",
            """
val tooBigForFloat: ${placeholder(0)} = 1e99
""",
            """
Use a double precision floating point number (`Double`) to store a number in the range (${Double.MIN_VALUE}, ${Double.MAX_VALUE}).

Just like `Float`s, `Double`s also have +∞ and special values -∞.
""",
            "Double"
        ),
    )
)

// TODO: NaN for floats and duplicated 0
