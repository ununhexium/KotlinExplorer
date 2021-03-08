package net.lab0.kotlinexplorer.business.course.data.kotlin.positivenegative

import net.lab0.kotlinexplorer.business.course.data.kotlin.dollar
import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.LessonPage.CodeQuestionPage
import net.lab0.kotlinexplorer.business.domain.LessonPage.InfoPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object StringConcatenation : LessonImpl(
  id = "kotlin.positivenegative.stringconcatenation",
  title = "String Concatenation",
  pages = listOf(
    CodeQuestionPage(
      title = "❤ + ❤",
      question = """
Concatenate these 2 hearts together.
""",
      snippet = """
val twoHearts = "❤" ${p(0)} "❤"
""",
      explanation = """
Between 2 strings, the `+` operator concatenates the strings together.

```kotlin
"❤" + "❤" == "❤❤"  // true
```
""",
      answer = listOf("+"),
      confusion = listOf(".", "~", "$$"),
    ),
    CodeQuestionPage(
      title = "String Templates",
      question = """
Put Kotlin's age in the middle of this `String`.
""",
      snippet = """
val age = $kotlinsAge
val s = "Kotlin is ${p(0)}${p(1)}age${p(2)} years old."
// or $kotlinsV1Age since version 1.0
""",
      explanation = """
Any value in Kotlin can be converted to a `String`.

The easy way is to use the string template syntax. `$dollar{}`

```kotlin
val g: Boolean = true
val gString = "$dollar{g}"  // "true"
```

```kotlin
val position: Int = 69
val nice = "$dollar{position}"  // "69"
```

```kotlin
val s: String = "world"

// "Hello world"
val hello = "Hello $dollar{s}" 
```
""",
      answer = listOf("$", "{", "}"),
      confusion = listOf("(", ")", "&"),
    ),
    CodeQuestionPage(
      title = "Short Template",
      question = """
You can also use templates without `{}`.
""",
      snippet = """
val s1 = "Kotlin is ${p(0)}age years old."
""",
      explanation = """
For the moment it will work in most cases.
 
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
      confusion = listOf("!", "&"),
    ),
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
from the rest of the string.

```kotlin
val what = "duck"
print("I shot 99 ${dollar}{what}s today.")
```
""",
      choices = listOf("Some error", "I shot 99 whats today.", "I shot 99 ducks today."),
      answer = setOf(0)
    ),
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
""",
      answer = listOf("+"),
      confusion = listOf("==", ">", "4"),
    ),
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

`n` is a string. `+` between strings does a concatenation. Therefore `n + n` is `"1" + "1"`.
Glue them together and you have `"11"`.
""",
      choices = listOf("1 + 1 = 11", "1 + 1 = 2"),
      answer = setOf(0),
    ),
    InfoPage(
      "Summary",
      """
Strings can be concatenated either with `+` for simple cases or with `$dollar{}` for more complex cases.

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
