package net.lab0.kotlinexplorer.business.course.data.kotlin.moreorless

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object Import : LessonImpl(
  id = "kotlin.moreorless.import",
  title = "Import",
  pages = listOf(
    LessonPage.CodeQuestionPage(
      title = "Import Int",
      question = """
${p(0)} 
""",
      snippet = """

""",
      explanation = """

""",
      answer = listOf(),
      confusion = listOf(),
    ),
    LessonPage.CodeQuestionPage(
      title = "Import",
      question = """
Import the random integer generator.
""",
      snippet = """
${p(0)} import kotlin.random.Random.Default.nextInt
""",
      explanation = """
`import` imports something from some package.

All the elements that you use in Kotlin are located in some package.

`print`, `Int`, `String`, ... are all located in packages:

```kotlin
import system.io.print
print("hello")

import kotlin.Int
val i:Int = 1

import kotlin.String
val s:String = "hello"

import kotlin.Boolean
val b:Boolean = true
```

So why is it possible to use them without an import?

Because the most often used packages are imported by default.

Here we explicitly imported the `nextInt` function.
""",
      answer = listOf(),
      confusion = listOf(),
    ),
  ),
  dependencies = listOf()
)
