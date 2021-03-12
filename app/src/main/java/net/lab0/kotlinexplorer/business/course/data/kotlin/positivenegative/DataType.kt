package net.lab0.kotlinexplorer.business.course.data.kotlin.positivenegative

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object DataType : LessonImpl(
  id = "kotlin.positivenegative.datatype",
  title = "Data type",
  pages = listOf(

    // tell int type
    LessonPage.CodeQuestionPage(
      title = "Tell what it is",
      question = """
Tell the type of this value.
""",
      snippet = """
val number: ${p(0)} = 42
""",
      explanation = """
`Int` indicates that the data type of the value `number` must be an `Int`eger..
""",
      answer = listOf("Int"),
      confusion = listOf("String", "Boolean"),
    ),

    // tell string type
    LessonPage.CodeQuestionPage(
      title = "It's a string",
      question = """
Tell the type of this value.
""",
      snippet = """
val alphabet: ${p(0)} = "abcdef..."
""",
      explanation = """
`String` indicates that alphabet must be a string.
""",
      answer = listOf("String"),
      confusion = listOf("Int", "Boolean"),
    ),

    // tell boolean type
    LessonPage.CodeQuestionPage(
      title = "It's the truth",
      question = """
Tell the type of this value.
""",
      snippet = """
val theSkyIsBlue: ${p(0)} = true // mostly...
""",
      explanation = """
`theSkyIsBlue` must be a boolean.
""",
      answer = listOf("Boolean"),
      confusion = listOf("Int", "String"),
    ),

    // negative int
    LessonPage.CodeQuestionPage(
      title = "Negative",
      question = """
Declare a negative integer
""",
      snippet = """
val negative = ${p(0)}
""",
      explanation = """
This can also be written

```kotlin
val negative=-12

val negative = - 12

val negative =    -   12

val negative
  =
    -
      12
```

The amount of spaces doesn't matter.

"-12" is a `String`
""",
      answer = listOf("-12"),
      confusion = listOf("\"-12\""),
    ),

    LessonPage.MultipleChoice(
      title = "Type inference",
      question = """
What is the type of `surprise`?

```kotlin
val surprise = "Mhhh 🤔"
```
""",
      explanation = """
When no type is specified, the type is inferred from the left part.

The type of `surprise` will be the type that is the most specific.

Here, `"Mhhh 🤔"` is a string. Therefore the type of `surprise` will also be a string.
""",
      answer = setOf(0),
      choices = listOf("String", "It doesn't have a type", "Anything", "Nothing")
    ),
    LessonPage.InfoPage(
      title = "Summary",
      """
When no indication is given, Kotlin will infer the type of the value.

```kotlin
val word = "hi"
```

Is exactly the same as
 
 ```kotlin
val word:String = "hi"
```

The same goes for the other data types.

```kotlin
val number = 42
val number:Int = 42

val yes = true
val yes:Boolean = true
```
"""
    )
  )
)
