package net.lab0.kotlinexplorer.business.course.data.kotlin.helloworld

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object Main : LessonImpl(
  id = "kotlin.helloworld.main",
  title = "Main",
  pages = listOf(

    // declare first ever main
    LessonPage.CodeQuestionPage(
      title = "The Main Function",
      question = """
Declare a function named `main`.
""",
      snippet = """
${p(0)}(){ /* block */ }
""",
      explanation = """
`fun` declares a function.

The name of the declared function is between `fun` and the opening parenthesis `(`.

`Main` and `main` are two different identifiers.

The `main` function has an execution block, starting with `{` and ending with `}`.

Here it only contains the `/* block */` comment.
This is where you can write code.
The block doesn't contain any code (only a comment), it's an empty block.
""",
      answer = listOf("fun main"),
      confusion = listOf("fun Main")
    ),

    // Rewrite the main function
    LessonPage.CodeQuestionPage(
      title = "Write main",
      question = """
Write an empty main function.
""",
      snippet = """
${p(0)} ${p(1)}${p(2)}${p(3)}${p(4)}
// block
${p(5)}
""",
      explanation = """
A code block can span over several lines.

Extra whitespace doesn't count. This function can also be written

```kotlin
fun   main (  )   {

  // empty space

}
```

but can't be written

```kotlin
funmain(){}
```

because `fun` and `main` must be separate words.
""",
      answer = listOf("fun", "main", "(", ")", "{", "}"),
    ),

    // println in main
    LessonPage.CodeQuestionPage(
      title = "Print in main block",
      question = """
Make the main function print `hello`.
""",
      snippet = """
fun main() ${p(0)}
""",
      explanation = """
The body of the function goes between curly brackets. It starts at `{` and ends at `}`.

To make the function more readable, the body inside the function is indented. This is optional.
""",
      answer = listOf("{\n\tprintln(\"hello\")\n}"),
      confusion = listOf("{\n}\n\tprintln(\"hello\")", "(\n\tprintln{\"hello\"}\n)")
    ),

    LessonPage.MultipleChoice(
      title = "What does this print?",
      question = """
```kotlin
fun main(){
  print("3")
  print("2")
  print("1")
  print("Go!")
}
```
""",
      explanation = """
The code inside a block is executed in the order it is written, from top to bottom.
""",
      choices = listOf("321Go!", "Go!123", "It could show then in any order"),
      answer = setOf(0),
    ),

    // summary
    LessonPage.InfoPage(
      title = "Summary",
      """
The `main` function is the entry point of a program.
This is where the program starts its execution.

It executes the content in the `main`'s body, the code
block inside `{` and `}`, in the order it is written.
"""
    )
  )
)

