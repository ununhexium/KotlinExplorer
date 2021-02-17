package net.lab0.kotlinexplorer.business.course.data.kotlin.positivenegative

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage.CodeQuestionPage
import net.lab0.kotlinexplorer.business.domain.LessonPage.InfoPage
import net.lab0.kotlinexplorer.business.domain.LessonPage.MultipleChoice
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object Comparators : LessonImpl(
    id = "kotlin.positivenegative.comparators",
    title = "Comparators",
    pages = listOf(
        CodeQuestionPage(
            title = "Equality",
            question = """
Make the following comparison true
""",
            snippet = """
true ${p(0)} true
""",
            explanation = """
##### `==`

The comparison *operator*.

##### `=` 

The assignment operator.

```kotlin
val i = 1
```

##### `===`

Exists but does a different kind of comparison. More about that in another lesson.

##### `equals`

```kotlin
1 equals 1  // error
1.equals(1) // valid, true
1.equals(2) // valid, false
1 == 2      // better. More readable
```
""",
            answer = listOf("=="),
            confusion = listOf("=", "===", "equals"),
        ),
        // TODO: string lexicographical comparison: "a" < "b"
        CodeQuestionPage(
            title = "Not so equal",
            question = """
Make the following comparison true.
""",
            snippet = """
"long" ${p(0)} "short"
""",
            explanation = """
##### `!=` 

Inequality operator

##### `!==`

Exists but does a different kind of comparison. It works in pair with `===`. More about that in another lesson.

##### `<>`

Is not a Kotlin operator.

""",
            answer = listOf("!="),
            confusion = listOf("<>", "!=="),
        ),
        CodeQuestionPage(
            title = "Bigger",
            question = """
Make the following comparison true.
""",
            snippet = """
100 ${p(0)} 99
""",
            explanation = """
##### `x < y`

`x` is smaller than `y`

##### `x > y`
 
`x` is greater than `y`

""",
            answer = listOf(">"),
            confusion = listOf("<"),
        ),
        CodeQuestionPage(
            title = "Smaller",
            question = """
Make the following comparison true.
""",
            snippet = """
0 ${p(0)} 1
""",
            explanation = """
##### `x < y`

`x` is smaller than `y`

##### `x > y`
 
`x` is greater than `y`

""",
            answer = listOf("<"),
            confusion = listOf(">"),
        ),
        CodeQuestionPage(
            title = "Greater or equal",
            question = """
Make the following comparisons true.
""",
            snippet = """
1 ${p(0)} 0
1 ${p(0)} 1
""",
            explanation = """
##### `x <= y`
 
`x` is smaller than `y` or equal to `y`

##### `x >= y`
 
`x` is greater than `y` or equal to `y`
""",
            answer = listOf(">="),
            confusion = listOf("<="),
        ),
        CodeQuestionPage(
            title = "Smaller or equal",
            question = """
Make the following comparisons true.
""",
            snippet = """
0 ${p(0)} 0
0 ${p(0)} 1
""",
            explanation = """
##### `x <= y`
 
`x` is smaller than `y` or equal to `y`

##### `x >= y`
 
`x` is greater than `y` or equal to `y`
""",
            answer = listOf("<="),
            confusion = listOf(">="),
        ),
        CodeQuestionPage(
            title = "Result type",
            question = """
A comparison returns a value. Can you guess its type?

Parentheses show operations priority.

1. compare
2. put the comparison's result into the value 'isTrue'
""",
            snippet = """
val isTrue: ${p(0)} = (1 == 1) 
""",
            explanation = """
All comparisons return a boolean, either `true`, like here, or `false` like in

```kotlin
val isFalse: Boolean = (1 == 2) 
```

Because `==` is an expression (outputs a value), its result can be assigned.

```kotlin
true == true == true == true       // true
```
```kotlin
//   true      ==       true
(true == true) == (false == false) // true
```
""",
            answer = listOf("Boolean"),
            confusion = listOf("Int", "String", "Undefined"),
        ),
        MultipleChoice(
            title = "Mhhh \uD83E\uDD14",
            question = """
Can you guess the result of the following expression?

```kotlin
true > false
```
""",
            explanation = """
You could also use `>=` and `<=`. But only on the first of April 😉.

...

Please don't do that. Only use `==` and `!=` to compare booleans. 🙏
""",
            choices = listOf("true", "false", "Not valid", "Undefined"),
            answer = setOf(0),
        ),
        MultipleChoice(
            title = "Different data types",
            question = """
Can you guess the result of the following expression?

```kotlin
"a" != 1
```
""",
            explanation = """
Comparisons must be between elements of the same type.

```kotlin
"a" != "1" // true
 1  !=  1  // false
"a" !=  1  // error
```
""",
            choices = listOf("Some error?", "true", "false"),
            answer = setOf(0),
        ),
        InfoPage(
            title = "Summary",
            """
Comparison operators can be used on comparable data types. So far you know three data types.

1. `Int`eger
2. `String`
3. `Boolean`

Elements on both sides of the operator must be of the same data type.

##### `Boolean`s

Compared with `!=` and `==`.

(Don't use `<`, `>`, ... I'm watching you 🥺 🔫😠)

##### `Int`egers
 
Compared with `!=`, `==`, `<`, `>`, `<=`, `>=`

##### `String`s
Compared with `!=`, `==`.
Could be compared with `<`, `>`, `<=`, `>=`, more about that in another lesson.
"""
        )
    )
)
