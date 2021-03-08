package net.lab0.kotlinexplorer.business.course.data.kotlin.pocketcalculator

import net.lab0.kotlinexplorer.business.course.data.kotlin.dollar
import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object StringSpecialChars : LessonImpl(
  id = "kotlin.pocketcalculator.stringspecialchars",
  title = "String Special Characters",
  pages = listOf(
    // characters declaration
    LessonPage.CodeQuestionPage(
      title = "Characters",
      question = """
Declare a single character `A`
""",
      snippet = """
val a = ${p(0)}A${p(0)}
""",
      explanation = """
Single quotes `'` declare single characters.
""",
      answer = listOf("'"),
      confusion = listOf("\"", "`"),
    ),

    // characters type
    LessonPage.CodeQuestionPage(
      title = "Char",
      question = """
Declare the type for this single character.
""",
      snippet = """
val a:${p(0)} = 'A'
""",
      explanation = """
`Char`acter is the type of a single character.
""",
      answer = listOf("Char"),
      confusion = listOf("ShortString", "Character"),
    ),

    // escape double quotes
    LessonPage.CodeQuestionPage(
      title = "Double quote",
      question = """
Put double quotes in the sentence.
""",
      snippet = """
// I said "Hi!"
val sentence = ${p(0)}I said ${p(1)}Hi!${p(1)}${p(0)}
""",
      explanation = """
`"` indicates the start and the end of the string.
When used inside a string, it must be escaped with `\`,
otherwise it will be interpreted as the end of the string.

`\"` is called an escape sequence.
""",
      answer = listOf("\"", "\\\""),
      confusion = listOf("\"", "\\\"", "'", "'"),
    ),

    // don't escape normal chars
    LessonPage.CodeQuestionPage(
      title = "Exclamation mark",
      question = """
Declare a string containing the exclamation mark `!` character.
""",
      snippet = """
// Hi Kotlin!
val sentence = "Hi Kotlin${p(0)}"
""",
      explanation = """
`!` is not a special character in strings and doesn't need any special treatment.

Worse, using `\!` is actually an error. It's an invalid escape sequence.
""",
      answer = listOf("!"),
      confusion = listOf("\\!"),
    ),

    // backslash
    LessonPage.CodeQuestionPage(
      title = "Backslash",
      question = """
Use a backslash.
""",
      snippet = """
// C:\
val windowsDrive = "C:${p(0)}"
""",
      explanation = """
`\` is a special character: it's *the* escape character.

When encountered, the next character will be interpreted as an escape code.

To make a single backslash, write a double backslash `\\`.
""",
      answer = listOf("\\\\"),
      confusion = listOf("\\"),
    ),

    // single quote may be escaped or not
    LessonPage.CodeQuestionPage(
      title = "Single quote",
      question = """
Declare a string containing a single quote.
""",
      snippet = """
// It's Kotlin!.
val sentence = "It${p(0)}s Kotlin!"
""",
      explanation = """
`'` is a special character in character literals.

They work the same way as strings but represent a single character.

Here is an example:

```kotlin
val character = 'A'
val quote = '\''
```

Because of this, it's possible to escape a single quote with `\'` event when inside a string.

So both answers are actually valid, but prefer a simple non escaped quote `'` for readability.
""",
      answer = listOf("'"),
      confusion = listOf("\\'"),
      validator = {
        it == listOf("\'") || it == listOf("'")
      }
    ),

    // New line linux
    LessonPage.CodeQuestionPage(
      title = "New Linux line",
      question = """
Write a function that prints the `input` and then prints a new line.
""",
      snippet = """
fun println(input: String) {
  print(${p(0)})
  print(${p(1)})
}
""",
      explanation = """
`\n` represents a new line (in MacOS X, Linux, Unix, Posix).

`\n` prints a line feed (fills the line until the cursor returns to the first column of the next line).

`\n` must be used as a character `'\n'` or a string `"\n"`.

The `println` function already exists in Kotlin and adapts to all operating systems.

```kotlin
println("a")
println("b")
```

will print
 
```
a
b
```

whereas
 
```kotlin
print("a")
print("b")
``` 

will print
 
```
ab
```
""",
      answer = listOf("input", "'\\n'"),
      confusion = listOf("<br>", "⏎", "\"input\"", "\\n"),
    ),

    // New line on Windows
    LessonPage.CodeQuestionPage(
      title = "New Windows line",
      question = """
Write this on 3 separate lines.
""",
      snippet = """
print("line1${p(0)}${p(1)}line2${p(0)}${p(1)}line3")
""",
      explanation = """
`\n\r` represents a new line on Windows systems.

It's actually 2 separate characters: carriage return `\r` and line feed `\n`.
""",
      answer = listOf("\\r", "\\n"),
      confusion = listOf("<br>", "⏎"),
    ),

    // Tabulation
    LessonPage.CodeQuestionPage(
      title = "Tabulation",
      question = """
Align the output with tabulation characters.
""",
      snippet = """
println("1${p(0)} is a number")
println("22${p(0)} is a number")
println("7777777${p(0)} is a number")
println("88888888${p(0)} is a number")
""",
      explanation = """
The tabulation `\t` character goes to the next column that is a multiple of 8.

The output of the code above is:

```kotlin
// the first column has index 0
//23456789012345678
1       is a number
22      is a number
7777777 is a number
88888888        is a number
//2345678901234567890123456
```
""",
      answer = listOf("\\t"),
      confusion = listOf("<tab>", "        "),
    ),

    // dollar
    LessonPage.CodeQuestionPage(
      title = "Money laundering",
      question = """
Don't print the content of the variable.
""",
      snippet = """
val name = "James"
// Hello ${dollar}name
val template = "Hello ${p(0)}name"
""",
      explanation = """
In string templates, to avoid printing a variable,
the dollar must be preceded by the escape character.

```kotlin
val name = "James"

// Hello ${dollar}name
val template = "Hello \${dollar}name"

// Hello James
val template = "Hello ${dollar}name"
```
""",
      answer = listOf("\\$"),
      confusion = listOf("$"),
    ),

    // which lines are escapes correctly?
    LessonPage.MultipleChoice(
      title = "Escapism",
      question = """
Which of the following lines has valid escape sequences.

```kotlin
// It's me, Mario
val hello = "It's me, Mario!"

// Line A.
// Line B.
val lineAandB = "Line A.\\NLine B."

// Line 1.
// Line 2.
val line1and2 = "Line 1.\\nLine 2."

//0       8       16
val spacing = "0\t8\t16"

// ?
val surprise = "\reset"
```
""",
      explanation = """
`Line A.\NLine B.`
        
`\N` is not a valid escape sequence.
Escape sequences are case sensitive.
The valid escape sequence would be `\n`.

`\reset`

It's a carriage return `\r` followed by `eset`.
Probably not what you want to show but it's not an error.
""",
      choices = listOf(
        "It's me, Mario!",
        "Line 1.\\nLine 2.",
        "0\\t8\\t16",
        "\\reset",
        "Line A.\\NLine B."
      ),
      answer = setOf(0, 1, 2, 3),
    ),

    // summary
    LessonPage.InfoPage(
      title = "Summary",
      markdown = """
`'` surrounds single characters declarations.

`\` is the escape character.

The special characters to escape are

```kotlin
// The escape character \
val backslash = '\\'

// Tabulation skips to the next column
// index that is a multiple of 8
val tabulation = '\t'

// Line feed for Posix-compliant systems
// MacOS X, Linux, Unix, BSD, ...
val lineFeed = '\n'

// For windows new lines: \r\n
val carriageReturn = '\r'

// single quote
// to declare a single quote character
val quote = '\''

// To escape double quotes in strings
val doubleQuote1 = "\""
val doubleQuote2 = '"'

// Avoid printing variables in templates
val test = "HELLO"
val dollarEscape = "\${dollar}test"
// prints ${dollar}test, not HELLO
print(dollarEscape)
```
"""
    )
  ),
)
