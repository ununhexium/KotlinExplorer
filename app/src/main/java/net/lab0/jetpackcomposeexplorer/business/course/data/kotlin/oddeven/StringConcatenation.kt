package net.lab0.jetpackcomposeexplorer.business.course.data.kotlin.oddeven

import net.lab0.jetpackcomposeexplorer.business.domain.LessonImpl
import net.lab0.jetpackcomposeexplorer.business.domain.LessonPage.CodeQuestionPage
import net.lab0.jetpackcomposeexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

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
            choices = listOf("+", ".", "~", "$$"),
            answer = listOf("+"),
        ),
        CodeQuestionPage(
            title = "",
            question = """

""",
            snippet = """

""",
            explanation = """

""",
            choices = listOf(),
            answer = listOf(),
        ),
    )
)

