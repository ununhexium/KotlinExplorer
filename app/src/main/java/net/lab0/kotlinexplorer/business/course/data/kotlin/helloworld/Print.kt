package net.lab0.kotlinexplorer.business.course.data.kotlin.helloworld

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object Print : LessonImpl(
  id = "kotlin.helloworld.println",
  title = "Printing",
  pages = listOf(
    LessonPage.CodeQuestionPage(
      title = "Use print",
      question = """Call the `print` function""",
      snippet = """${p(0)}("Hi!")""",
      explanation =
      """
The `print` function name is case sensitive,
you must use the name exactly as it is declared: `print`.

Printing allows you to show text to the user.
""",
      answer = listOf("print"),
      confusion = listOf("Print", "PRINT"),
    ),

    LessonPage.CodeQuestionPage(
      title = "Use println",
      question = """Call the `println` function""",
      snippet = """${p(0)}("Hi!")""",
      explanation =
      """
All function names are case sensitive.

`println` does the same as `print` and then goes back to the beginning of the next line.

```kotlin
print("1")
print("2")
print("3")
```

prints

```
123
```

but

```kotlin
println("1")
println("2")
println("3")
```

prints

```
1
2
3
```
""",
      answer = listOf("println"),
      confusion = listOf("Println", "printLn"),
    ),

    LessonPage.CodeQuestionPage(
      title = "Print Kotlin",
      question = """Print a string that contains `Kotlin`""",
      snippet = """print(${p(0)})""",
      explanation =
      """
When printing text, the text must be between double quotes `"`.

The quotes `"` are not displayed.
""",
      answer = listOf("\"Kotlin\""),
      confusion = listOf("Kotlin"),
    ),

    LessonPage.CodeQuestionPage(
      title = "Call the print function",
      question = """Call the print function""",
      snippet = """print${p(0)}"Hi! :)"${p(1)}""",
      explanation =
      """
To call the `print` function, or any other function, you must use `(` and `)`.

The values between `(` and `)` are called the arguments of the function.
Arguments tell the function what to use to do its job.
""",
      answer = listOf("(", ")"),
      confusion = listOf("{", "}"),
    ),

    LessonPage.MultipleChoice(
      title = "Wrong call",
      question = """
What's wrong with this call to `print`?

```
print "Kotlin"
```
""",
      explanation = """
```kotlin
print("Kotlin")
```

A function requires parentheses around its arguments.
""",
      choices = listOf(
        "Parentheses () are missing",
        "print is not the correct function name",
        "There must be no space between print and \"Kotlin\"",
      ),
      answer = setOf(0),
    ),

    LessonPage.MultipleChoice(
      title = "Wrong call",
      question = """
What's wrong with this call to `print`?

```
(Print "Kotlin" )
```
""",
      explanation = """
```kotlin
print("Kotlin")
```

Spaces don't matter when calling a function,
the position of the parentheses `()` and the arguments are important.
""",
      choices = listOf(
        "The parentheses are at the wrong position",
        "Print is not the correct function name",
        "There must be no space between print and \"Kotlin\"",
      ),
      answer = setOf(0, 1),
    ),

    LessonPage.CodeQuestionPage(
      title = "Finished",
      question = """Print `Finished!`""",
      snippet = """${p(0)}${p(1)}${p(2)}${p(3)}${p(4)}${p(5)}""",
      explanation =
      """
Remember `print`, `println` and strings, they will be used a lot later 😉.
""",
      answer = listOf("print", "(", "\"", "Finished!", "\"", ")"),
      confusion = listOf("'", "'"),
    ),

    LessonPage.InfoPage(
      "Summary",
      """
Printing is the most primitive way to communicate with the user,
but that's the easiest way to start showing information.
"""
    )
  )
)
