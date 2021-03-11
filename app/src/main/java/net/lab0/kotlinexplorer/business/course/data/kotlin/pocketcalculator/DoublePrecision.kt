package net.lab0.kotlinexplorer.business.course.data.kotlin.pocketcalculator

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks

object DoublePrecision : LessonImpl(
  id = "",
  title = "",
  pages = listOf(

    // how to store 1e99 -> use double
//    LessonPage.InfoPage(),

    // Declare double PI
    LessonPage.CodeQuestionPage(
      title = "Declare π",
      question = """
Declare the value π with double precision.
""",
      snippet = """
val pi:Double = ${KotlinCodeWithBlanks.placeholder(0)}
""",
      explanation = """
By default, a floating point value is expressed in double precision.
""",
      answer = listOf("3.1415"),
      confusion = listOf("3.1415f", "3.1415d"),
    ),
// difference between float and double
// declare 1
// declare -1
// declare big number 1E10
// declare big number 1E100
// declare +inf
// declare -inf
// declare NaN
// x/inf = 0
// 3 / 0 = Inf
// 0 / 0 = NaN
// Rounding
// convert between number types
// interaction with Ints in calculation
  )
)
