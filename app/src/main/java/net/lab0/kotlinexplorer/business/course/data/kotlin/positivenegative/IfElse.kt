package net.lab0.kotlinexplorer.business.course.data.kotlin.positivenegative

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage.CodeQuestionPage
import net.lab0.kotlinexplorer.business.domain.LessonPage.InfoPage
import net.lab0.kotlinexplorer.business.domain.LessonPage.MultipleChoice
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object IfElse : LessonImpl(
  id = "kotlin.positivenegative.ifelse",
  title = "If Else",
  pages = listOf(

    // if(true)
    CodeQuestionPage(
      title = "If it's true",
      question = """
Print `I'm the boss`
""",
      snippet = """
if( ${p(0)} ) {
    print("I'm the boss")
}
""",
      explanation = """
The value in the `if`'s condition `()` must be a `Boolean`.

If that boolean is true, then the code in the if's block `{ ... }` is executed.

`"true"` is a string

`1` is an integer.
""",
      answer = listOf("true"),
      confusion = listOf("\"true\"", "1"),
    ),

    // if(false)
    CodeQuestionPage(
      title = "If it's false",
      question = """
Don't print `I like games`
""",
      snippet = """
if( ${p(0)} ) {
    print("I like games")
}
""",
      explanation = """
The value in the `if`'s condition `()` must be a `Boolean`.

If that boolean is true, then the code in the if's block `{ ... }` is executed.
""",
      answer = listOf("false"),
      confusion = listOf("\"false\"", "0"),
    ),

    // if (false expression)
    CodeQuestionPage(
      title = "Denied",
      question = """
Print `line 4` but not `line 2`.
""",
      snippet = """
if ( 1 ${p(0)} 2 ) {
    print("line 2")
}
print("line 4")
""",
      explanation = """
The comparison operators return a `Boolean` value.

That value can be used in the `if`.

`line 2` must not be displayed, so the conditional must return false.

```kotlin
1 != 2   // true
1 < 2    // true
1 == 2   // false
```

Only the third option will avoid executing the `if` block.
""",
      answer = listOf("=="),
      confusion = listOf("!=", "<"),
    ),

    // if true expression skip else
    CodeQuestionPage(
      title = "Ignore that",
      question = """
Print `line 2` but not `line 4`
""",
      snippet = """
if ( 1 ${p(0)} 2 ) {
    print("line 2")
} else {
    print("line 4")
}
""",
      explanation = """
When the `if`'s condition is true, only the `if` block is executed.

The part in the `else` block is executed only when the `if`'s condition is false.

This is called `branching`: selecting between 2 or more possible tasks.

```kotlin
if ( /* some boolean */ ) {
    // branch when if is true
    print("line 2")
} else {
    // branch when if is false
    print("line 4")
}
```
""",
      answer = listOf("<"),
      confusion = listOf("==", ">="),
    ),

    // is false exec else block
    CodeQuestionPage(
      title = "Otherwise",
      question = """
Print `line 4` but not `line 2`.
""",
      snippet = """
if ( ${p(0)} ) {
    print("line 2")
} else {
    print("line 4")
}
""",
      explanation = """
When the `if`'s condition is false, only the part in the `else` is executed.
""",
      answer = listOf("false"),
      confusion = listOf("true"),
    ),

    // not expression
    CodeQuestionPage(
      title = "No no no",
      question = """
Don't lie.
""",
      snippet = """
if ( ${p(0)} true ) {
    print("I'm lying")       
} else {
    print("Good boi :3")
}
""",
      explanation = """
##### `!`

negates a boolean.

```kotlin
!true   // false
!false  // true
```

These are the same

```kotlin
if ( ! true )
if ( !true )
if (!true)
if ( false )
```

##### `!!`

Exists, but is not for negation.

""",
      answer = listOf("!"),
      confusion = listOf("not", "!!", "~"),
    ),

    // short if else no bracket
    MultipleChoice(
      title = "Short if else",
      question = """
What will be printed?

```kotlin
if (true) print("1") else print("2")
```
""",
      explanation = """
When there is only **one** statement in the if or in the else block, the curly brackets `{}` can be omitted.

The code above is the same as
 
```kotlin
if(true){ print("1") }else{ print("2") }
```

And the same as
```kotlin
if (true) {
    print("1")
} else { 
    print("2")
}
```

or

```kotlin
if (true) {
    print("1")
} else print("2")
```

Or any other variation.
""",
      choices = listOf("1", "2", "An error like 'Missing {}'"),
      answer = setOf(0),
    ),

    // if else expression
    MultipleChoice(
      title = "Return type",
      question = """
What is the type of `x`?
```kotlin
val x = if (true) 1 else 2
```
""",
      explanation = """
`if else` is an expression. It means that it returns *something*.

What does it return? The last statement of either the *true* or the *false* branch.

Here both branches are `Int`egers, therefore it will return an integer.

In this case, it returns `1`.
""",
      choices = listOf("Integer", "String", "Something else", "It doesn't work"),
      answer = setOf(0),
    ),

    // if else expression return type
    MultipleChoice(
      title = "Return type?",
      question = """
What is the type of `x`?
```kotlin
val b: Boolean = true
// Hint: b could also be set to false
val x = if (b) 1
```
""",
      explanation = """
Here there is only one branch. This will result in an error.

To use `if else` as an expression, there *must always* be some returned value.
""",
      choices = listOf("It doesn't work", "Integer", "Something else"),
      answer = setOf(0),
    ),

    // if else chains
    CodeQuestionPage(
      title = "Chains",
      question = """
The following code must tell if a number is positive, negative or equal to `0`.
""",
      snippet = """
/* number can be any positive 
   or negative value. */
val number = 1
if (number ${p(0)} ${p(1)}) {
    print("positive") // 1
} else if (number ${p(2)} ${p(3)}) {
    print("negative") // 2
} else {
    print("zero!")    // 3
}
// end
""",
      explanation = """
`if else` statements can be chained.

The first `if` will be evaluated. If it's `true`, it executes only the block `1` and continues at the `end`.
If it's `false` it goes into the second `if`, evaluates it.
If the second if is true, it executes to `2` and then goes to the `end`.
Finally, if none of the `if`s in true, it executes `3` and goes to the end.

ℹ️ You will see later that there is another more compact syntax to express the same logic.

The code above is the same as

```kotlin
val number = 1
if (number > 0) {
    print("positive") // 1
} else {
    if (number < 0) {
        print("negative") // 2
    } else {
        print("zero!")    // 3
    }
}
```
""",
      answer = listOf(">", "0", "<", "0"),
      confusion = listOf("<=", ">="),
    ),

    // summary
    InfoPage(
      title = "Summary",
      """
#### Syntax

```kotlin
if (/*boolean*/) { 
  /*this*/
} else { 
  /*that*/
}
```

##### Shortened

If there is only 1 statement the `{}` can be removed. 
In the beginning, prefer always using `{}` as it will help you find mistakes.

```kotlin
if (/*boolean*/) print("A") else print("B")
```

##### `else` is optional

If there is no statement in the else branch, the `else` branch can be removed.

```kotlin
val happy = true
if (happy) print(":)")
```

#### Expression

`if else` is an *expression* and returns a *value*.
To use it as an expression, you must declare both a `true` and a `false` branch.

```kotlin
val hi = if (false) "bye" else "hi"
```
"""
    ),
  )
)
