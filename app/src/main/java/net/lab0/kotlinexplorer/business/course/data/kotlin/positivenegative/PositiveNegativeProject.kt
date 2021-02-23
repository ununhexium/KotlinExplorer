package net.lab0.kotlinexplorer.business.course.data.kotlin.positivenegative

import net.lab0.kotlinexplorer.business.course.data.kotlin.dollar
import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object PositiveNegativeProject : LessonImpl(
    id = "kotlin.positivenegative.project",
    title = "+ / - : The Project",
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
Declare a value `n` and assign `116` to it.
""",
            snippet = """
fun main() {
    ${p(0)} ${p(1)} ${p(2)} 116
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
        LessonPage.MultipleChoice(
            title = "Meh 😕",
            question = """
What's the problem with the code below?

```kotlin
fun main() {
val n = 116
]
```
""",
            explanation = """
##### Naming

`n` is not a good variable name because you don't know what it is by looking at its name.

A better name would be `numberToTest`.
Unfortunately I have a very small character budget
if I want the code to fit on your phone.

##### Code style

The whole line `val n = 116` should be indented for readability.

##### Closing a block

`}` closes a block, not `]`.
""",
            choices = listOf(
                "Missing indentation",
                "n is a bad variable name",
                "116 should be in quotes",
                "The last character should be a '}'"
            ),
            answer = setOf(0, 1, 3),
        ),
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
        ${p(0)}${p(1)}n ${p(2)} ${p(3)}${p(4)} // next part goes here
    )
}
""",
            explanation = """
`{}` is for blocks.

The `if`'s test must be in `()`.

`>=` is more than or equal. We want to check for strict positivity.
""",
            answer = listOf("if", "(", ">", "0", ")"),
            confusion = listOf("when", "{", "}", ">="),
        ),
        LessonPage.CodeQuestionPage(
            title = "Print +",
            question = """
Tell the world that `n` is positive.

Show `It's >0: 116`.
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
`"It's >0: ` doesn't work because the string needs to be closed 
before using `+` for concatenation.
""",
            answer = listOf("\"It's >0: \"", "+", "n"),
            confusion = listOf("-", "\"It's >0: "),
        ),
        LessonPage.CodeQuestionPage(
            title = "Negative test",
            question = """
Test that `n` is strictly negative (-1 or less).
""",
            snippet = """
fun main() {
  val n = 116
  print(
      if (n > 0) "It's >0: " + n
      ${p(0)} ${p(1)}${p(2)} ${p(3)} ${p(4)} ${p(5)}${p(6)} // next part goes here
  )
}
""",
            explanation = """
This `if` sequence must return a value to print.
Therefore we need build a chain and cover all the if/else cases.

Using `if` only will be invalid as the else block would be missing.
""",
            answer = listOf("else", "if", "(", "n", "<", "0", ")"),
            confusion = listOf("<=", "elsif", "then", "number"),
        ),
        LessonPage.CodeQuestionPage(
            title = "Print -",
            question = """
Give a message in the case `n` is negative. `It's <0: 116`
""",
            snippet = """
fun main() {
  val n = 116
  print(
      if(n > 0) "It's >0: " + n
      else if(n < 0) ${p(0)} ${p(1)}${p(2)}${p(3)}
      // next part goes here
  )
}
""",
            explanation = """
`\"It's <0: \"` doesn't work because the string template must be inside the string.
This string ends at the second `"`. After it, it's too late for templates.
""",
            answer = listOf("\"It's <0: ", "$dollar", "n", "\""),
            confusion = listOf("+", "\"It's <0: \""),
        ),
        LessonPage.CodeQuestionPage(
            title = "Nought",
            question = """
Complete the if sequence will the last possible case
""",
            snippet = """
fun main() {
  val n = 116
  print(
      if (n > 0) "It's >0: " + n
      else if (n < 0) "It's <0: ${dollar}n"
      ${p(0)} ${p(1)}
  )
}
""",
            explanation = """
This `if` sequence must return a value to print.
As this is the last statement, we need to stop the `if`/`else` chain and
 return the last possible value.
""",
            answer = listOf("else", "\"It's 0\""),
            confusion = listOf("else if"),
        ),
        LessonPage.InfoPage(
            title = "You did it!",
            """
Congratulations for write the first program that executes a little bit of logic!

You can take a rest. 😊
"""
        )
    )
)
