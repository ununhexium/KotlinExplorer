package net.lab0.kotlinexplorer.business.course.data.kotlin.pocketcalculator

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object ImportAndPackge : LessonImpl(
  id = "kotlin.pocketcalculator.importandpackage",
  title = "Imports and Packages",
  pages = listOf(
    // absolute names
    LessonPage.CodeQuestionPage(
      title = "Absolute name",
      question = """
There is always a *unique*, *absolute* way to use an element declared in Kotlin.

Call `println` using its absolute name.
""",
      snippet = """
${p(0)}println()
""",
      explanation = """
`kotlin.io` is the package where the `println` function is declared.

`println` is the name of the `println` function you already used.
""",
      answer = listOf("kotlin.io."),
      confusion = listOf(),
    ),

    // use absolute names
    LessonPage.CodeQuestionPage(
      title = "Use absolute names",
      question = """
Use the absolute names of the elements you already know.
""",
      snippet = """
val message: 
""",
      explanation = """
val message: ${p(0)} = "hi!"
val integer: ${p(1)} = 1
val floatingPoint: ${p(2)} = 1f

${p(3)}(true)
""",
      answer = listOf("kotlin.String", "kotlin.Int", "kotlin.Float", "kotlin.io.print"),
      confusion = listOf(),
    ),

    // package
    LessonPage.CodeQuestionPage(
      title = "Declare a package",
      question = """
Declare a package name.
""",
      snippet = """
${p(0)} net.lab0.kotlinexplorer
""",
      explanation = """
`net.lab0.kotlinexplorer` is the package in which this application
(written in Kotlin 😉) is declared.

Packages help organize code. This application is more than `20.000` 
(twenty thousand) lines of code, without counting all the code from 
the various imports it relies on.

It's almost impossible to manage that in a single file, 
so it's split in several files, which are then grouped into packages.

This helps in 2 ways:
* avoid conflicts, each developer or company can have their own "reserved" space.
* find elements, elements that are working together can be grouped.

Packages are lower case, and usually follow the reverse order of the company's domain name.

For instance, `google.com` will use `com.google` as a package name.
""",
      answer = listOf("package"),
      confusion = listOf(),
    ),

    // import
    LessonPage.CodeQuestionPage(
      title = "Import",
      question = """
`import` the `min` (minimum) function.
""",
      snippet = """
${p(0)} kotlin.math.min
""",
      explanation = """
`import` tells to Kotlin that you want to use the `min` function in this code.

`import` is valid for the whole file where this code is written, from top to bottom.

`import` declarations are always placed second next to the top of the file.

In this example, the `min` function is declared in the `kotlin.math` package.
""",
      answer = listOf("import"),
      confusion = listOf(),
    ),

    // optional imports
    LessonPage.CodeQuestionPage(
      title = "Use println",
      question = """
Print `Either`
""",
      snippet = """
${p(0)}("Either")
""",
      explanation = """
Both are correct.

So why does it work without an import?

Kotlin automatically imports elements that people may use the most, 
like the basic types, basic functions, ...

This is a fixed list and will not change.
It contains the globally most used elements like `Int`, `String`, `print` any others.
""",
      answer = listOf("print", "kotlin.io.print"),
      confusion = listOf(),
      validator = { true }
    ),

    // why no need for some imports
    LessonPage.MultipleChoice(
      title = "Why no import until now?",
      question = """
Why didn't we need to import `Int`, `String`, ... until now?
""",
      explanation = """
Kotlin automatically imports packages from a set of predefined packages, it's a fixed list
of the elements that are the most likely to be used by developers in general.

It's not an adaptive list that imports what you may use the most.

There is no magic about these imports, Kotlin will not guess what to use if you don't know yourself.

They were never hidden, the previous code samples are all valid Kotlin code.
""",
      choices = listOf(
        "Kotlin automatically imports a preset list of elements",
        "Kotlin automatically imports elements that I use the most",
        "Kotlin finds the best import automatically",
        "We always needed to import them but they were hidden for simplicity"
      ),
      answer = setOf(0),
    ),

    // TODO: why packages? organize, reserved space

  // TODO import as and name conflicts resolution
  )
)