package net.lab0.kotlinexplorer.business.course.data.kotlin

import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object Standby {

  init {

    LessonPage.CodeQuestionPage(
      title = "Mixed feelings",
      question = """
`b` is the result on a previous computation.
You don't know for sure if it's true or false. It could be either.
In this specific example, it is `true`.

What is the type of `unknown`?
""",
      snippet = """
val b = true // or false
val unknown: ${p(0)} =
    if (b) 1 else "false"
""",
      explanation = """
Here, `b` may either be `true` or `false`.

Even if in this specific case you see that `unknown` will be an `Int`,
it's not possible to know this in the general case.

Therefore `unknown` will have a type that is common to both `Int` and `String`.
This type is called `Any`.
""",
      answer = listOf("Any"),
      confusion = listOf("Int", "String"),
    )


    // complications intro
    LessonPage.InfoPage(
      title = "Unicode",
      markdown = """
Computers can only handle numbers, not characters.

Each characters is therefore represented by a number.

There are many different ways to do this translation but the most common is UTF-8.

Without going into details, UTF-8 assigns a number to each character. Here are a few:

```
${
        listOf('A', 'a', 'Z', '0', '\n', '\\').joinToString("\n") {
          "$it → ${it.toInt()}"
        }
      }
```

When you write something like `print("A")`, this A is transformed
 into a `65` and that 65 is shows as "A" in pixels.
 This is of course greatly oversimplified but these are the essentials steps.
 
Kotlin has a way to express any arbitrary character using its codepoint.

This capability will be explored through the next pages.
""",
    )

// Print A
    LessonPage.CodeQuestionPage(
      title = "Print A",
      question = """
Print `A` using the unicode codepoint.

Hint: 'A' is 
""",
      snippet = """
print(${p(0)})
""",
      explanation = """

""",
      answer = listOf("\\u0041"),
      confusion = listOf(),
    )

    // complications
    LessonPage.CodeQuestionPage(
      title = "Complications",
      question = """
Each character has a corresponding code point.
""",
      snippet = """

""",
      explanation = """

""",
      answer = listOf(),
      confusion = listOf(),
    )

    // unicode
    LessonPage.CodeQuestionPage(
      title = "Bell \uD83D\uDD14",
      question = """

The bell character is a special characters that will make the computer make a *beep*.

In all current computers, which don't have an internal speaker anymore,
the operating system can catch this and play a *ding* sound on the normal speakers instead
or simply ignore it.
 
Print a bell character.
""",
      snippet = """
print(${p()})
""",
      explanation = """

""",
      answer = listOf(),
      confusion = listOf(),
    )
  }
}


// in Strings
// TODO: String with double quotes and other escape characters.

// in Functions:
// TODO parameter name and type


// TODO more operators: conjunction
// TODO more operators: disjunction
