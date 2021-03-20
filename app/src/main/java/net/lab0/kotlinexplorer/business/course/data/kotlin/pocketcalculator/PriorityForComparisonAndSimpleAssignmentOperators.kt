package net.lab0.kotlinexplorer.business.course.data.kotlin.pocketcalculator

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks

object PriorityForComparisonAndSimpleAssignmentOperators : LessonImpl(
  id = "kotlin.pocketcalculator.priorityforcomparisonandsimpleassignment",
  title = "Priority for comparison",
  pages = listOf(

    // <
    LessonPage.CodeQuestionPage(
      title = "Comparison",
      question = """
Make this true.

Hint: comparison operators have a lower priority than the other operators you've seen so far.
""",
      snippet = """
1 + 1 ${KotlinCodeWithBlanks.placeholder(0)} 5
""",
      explanation = """
This is the same as

```kotlin
(1 + 1) < 5
2 < 5
true
```
""",
      answer = listOf("<"),
      confusion = listOf(">", "=="),
    ),

    // ==
    LessonPage.CodeQuestionPage(
      title = "Comparison",
      question = """
Make this true.
""",
      snippet = """
1-3*5${KotlinCodeWithBlanks.placeholder(0)}-14
""",
      explanation = """
This is the same as

```kotlin
(1-3*5) == -14
(1 - 3 * 5) == -14
(1 - 15) == -14
1 - 15 == -14
```
""",
      answer = listOf("=="),
      confusion = listOf("!="),
    ),

    // mix == and <
    LessonPage.MultipleChoice(
      title = "Comparison",
      question = """
Make this true.

Equality operators have a lower priority than comparison operators.

```kotlin
1 > 2 == 6 <= 1
```
""",
      explanation = """
Equality operators have a priority lower than comparison operators.

This is the same as

```kotlin
(1 > 2) == (6 <= 1)
(false) == (false)
false == false
true
```
""",
      choices = listOf("true", "false", "F"),
      answer = setOf(0)
    ),

    // prio 10 and 11
    LessonPage.InfoPage(
      title = "Priority #10 and #11",
      """
Comparison operators have priority 10.

Equality operators have priority 11.

So far we have:

1. `++`, `--`
2. Prefix `+`, `-`
3. *Secret*
4. `*`, `/`, `%`
5. `+`, `-`
6. *Secret*
7. *Secret*
8. *Secret*
9. *Secret*
10. `<`, `>`, `<=`, `>=`
11. `==`, `!=`

"""
    ),

    // simple assignment
    LessonPage.CodeQuestionPage(
      title = "Assignment",
      question = """
What does x contain?
""",
      snippet = """
val x: ${KotlinCodeWithBlanks.placeholder(0)} = 1 + 1
""",
      explanation = """
```kotlin
val x: Int = (1 + 1)
val x: Int = 2
```
""",
      answer = listOf("Int"),
      confusion = listOf("Boolean", "2"),
    ),

    // prio 15
    LessonPage.InfoPage(
      title = "Priority #15",
      """
The assignment operator has priority 15.
That is the lowest priority.

1. `++`, `--`
2. Prefix `+`, `-`
3. *Secret*
4. `*`, `/`, `%`
5. `+`, `-`
6. *Secret*
7. *Secret*
8. *Secret*
9. *Secret*
10. `<`, `>`, `<=`, `>=`
11. `==`, `!=`
12. *Secret*
13. *Secret*
14. *Secret*
15. `=`

"""
    ),
  )
)
