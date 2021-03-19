package net.lab0.kotlinexplorer.business.course.data.kotlin.pocketcalculator

import net.lab0.kotlinexplorer.business.course.data.kotlin.dollar
import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object FunctionReturn : LessonImpl(
  id = "kotlin.pocketcalculator.functionreturn",
  title = "Function results",
  pages = listOf(
    // function returned value
    LessonPage.MultipleChoice(
      title = "Scopes",
      question = """
What will this do?

```kotlin
fun compute(a:Int, b:Int) {
  val result = a + b
}

compute(1,1)

print(result)
```
""",
      explanation = """
`result` was declared inside the body of the `compute` function.

It is only accessible inside that function's body.

We say that the value result is *scoped* to the body of the compute function.

It starts existing where it is declared and is not accessible
after the end of the block where it is declared.
""",
      choices = listOf(
        "Some error: no value named 'result'",
        "Print 2: it computes the results, then send the result to print",
      ),
      answer = setOf(0),
    ),

    // function return and return type
    LessonPage.CodeQuestionPage(
      title = "Make a cookie",
      question = """
Build a `String` but `return` it.
""",
      snippet = """
fun makeCookie(): ${p(0)} /*Type*/ {
  ${p(1)} "🍪" // return the cookie
}

val cookie = makeCookie()

// prints 🍪
print(cookie)
""",
      explanation = """
`String` is the return type of the `makeCookie` function.
It is mandatory to match this declaration with the actual return type of the function.

A function can only return *one* element.

To return more than one element, they must be packed together
using various techniques that we will see later.
```
""",
      answer = listOf("String", "return"),
      confusion = listOf("Int", "Int"),
    ),

    // use param and build some return value
    LessonPage.CodeQuestionPage(
      title = "Hello, you",
      question = """
Build a string but `return` it instead of printing it.
""",
      snippet = """
fun makeHello(name: ${p(0)}): ${p(1)} {
  ${p(2)} "Hello ${dollar}name"
}

val greeting:String = makeHello("world")

// prints Hello world 
print(greeting)
""",
      explanation = """
##### Input

The *argument* given to the `makeHello` function is a `String`.

The first *parameter* of the function must be of type `String`.

##### Output

The returned item type is a `String`.

The declared return type of the function must be a `String`.

##### Shorter

The above code can also be written as

```kotlin
fun makeHello(name: String): String {
  return "Hello ${dollar}name"
}

// prints Hello world
print(makeHello("world"))
```
""",
      answer = listOf("String", "String", "return"),
      confusion = listOf("Int", "Int"),
    ),

    // declare function body
    LessonPage.CodeQuestionPage(
      title = "Alea iacta est",
      question = """
Make this `dice` function always return 6.
""",
      snippet = """
fun dice(): ${p(0)} {
  return ${p(1)}
}

print(dice()) // prints 6
""",
      explanation = """
`dice` returns an integer, therefore it must be declared as a function returning an `Int`.
""",
      answer = listOf("Int", "6"),
      confusion = listOf("String", "0"),
    ),

    // what does it return: variable name or content of the variable?
    LessonPage.MultipleChoice(
      title = "What is returned",
      question = """
What will the function `makeHello` return?

```kotlin_lines
fun makeHello(name: String): String {
  val message:String = "Hello ${dollar}name"
  return message
}

// what does it print?
print(makeHello("you"))
```
""",
      explanation = """
`message` is only a variable name.

When returning a value, the *content* of `message` is returned.
""",
      choices = listOf("It prints \"Hello you\"", "It prints message"),
      answer = setOf(0),
    ),

    // parameter type is mandatory
    LessonPage.MultipleChoice(
      title = "Missing parameter",
      question = """
What will this do?

Note: the code coloration was removed.
```
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

The variable type is **mandatory**.
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
`integer` is used as an integer, its type must be `Int`.

`increment()` returns an integer, its type must be `Int`.
""",
      answer = listOf("integer", "Int", "Int", "return"),
      confusion = listOf("String", "String"),
    ),

    // concat and return string
    LessonPage.CodeQuestionPage(
      title = "Increment",
      question = """
Write a function that appends `1` to a string and returns that value.
""",
      snippet = """
fun extend(${p(0)}:${p(1)}): ${p(2)} {
  ${p(3)} ${p(0)} + "1"
}

print(extend("1"))  // prints 11
""",
      explanation = """
`input` is used as a String, its type must be a `String`.

`extend()` returns a string, its type must be a `String`.
""",
      answer = listOf("input", "String", "String", "return"),
      confusion = listOf("Int", "Int"),
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
If a function declares a return type of `String`, it can't return something different.

Here it could either return an integer

```kotlin
fun doStuff(): Int {
  // intense computation
  return 42
}
```

or return a string

```kotlin
fun doStuff(): String {
  // intense computation
  return "42"
}
```
""",
      choices = listOf("Returns Int but String expected", "42 as an integer", "\"42\" as a string"),
      answer = setOf(0),
    ),

    // variable or function?
    LessonPage.MultipleChoice(
      title = "Value or function",
      question = """
What does this print?

```kotlin
val x = 1
fun x():Int { return 2 }

print(x) // prints ..?
```
""",
      explanation = """
It's possible, *but not recommended*, to give the same name to a function and a variable.

The variable can be accessed with just its name, here `x`.

The function must be called like this

```kotlin
val x = 1
fun x():Int { return 2 }

print(x()) // prints 2
```
""",
      choices = listOf("1", "2", "Ambiguous identifier: x"),
      answer = setOf(0),
    ),

    // declare function with multiple params
    LessonPage.CodeQuestionPage(
      title = "Addition",
      question = """
Write a function to add 2 integers and return the result.
""",
      snippet = """
fun ${p(0)}(left:${p(1)}, right:${p(1)}): ${p(1)} {
  ${p(2)} ${p(3)}
}

// print 8
print(add(3,5))
""",
      explanation = """
Functions can take as many parameters as necessary.

Each parameter has a type.

Parameters are separated by comas `,`.

For readability and your own sanity, functions with few parameters are preferred.
""",
      answer = listOf("add", "Int", "return", "left + right"),
      confusion = listOf("left * right"),
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
print(${p(0)}(${p(1)}${p(2)}${p(3)}))
""",
      explanation = """
When a function needs more than 1 argument, the arguments are separated by comas `,`.
""",
      answer = listOf("add", "3", ", ", "3"),
      confusion = listOf("fun add", "+"),
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

`// prints 2`: don't trust comments, maybe they were correct before, 
but after (accidental) code changes, they will tell what was there *before*.

Do understand what the code *does*, trust the code only.

Comments are there to guide you, they're not an absolute truth.
""",
      choices = listOf("An error: missing parameter #2", "It prints 2"),
      answer = setOf(0),
    ),

    // missing parentheses
    LessonPage.CodeQuestionPage(
      title = "Call me",
      question = """
Which answer will print 10?
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
print(add(5, 5))  // prints 10
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
##### `69`

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

Function names have the same restrictions as variable names.

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

    // space in function
LessonPage.MultipleChoice(
      title = "Spaces",
      question = """
What will this do?
""",
      explanation = """
fun say Hello() {
  print("Hello")
}

say Hello()
""",
      choices = listOf("It prints Hello", "Somme error: there is a space in the function's name"),
      answer = setOf(),
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
This declaration is valid Kotlin code.
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
  
  val prisoner = "The Joker"
  
  /*
   * return a value at the end
   * the returned type must match
   * the declared return type
   */
  return 42
} // end of the function's body

// the "prisoner" value is no more accessible here
```
"""
    )
  ),
)
