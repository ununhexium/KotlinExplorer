package net.lab0.kotlinexplorer.business.course.data.kotlin.positivenegative

import net.lab0.kotlinexplorer.business.course.data.kotlin.dollar
import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.LessonPage.CodeQuestionPage
import net.lab0.kotlinexplorer.business.domain.LessonPage.InfoPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object StringConcatenation : LessonImpl(
  id = "kotlin.positivenegative.stringconcatenation",
  title = "Concatenation",
  pages = listOf(
    // try concatenation with +
    CodeQuestionPage(
      title = "❤ + ❤",
      question = """
Concatenate these 2 hearts together.
""",
      snippet = """
val twoHearts = "❤" ${p(0)} "❤"
""",
      explanation = """
Between 2 strings, the concatenation operator `+` joins the strings together.

```kotlin
"❤" + "❤" == "❤❤"  // true
```
""",
      answer = listOf("+"),
    ),

    // multiple string concat
    CodeQuestionPage(
      title = "Multiple concatenations",
      question = """
Concatenate all these strings
""",
      snippet = """
val alphabet = "a" ${p(0)} "b" ${p(1)} "c" ${p(2)} "d"
""",
      explanation = """
`+` is an operator. It returns the 2 strings concatenated together.

```kotlin
val alphabet = "a" + "b" + "c" + "d"
```

```kotlin
val alphabet = "ab" + "c" + "d"
```

```kotlin
val alphabet = "abc" + "d"
```

```kotlin
val alphabet = "abcd"
```
""",
      answer = listOf("+", "+", "+"),
      confusion = listOf("$", "$", "$"),
    ),

    // multi line string concat
    CodeQuestionPage(
      title = "Multiple lines concatenation",
      question = """
Bring these 3 lines together.
""",
      snippet = """
val all =
  "first line" ${p(0)}
    "second line" ${p(1)}
    "third line"
""",
      explanation = """
When concatenating over several lines, the `+` must stay at the end of the previous line.

The following will not work

```kotlin
val all =
  "first line"
  + "second line"
  + "third line"
```

This is because Kotlin doesn't use any character to separate statements 
and `+` in front of a string could be a valid operation in some specific case.
""",
      answer = listOf("+", "+"),
      confusion = listOf("$$", "$$"),
    ),

    // templates
    CodeQuestionPage(
      title = "String Templates",
      question = """
Put Kotlin's age in the middle of this `String`.
""",
      snippet = """
val age = $kotlinsAge
val s = "Kotlin is ${p(0)}age${p(1)} years old."
// or $kotlinsV1Age since version 1.0
""",
      explanation = """
Any value in Kotlin can be converted to a `String`.

The easy way is to use a value in a string is with the string template syntax. `$dollar{}`

```kotlin
val g: Boolean = true
val gString = "$dollar{g}"  // "true"
```

```kotlin
val position: Int = 69
val nice = "$dollar{position}"  // "69"
```

```kotlin
val what: String = "world"

// "Hello world"
val hello = "Hello $dollar{what}" 
```
""",
      answer = listOf("$dollar{", "}"),
    ),

    // short templates
    CodeQuestionPage(
      title = "Short Template",
      question = """
You can also use templates without `{}`.
""",
      snippet = """
val s1 = "Kotlin is ${p(0)}age years old."
""",
      explanation = """
It will work when the printed value
is followed by a space.

```kotlin
val g = true
val gString = "${dollar}g"  // "true"
```

```kotlin
val position = 69
val nice = "${dollar}position"  // "69"
```

```kotlin
val s = "world"

// "Hello world"
val hello = "Hello ${dollar}s" 
```
""",
      answer = listOf("$"),
      confusion = listOf("+"),
    ),

    // ambiguous syntax with simple dollar
    LessonPage.MultipleChoice(
      title = "Short problems",
      question = """
What will this print?

```kotlin
val what = "duck"
print("I shot 99 ${dollar}whats today.")
```
""",
      explanation = """
Kotlin doesn't know when the variable `what` ends and
the rest of the string `"s today."` continues. It will not guess.
You must use the full notation `$dollar{what}s` to isolate the variable name
from the rest of the string, like this:

```kotlin
val what = "duck"
print("I shot 99 ${dollar}{what}s today.")
```
""",
      choices = listOf(
        "Some error: whats is not declared",
        "I shot 99 whats today.",
        "I shot 99 ducks today."
      ),
      answer = setOf(0)
    ),

    // templates can contain code
    CodeQuestionPage(
      title = "Code in strings",
      question = """
Put the number `2` in `s`.
""",
      snippet = """
val n = 1
val s = "$dollar{ n ${p(0)} n }"
""",
      explanation = """
`$dollar{...}` can contain any Kotlin code.

The code inside the curly brackets `{}` will be executed and 
the result will be transformed to a string.

```kotlin
val s = "$dollar{ n + n }"
val s = "$dollar{ 1 + 1 }"
val s = "$dollar{ 2 }"
val s = "2"
```
""",
      answer = listOf("+"),
      confusion = listOf("==", ">"),
    ),

    // templates can contain code
    CodeQuestionPage(
      title = "Code in strings 2",
      question = """
Put the `true` in `s`.
""",
      snippet = """
val n = 1
val s = "$dollar{ n ${p(0)} n }"
""",
      explanation = """
The code inside the curly brackets `{}` will be executed and 
the result will be transformed to a string.

```kotlin
val s = "$dollar{ n == n }"
val s = "$dollar{ 1 == 1 }"
val s = "$dollar{ true }"
val s = "true"
```
Here is the result for the last proposition:

```kotlin
val n = 1

// false
val s = "$dollar{ n > n }"
val s = "$dollar{ 1 > 1 }"
val s = "$dollar{ false }"
val s = "false"
```
""",
      answer = listOf("+"),
      confusion = listOf("==", ">"),
    ),

    // string concat inside string template
    LessonPage.MultipleChoice(
      title = "1 + 1 =",
      question = """
What will this print?

```kotlin
val n = "1"
print("1 + 1 = $dollar{ n + n }")
```
""",
      explanation = """
The content of the string doesn't matter when using `+` between them.
It will always be considered as a `String` and not as what it could be converted to (`Int`, `Boolean`, ...)

`n` is a string. `+` between strings does a concatenation. Therefore `n + n` is `"1" + "1"`.
Glue them together and you have `"11"`.

```kotlin
print("1 + 1 = $dollar{ n + n }")
// replace with the value
print("1 + 1 = $dollar{ "1" + "1" }")
// concatenate
print("1 + 1 = $dollar{ "11" }")
// put the content inside {} in the string
print("1 + 1 = 11")
```
""",
      choices = listOf("1 + 1 = 11", "1 + 1 = 2"),
      answer = setOf(0),
    ),

    // summary
    InfoPage(
      "Summary",
      """
Strings can be concatenated either with `+` for simple cases 
or with `$dollar{}` for more complex cases.

All the Kotlin data types have a `String` representation.

`$dollar{}` can contain any Kotlin code.
This code will be executed before it's transformed to a `String`.

```kotlin
println(
  "$dollar{ 1 + 2 } deep $dollar{ 3 + 2 } you."
)
```
"""
    )
  )
)
