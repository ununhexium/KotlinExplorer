package net.lab0.jetpackcomposeexplorer.business.course.data.kotlin.oddeven

import net.lab0.jetpackcomposeexplorer.business.domain.LessonImpl
import net.lab0.jetpackcomposeexplorer.business.domain.LessonPage
import net.lab0.jetpackcomposeexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object DataType : LessonImpl(
    id = "kotlin.oddeven.datatype",
    title = "Data type",
    pages = listOf(
        LessonPage.CodeQuestionPage(
            title = "Tell what it is",
            question = """
Tell the type of this value.
""",
            snippet = """
val number: ${p(0)} = 42
""",
            explanation = """
`Int` (integer) indicates the data type of the value `number`.
""",
            choices = listOf("Int", "String", "Boolean"),
            answer = listOf("Int"),
        ),
        LessonPage.CodeQuestionPage(
            title = "It's a string",
            question = """
Tell the type of this value.
""",
            snippet = """
val alphabet: ${p(0)} = "abcdef..."
""",
            explanation = """
`String` indicates that alphabet is a string.
""",
            choices = listOf("Int", "String", "Boolean"),
            answer = listOf("String"),
        ),
        LessonPage.CodeQuestionPage(
            title = "It's the truth",
            question = """
Tell the type of this value.
""",
            snippet = """
val theSkyIsBlue: ${p(0)} = true // mostly...
""",
            explanation = """
`theSkyIsBlue` must now be a boolean.
""",
            choices = listOf("Int", "String", "Boolean"),
            answer = listOf("Boolean"),
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
            choices = listOf("String", "It doesn't have a type", "Anything", "Nothing"),
            answer = setOf(0)
        ),
        // TODO: summary page
        LessonPage.InfoPage(
            title = "Type inference",
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
        // TODO: multiple choice question: tell the type (like type inference) like to compiler would do
    )
)
