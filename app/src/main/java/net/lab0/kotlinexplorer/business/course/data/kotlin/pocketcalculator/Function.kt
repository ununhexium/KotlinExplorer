package net.lab0.kotlinexplorer.business.course.data.kotlin.pocketcalculator

import net.lab0.kotlinexplorer.business.course.data.kotlin.dollar
import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object Function : LessonImpl(
  id = "kotlin.pocketcalculator.function",
  title = "Functions",
  pages = listOf(

    // function declaration
    LessonPage.CodeQuestionPage(
      title = "Declare a function",
      question = """
Declare a function named `sayHello`
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
      confusion = listOf("{", "}", "function", ""),
    ),

    // function call
    LessonPage.CodeQuestionPage(
      title = "Call sayHello",
      question = """
Call the `sayHello` function.
""",
      snippet = """
fun sayHello(){
  print("Hello")
}

// call sayHello
${p(0)}${p(1)}${p(2)}
""",
      explanation = """
This works the same you as calling `print()`.
""",
      answer = listOf("sayHello", "(", ")"),
      confusion = listOf("call", "fun", "{", "}"),
    ),

    // argument (with print)
    LessonPage.CodeQuestionPage(
      title = "Print's argument",
      question = """
Give the `String` argument `"hello"` to the `print` function.
""",
      snippet = """
print(${p(0)})
""",
      explanation = """
`"hello"` is the first *argument* that was *given* to the function `print`.

Kotlin knows what the type of the argument is, either with type inference or explicit declaration.

The function `print` **takes** 1 **parameter**: a `String`.
""",
      answer = listOf("\"Hello\""),
      confusion = listOf("Hello: String"),
    ),

    // parameter type
    LessonPage.MultipleChoice(
      title = "Parameter type",
      question = """
What is `print`'s first parameter's *type*?

```kotlin
print("1")
```
""",
      explanation = """
`print`'s first argument is `"1"`.
The type inference finds that `"1"` is a `String`. 
""",
      choices = listOf(
        "String", "print can take any type of parameter", "Int"
      ),
      answer = setOf(0),
    ),

    // parameter declaration
    LessonPage.CodeQuestionPage(
      title = "Parameters",
      question = """
The function `foo` takes 1 parameter: `parameter1`, an `Int`.
""",
      snippet = """
fun foo(${p(0)}: ${p(1)}) {
}
""",
      explanation = """
`parameter1` is the name of the first parameter of the function.

`Int` is its type.
""",
      answer = listOf("parameter1", "Int"),
      confusion = listOf(),
    ),

    // use a parameter
    LessonPage.CodeQuestionPage(
      title = "Hello, you",
      question = """
Personalize the hello message with a name.
""",
      snippet = """
fun sayHello(${p(0)}:${p(1)}) {
  print("Hello ${p(2)}${p(3)}")
}

sayHello("kitty") // prints Hello kitty
""",
      explanation = """
`name` is the first parameter of the function.

`String` requires the parameters to be a string.

The hello message is concatenated with a string template.

`"kitty"` is the first argument in the call to `sayHello()`.
""",
      answer = listOf("name", "String", "$", "name"),
      confusion = listOf(),
    ),

    // argument value
    // TODO parameter name and type
    LessonPage.MultipleChoice(
      title = "Parameter value",
      question = """
What is the *value* of the first argument in `print`?

```kotlin
print("hello")
```
""",
      explanation = """
The first argument's *value* is `"hello"`.

The first argument's *type* is `String`.
""",
      choices = listOf("\"hello\"", "String"),
      answer = setOf(0),
    ),

    // function return and return type
    LessonPage.CodeQuestionPage(
      title = "Return hello",
      question = """
Build a string but `return` it instead of printing it.
""",
      snippet = """
fun makeHello(name: String): ${p(0)} {
  ${p(1)} "Hello ${dollar}name"
}

val sentence: ${p(0)} = makeHello("world")
print(sentence) // prints Hello world 
""",
      explanation = """
`String` is the return type of the `makeHello` function.
It is mandatory to match this declaration with the actual return type of the function.
""",
      answer = listOf("String", "return"),
      confusion = listOf("Int"),
    ),

    // what does it return: variable name or content of the variable?
    LessonPage.MultipleChoice(
      title = "What is returned",
      question = """
What will the function `foo` return?
```kotlin_lines
fun makeHello(name: String): String {
  val message:String = "Hello ${dollar}name"
  return message
}

print(makeHello("you")) // what does it print?
```
""",
      explanation = """
`message` is only a variable name.

When returning, the content of `message` is returned.
""",
      choices = listOf("Hello you", "message"),
      answer = setOf(0),
    ),

    // declare function body
    LessonPage.CodeQuestionPage(
      title = "Return 4",
      question = """
Make this `dice` function always return 6.
""",
      snippet = """
fun dice(): ${p(0)} {
  return ${p(1)};
}

print(dice()) // prints 6
""",
      explanation = """
`dice` returns an integer, therefore it must be declared as a function returning an `Int`.
""",
      answer = listOf("Int", "6"),
      confusion = listOf("String", "0"),
    ),

    // parameter type is mandatory
    LessonPage.MultipleChoice(
      title = "Missing parameter",
      question = """
What will this do?

```kotlin
fun surprise(int) {
  print(int)
}

surprise(42)
```
""",
      explanation = """
```kotlin
fun surprise(int: Int) // ...
```

The variable type is *mandatory*.
""",
      choices = listOf(
        "Some error: missing parameter type",
        "Some error: int must be of type String",
        "Prints 42",
        "Prints \"42\""
      ),
      answer = setOf(0),
    ),

    // increment and return int
    LessonPage.CodeQuestionPage(
      title = "Increment",
      question = """
Write a function that adds 1 to a number and returns that value.
""",
      snippet = """
fun increment(${p(0)}:${p(1)}): ${p(2)} {
  ${p(3)} ${p(0)} + 1
}

print(increment(1))  // prints 2
""",
      explanation = """
`number` is a better variable name than `n`, but technically it works too.

`number` is used as a number, its type must be `Int`.

`increment()` returns an integer, its type must be `Int`
""",
      answer = listOf("number", "Int", "Int", "return"),
      confusion = listOf("n", ""),
    ),

    // mismatch return type
    LessonPage.MultipleChoice(
      title = "Mismatch",
      question = """
What will it print?
        
```kotlin
fun doStuff(): String {
  // intense computation
  return 42
}

print(doStuff())
```
""",
      explanation = """

""",
      choices = listOf("Returns Int but String expected", "42 as an integer", "\"42\" as a string"),
      answer = setOf(0),
    ),

    // variable or function?
    LessonPage.MultipleChoice(
      title = "Value fun",
      question = """
What does this print?

```kotlin
val x = 1
fun x():Int { return 2 }

print(x) // prints ..?
```
""",
      explanation = """

""",
      choices = listOf("1", "2", "Ambiguous identifier: x"),
      answer = setOf(0),
    ),

    // declare function with mutiple params
    LessonPage.CodeQuestionPage(
      title = "Addition",
      question = """
Write a function to add 2 integers and return the result.
""",
      snippet = """
fun ${p(0)}(left:${p(1)}, right:${p(1)}): ${p(1)} {
  ${p(2)} left + right
}
""",
      explanation = """
Functions can take as many parameters as necessary.
Each parameter has a type.
Parameters are separated by comas `,`.

For readability and your own sanity, functions with few parameters are preferred.
""",
      answer = listOf("add", "Int", "return"),
      confusion = listOf(""),
    ),

    // call function with multiple args
    LessonPage.CodeQuestionPage(
      title = "Call add",
      question = """
Compute 3 + 3
""",
      snippet = """
fun add(left:Int, right:Int): Int {
  return left + right
}

// prints 6
print(${p(0)}(${p(1)}${p(2)} ${p(3)}))
""",
      explanation = """
When a function needs more than 1 argument, the arguments are separated by comas `,`.
""",
      answer = listOf("add", "3", ",", "3"),
      confusion = listOf("fun add", "3", "3", "+"),
    ),

    // missing arg
    LessonPage.MultipleChoice(
      title = "1 + 1 =",
      question = """
What is the result of this function call?
```kotlin
fun add(left:Int, right:Int): Int {
  return left + right
}

// prints 2
print(add(1 + 1))
```
""",
      explanation = """
`add` takes 2 parameters, `1 + 1` will be evaluated to true and the call becomes:

```kotlin
print(add(2))
```

Therefore the second argument is missing.
""",
      choices = listOf("An error: missing parameter #2", "2"),
      answer = setOf(0),
    ),

    // missing parentheses
    LessonPage.CodeQuestionPage(
      title = "Call me",
      question = """
Which answers will print 10?
""",
      snippet = """
fun add(left:Int, right:Int): Int {
  return left + right
}

// prints 10
print(add ${p(0)} )
""",
      explanation = """
Parentheses are mandatory to call the function.

```kotlin
print(add(2 + 3, 5))  // is the same as
print(add(5, 5)) // prints 10
```
""",
      answer = listOf("(2 + 3, 5)"),
      confusion = listOf("5, 5")
    ),

    // store function call result to value
    LessonPage.MultipleChoice(
      title = "Wait a second",
      question = """
What will this print?

```kotlin
fun add(left:Int, right:Int): Int {
  return left + right
}

val result:Int = add(60, 9)

print(result)
```
""",
      explanation = """
Functions return values and that value can be stored for later use.
""",
      choices = listOf("69", "result", "An error: the result must be used directly."),
      answer = setOf(0),
    ),

    // naming functions
    LessonPage.CodeQuestionPage(
      title = "Best name",
      question = """
Choose the best name for a function computing how many eggs to cook for breakfast.
""",
      snippet = """
fun ${p(0)}(): Int {
  return 42
}
""",
      explanation = """
Just like variables, functions must have clear, descriptive names.
Choose something that is long enough but no more than that.

`eggs` is too short, it doesn't say anything about what the function does, why it does it or how it does it.

`computeTheNumberOfEggsToCookForBreakfast` is probably too long: you can't read the function declaration anymore.
""",
      answer = listOf("computeBreakfastEggsAmount"),
      confusion = listOf(
        "eggs",
        "computeTheNumberOfEggsToCookForBreakfast",
        "compute_the_number_of_eggs_to_cook_for_breakfast"
      ),
    ),

    // summary
    LessonPage.InfoPage(
      title = "Summary",
      markdown = """
Functions are used everywhere when programming.

A function will always have the following structure:

```kotlin
fun name(
  parameter1:Type1,
  parameter2:Type2,
  // maybe more parameters...
): Int {
  return 42
}
```

Here is exactly the same declaration, with extra comments.
This declaration is valida Kotlin code.
The whitespace doesn't matter most of the time, 
so you can take all the space you need to state all the input parameters.

```kotlin
fun // declares a function
    name // function's name
      ( // list 0 or more parameters
  parameter1: // first parameter's name
    Type1, // first parameter's type
  parameter2: // second parameter's name
    Type2, // second parameter's type
) // end of the parameters' list
: Int // Declare the return type
{ // start the function's body block.
  // write the code to execute
  
  // ... more code ...
  
  /*
   * return a value at the end
   * the returned type must match
   * the declared return type
   */
  return 42
} // end of the function's body
```
"""
    )
  ),
)

// TODO: function can't return more than 1 parameter
