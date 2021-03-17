package net.lab0.kotlinexplorer.business.course.data.kotlin.helloworld

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object Comment : LessonImpl(
  id = "kotlin.helloworld.comment",
  title = "Comment",
  pages = listOf(
    // introduce comment
    LessonPage.CodeQuestionPage(
      title = "Comment it",
      question = """
Comment this single line.
""",
      snippet = """
${p(0)} print("Not executed")
""",
      explanation = """
Double slash `//` starts a single-line comment.

The rest of the line, after `//`, will not be executed
""",
      answer = listOf("//"),
      confusion = listOf(),
    ),

    // code in comments is not executed
    LessonPage.CodeQuestionPage(
      title = "Semi \uD83D\uDE9A",
      question = """
Print only `Semi`
""",
      snippet = """
// you can scroll the code horizontally to see what is written on very long lines like this one
print("Semi")${p(0)}print("trailer")${p(1)}print("truck") 
""",
      explanation = """
The whole line after `//` is commented.

No need to repeat it before each piece of code.
""",
      answer = listOf("//", ""),
      confusion = listOf(""),
    ),

    // comment only the line on which it is
    LessonPage.MultipleChoice(
      title = "Chit chat",
      question = """
Tell what the next line does

```kotlin
//// The following line prints hello
print("hello")
```
""",
      explanation = """
The comment stops at the end of the line. `print` is executed.

Doubling the comment `////` doesn't make the comment span over several lines.
`////test` is actually a comment indicator `//` followed by some text `//test`.
""",
      choices = listOf(
        "Print hello, because the comment is for a single line",
        "Nothing, the comment is doubled, commenting 2 lines"
      ),
      answer = setOf(0),
    ),

    // disable code
    LessonPage.CodeQuestionPage(
      title = "Disable code",
      question = "Print `B` only",
      snippet = """
${p(0)} print("A")
${p(1)} print("B")
""",
      explanation = """
Commented code will not be executed.
""",
      answer = listOf("//", "  "),
    ),

    // Multi line comments
    LessonPage.CodeQuestionPage(
      title = "Talkative",
      question = "Comment all the lines",
      snippet = """
${p(0)}
   Lorem ipsum dolor sit amet,
   consectetur adipiscing elit,
   sed do eiusmod tempor incididunt
   ut labore et dolore magna aliqua.
${p(1)}
""",
      explanation = """
Sometimes one line of explanation is not enough.
In these cases, you can either use multiple single line comments `//`
or a single multi line comment `/* */`.

The comment block starts at `/*` and ends at `*/`.

The equivalent with single line comments is

```kotlin
// Lorem ipsum dolor sit amet,
// consectetur adipiscing elit,
// sed do eiusmod tempor incididunt
// ut labore et dolore magna aliqua.
```
""",
      answer = listOf("/*", "*/"),
      confusion = listOf("//", "//")
    ),

    // Commented code doesn't execute
    LessonPage.MultipleChoice(
      title = "Skip code",
      question = """
Which part of the code will be executed?

```
print("1") // print("2")
/*
print("3")
*/
print("4")
```
""",
      explanation = """
```kotlin
print("1") // print("2")
/*
print("3")
*/
print("4")
```

`print("2")` is not executed because it's a single line comment.

`print("3")` is not executed because it's in a multi line comment.
""",
      choices = listOf("print(\"1\")", "print(\"4\")", "print(\"2\")", "print(\"3\")"),
      answer = setOf(0, 1),
    ),

    // avoid giving arguments with multi line comments
    LessonPage.CodeQuestionPage(
      title = "Print nothing",
      question = "**Don't** print `Something`",
      snippet = """
println(${p(0)}"Something"${p(1)})
""",
      explanation = """
Comments can be used to ignore some of the code or add comments
right next to the element to comment, like this:

```kotlin
print(11*11 /* =121 */)
```

Here `//` can't be used, because the closing parenthesis `)` would be missing.

The opening multi line comment mark `/*`, works in pair with another `*/`.
""",
      answer = listOf("/*", "*/"),
      confusion = listOf("//", "//")
    ),

    // dont close single line comments with multi line comments
LessonPage.MultipleChoice(
      title = "",
      question = """
What will be printed?
        
```
// */ print("always")
```
""",
      explanation = """
`//` ignores the rest of the line. You can't stop it
even with a multi line comment closing mark `*/`
""",
      choices = listOf(
        "Nothing, because // comments the whole line",
        "Always, because */ stops the comment",
      ),
      answer = setOf(0),
    ),

    // summary
    LessonPage.InfoPage(
      title = "Summary",
      """
Comments will be used a lot to add information that can't be expressed with code.

In this app, they will also be used to indicate positions and extra information 
about what must be done directly inside the code.
"""
    )
  )
)
