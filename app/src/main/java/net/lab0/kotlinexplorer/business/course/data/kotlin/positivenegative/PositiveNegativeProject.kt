package net.lab0.kotlinexplorer.business.course.data.kotlin.positivenegative

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object PositiveNegativeProject : LessonImpl(
    id = "kotlin.positivenegative.project",
    title = "+ / - The Project",
    pages = listOf(
        LessonPage.CodeQuestionPage(
            title = "Main",
            question = """
As with any program, we need a main function. Declare it
""",
            snippet = """
${p(0)}${p(1)}{
    // next part goes here
}
""",
            explanation = """
`fun main` not `main fun`, the order is important.
""",
            answer = listOf("fun main", "()"),
            confusion = listOf("main fun", "{}", "[]"),
        ),
        LessonPage.CodeQuestionPage(
            title = "Value",
            question = """
Declare a value `n` and assign it `116`
""",
            snippet = """
fun main() {
    ${p(0)} ${p(0)} ${p(0)} 116
    // next part goes here
}
""",
            explanation = """
`var` is for variables, you will see it later.

`let` is not Kotlin.

`:=` is not a Kotlin assignment.
""",
            answer = listOf("val", "n", "="),
            confusion = listOf("var", "let", "", ":="),
        ),
        // TODO: what is the problem with the variable `n`
LessonPage.CodeQuestionPage(
            title = "Prepare to print",
            question = """
Prepare to print something.
""",
            snippet = """
fun main() {
      val n = 116
    ${p(0)}${p(1)}
        // next part goes here
    ${p(2)}
}
""",
            explanation = """
`{}` is for code blocks.

`print` is case sensitive.
""",
            answer = listOf("print", "(", ")"),
            confusion = listOf("{", "}", "Print"),
        ),
LessonPage.CodeQuestionPage(
            title = "Positive test",
            question = """
Test that `n` is strictly positive (1 or more).
""",
            snippet = """
fun main() {
    val n = 116
    print(
        ${p(0)}${p(1)}n ${p(3)} ${p(4)}${p(5)}
        // next part goes here
    )
}
""",
            explanation = """
`{}` is for blocks. The `if`'s test must be in `()`.

`<=` is less than or equal. We want to check for strict positivity.
""",
            answer = listOf("if", "(", ">", "0", ")"),
            confusion = listOf("when", "{", "}", ">="),
        ),
LessonPage.CodeQuestionPage(
            title = "Print +",
            question = """
What should it print when the n os positive?
""",
            snippet = """
fun main() {
  val n = 116
  print(
      if(n > 0) ${p(0)} ${p(1)} ${p(2)}
      // next part goes here
  )
}
""",
            explanation = """

""",
            answer = listOf(),
            confusion = listOf(),
        ),
LessonPage.CodeQuestionPage(
            title = "Negative test",
            question = """
Test that `n` is strictly negative (-1 or less).
""",
            snippet = """

""",
            explanation = """

""",
            answer = listOf(),
            confusion = listOf(),
        ),
    )
)