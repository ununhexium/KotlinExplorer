package net.lab0.kotlinexplorer.business.course.data.kotlin.pocketcalculator

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object PocketCalculatorProject : LessonImpl(
  id = "kotlin.pocketcalculator.project",
  title = "Calculator project",
  pages = listOf(

    // declare square
    LessonPage.CodeQuestionPage(
      title = "Square",
      question = """
Declare a function that computes the square of an integer.
""",
      snippet = """
fun square(${p(0)}: ${p(1)}): ${p(2)} {
  ${p(3)} ${p(4)} * ${p(4)}
}
""",
      explanation = """
""",
      answer = listOf("a", "Int", "Int", "return", "a"),
      confusion = listOf("b"),
    ),

    // declare square root
    LessonPage.CodeQuestionPage(
      title = "Square root function declaration",
      question = """
Declare a function `squareRoot` that takes an integer.

For the return type, remember that the square root is rarely an integer.
""",
      snippet = """
${p(0)}(a: ${p(1)}): ${p(2)} {
  // next steps
}
""",
      explanation = """
When computing a square root, the result will rarely be a whole number.

Returning an `Double` is much more useful than returning the closest Integer.
""",
      answer = listOf("fun squareRoot", "Int", "Double"),
      confusion = listOf("fun square_root", "Int", "Double"),
    ),

    // declare square root
    LessonPage.CodeQuestionPage(
      title = "Square root function declaration",
      question = """
Compute the square root.

You can use the existing function `sqrt` that is located in the package `kotlin.math`.

Note: `sqrt` takes aa `Double` as input and returns a `Double.
""",
      snippet = """
${p(0)} ${p(1)}.sqrt

fun squareRoot(a: Int): Double {
  return ${p(2)}(a${p(3)})
}
""",
      explanation = """

""",
      answer = listOf("import", "kotlin.math", "sqrt", ".toDouble()"),
      confusion = listOf("package", ".toFloat()", ".toInt()"),
    ),

    // use in main
    LessonPage.CodeQuestionPage(
      title = "Square and root",
      question = """
Print the square of `a`, 
the square root of `a`, 
and the minimum value between `a` and `b`,

Note: the minimum function is available as `min` in `kotlin.math`
""",
      snippet = """
fun main() {
  val a = 3
  val b = 1
  println(${p(0)})
  println(${p(1)})
  println(${p(2)}${p(3)}${p(4)}(a, b))
}
""",
      explanation = """

""",
      answer = listOf("square(a)", "squareRoot(a)", "kotlin.math", ".", "min"),
      confusion = listOf("square(a*a)", "squareRoot(square(a))", " "),
    ),
  )
)