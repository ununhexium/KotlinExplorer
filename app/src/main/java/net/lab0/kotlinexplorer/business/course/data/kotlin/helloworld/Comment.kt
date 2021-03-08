package net.lab0.kotlinexplorer.business.course.data.kotlin.helloworld

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object Comment : LessonImpl(
  id = "kotlin.helloworld.comment",
  title = "Comment",
  pages = listOf(
    LessonPage.CodeQuestionPage(
      title = "Chit chat",
      question = "Tell what the next line does",
      snippet = """
${p(0)} The following line prints hello
print("hello")
""",
      explanation = """
Double slash `//` starts a single line comment.

The text after `//` will only be shown in the source code. If it's code, it will not be executed.
""",
      answer = listOf("//"),
      confusion = listOf("/*", "--", "!", "#")
    ),
    LessonPage.CodeQuestionPage(
      title = "Ignore it",
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
In these cases, one can either use multiple single line comments `//`
or a single multi line comment `/* */`.

The commented block starts at `/*` and ends at `*/`.
""",
      answer = listOf("/*", "*/"),
      confusion = listOf("//", "//", "(*", "*)")
    ),
    // Commented code doesn't execute
    LessonPage.MultipleChoice(
      title = "Skip code",
      // TODO: don't use kotlin but only monospace so it doesn't give a hint
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

`print("3")` is not executed because it's a multi line comment.
""",
      choices = listOf("print(\"1\")", "print(\"4\")", "print(\"2\")", "print(\"3\")"),
      answer = setOf(0, 1),
    ),
    //
    LessonPage.CodeQuestionPage(
      title = "Print nothing",
      question = "**Don't** print `Something`",
      snippet = """
print(${p(0)}"Something"${p(1)})
""",
      explanation = """
Comments can be used to remove some of the code or add comments right next to the element to comment, like this:

```kotlin
print(11*11 /* =121 */)
```

Here `//` can't be used twice, because the closing `(` would be missing.

When a comment is open with `/*`, it must be closed with `*/`.

```kotlin
// */ this is in the comment open with //
This is not commented
```
""",
      answer = listOf("/*", "*/"),
      confusion = listOf("//", "//")
    ),
    LessonPage.InfoPage(
      title = "Comment squeeze",
      """
Comments will be used a lot to add information that can't be expressed with code.

In this app, they will also be used to indicate positions and extra information 
about what must be done directly inside the code.
"""
    )
  )
)
