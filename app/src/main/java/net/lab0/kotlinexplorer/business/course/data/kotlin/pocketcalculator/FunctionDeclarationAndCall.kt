package net.lab0.kotlinexplorer.business.course.data.kotlin.pocketcalculator

import net.lab0.kotlinexplorer.business.course.data.kotlin.dollar
import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object FunctionDeclarationAndCall : LessonImpl(
  id = "kotlin.pocketcalculator.functiondeclarationandcall",
  title = "Functions",
  pages = listOf(
    // function declaration
    LessonPage.CodeQuestionPage(
      title = "Declare a function",
      question = """
Declare a function named `sayHello`.

Hint: it works the same way as the `main` function.
""",
      snippet = """
${p(0)} ${p(1)}${p(2)}${p(3)} {
  print("Hello")
}
""",
      explanation = """
This works the same way as the `main()` function, but with a different name.
""",
      answer = listOf("fun", "sayHello", "(", ")"),
      confusion = listOf(),
    ),

    // choose valid function names
LessonPage.MultipleChoice(
      title = "Valid names",
      question = """
Function names should follow the same naming style as variables.

Select the function names that follow the right naming convention.
""",
      explanation = """
First, all these names are *possible*.
That doesn't mean it's a good idea to use them.

The reason why will come later when looking how Kotlin
interacts with other programming languages.

`UpDownLeftRight`: This is `U`pperCamelCase. Function names should be `l`owerCamelCase

`spaced out` contains a space.

`1by1` starts with a number.

`step-by-step` contains a dash `-`.
""",
      choices = listOf("example", "camelCase", "UpDownLeftRight", "spaced out", "1by1", "step-by-step"),
      answer = setOf(),
    ),

    // function call
    LessonPage.CodeQuestionPage(
      title = "Call sayHello",
      question = """
Call the `sayHello` function.
""",
      snippet = """
fun sayHello() {
  print("Hello")
}

// call sayHello
${p(0)}${p(1)}${p(2)}
""",
      explanation = """
This works the same you as calling `print()`.

`sayHello` is the name of the function. It doesn't *call* it.
""",
      answer = listOf("sayHello()"),
      confusion = listOf("sayHello"),
    ),

    // argument (with print)
    LessonPage.CodeQuestionPage(
      title = "Print's argument",
      question = """
Give the `String` argument `"hello"` to the print function.
""",
      snippet = """
print(${p(0)})
""",
      explanation = """
`"hello"` is the *first argument* that was *given* to the function `print`.
""",
      answer = listOf("\"Hello\""),
      confusion = listOf("Hello: String"),
    ),

    // use a parameter
    LessonPage.CodeQuestionPage(
      title = "Hello, you",
      question = """
Personalize the hello message with a name.
""",
      snippet = """
fun sayHello(${p(0)}:String) {
  print("Hello ${dollar}name")
}

sayHello("kitty") // prints Hello kitty
""",
      explanation = """
`name` is the first parameter of the function.

`String` requires the `name` parameter to be a string.

The hello message is concatenated with a string template.

`"kitty"` is the first *argument* in the call to `sayHello()`.

`kitty` is a valid parameters name but here it's printing the value of `name`.

If we use `kitty` as a parameter name, then we have an error on the print line.
""",
      answer = listOf("name"),
      confusion = listOf("kitty"),
    ),

    // parameter type
    LessonPage.MultipleChoice(
      title = "Argument type",
      question = """
What is `print`'s first argument's *type*?

```kotlin
print("1")
```
""",
      explanation = """
`print`'s first argument is `"1"`.

The type of that arguments is `String`. 
""",
      choices = listOf("String", "Int"),
      answer = setOf(0),
    ),

    // parameter declaration
    LessonPage.CodeQuestionPage(
      title = "Parameters",
      question = """
The function `foo` takes 1 parameter: `parameter1`, an `Int`.
""",
      snippet = """
fun compute(${p(0)}: ${p(1)}) {
  // do stuff here
}
""",
      explanation = """
`parameter1` is the name of the first parameter of the function.

`Int` is its type.
""",
      answer = listOf("parameter1", "Int"),
      confusion = listOf(),
    ),

    // argument value
    LessonPage.MultipleChoice(
      title = "Parameter value",
      question = """
What can we say about this code?

```kotlin
print("hello")
```
""",
      explanation = """
The first argument's *value* is `"hello"`.

The first argument's *type* is `String`.

`"hello" is the second argument` where did you see a second argument ..?
""",
      choices = listOf(
        "The first argument's type is String",
        "\"hello\" is the second argument",
      ),
      answer = setOf(0),
    ),

    // summary
    LessonPage.InfoPage(
      title = "Basic functions",
      markdown = """
Functions can be declared with this syntax:

```kotlin
fun someName(
  parameter1: Type1,
  parameter2: Type2,
) {
  // some code
}
```

They can take 0 or more parameters.

A function is called with `()`.

The function call states which `arguments` to send to the function.
 
```kotlin
val argument2 = true
someName("argument1", argument2)
```
"""
    )
  ),
)
