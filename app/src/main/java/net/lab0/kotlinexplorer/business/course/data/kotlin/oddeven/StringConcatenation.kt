package net.lab0.kotlinexplorer.business.course.data.kotlin.oddeven

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage.CodeQuestionPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

// TODO: "A" + "B"
// TODO "$A $B"
object StringConcatenation : LessonImpl(
    id = "kotlin.oddeven.stringconcatenation",
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
            choices = listOf("+", ".", "~", "$$"),
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
Each value in Kotlin can be converted to a `String`.

The easier way to do that the string template syntax. `${'$'}{}`

```kotlin
val g: Boolean = true
val gString = "${'$'}{g}"  // "true"
```

```kotlin
val position: Int = 69
val nice = "${'$'}{position}"  // "69"
```

```kotlin
val s: String = "world"

// "Hello world"
val hello = "Hello ${'$'}{s}" 
```
""",
            answer = listOf("$", "{", "}"),
            choices = listOf("$", "{", "}", "(", ")", "&"),
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

Later we'll see when and why it can fail. But we need to learn more to understand why 🤓.
 
```kotlin
val g = true
val gString = "${'$'}g"  // "true"
```

```kotlin
val position = 69
val nice = "${'$'}position"  // "69"
```

```kotlin
val s = "world"

// "Hello world"
val hello = "Hello ${'$'}s" 
```
""",
            answer = listOf("$"),
            choices = listOf("$", "!", "&"),
        ),
    )
)
