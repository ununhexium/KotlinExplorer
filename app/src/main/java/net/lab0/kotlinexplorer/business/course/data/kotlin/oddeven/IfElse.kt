package net.lab0.kotlinexplorer.business.course.data.kotlin.oddeven

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object IfElse : LessonImpl(
    id = "kotlin.oddeven.ifelse",
    title = "If this Else that",
    pages = listOf(
        LessonPage.CodeQuestionPage(
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
""",
            answer = listOf("true"),
            confusion = listOf(""""true"""", "1"),
        ),
        LessonPage.CodeQuestionPage(
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
        LessonPage.CodeQuestionPage(
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
""",
            answer = listOf("<"),
            confusion = listOf("==", ">="),
        ),
        LessonPage.CodeQuestionPage(
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
        LessonPage.CodeQuestionPage(
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
#####`!`

negates a boolean.

```kotlin
!true   // false
!false  // true
```

##### `!!`

Exists, but is not for negation.

##### `~`

Is nothing special in Kotlin.

##### `not`

Is nothing special in Kotlin.
""",
            answer = listOf("!"),
            confusion = listOf("not", "!!", "~"),
        ),
    )
)
