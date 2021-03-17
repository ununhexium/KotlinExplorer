package net.lab0.kotlinexplorer.business.course.data.kotlin.positivenegative

import net.lab0.kotlinexplorer.business.course.data.kotlin.dollar
import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object Value : LessonImpl(
  id = "kotlin.positivenegative.value",
  title = "Value",
  pages = listOf(

    // declare value
    LessonPage.CodeQuestionPage(
      title = "Memory",
      question = "Declare a value named number",
      snippet = """
${p(0)} number = 1
""",
      explanation = """
`val` declares a value.

The name of this value is `number`.

`let` is not a keyword in Kotlin.

A blank will not work. It's a syntax error.
""",
      answer = listOf("val"),
      confusion = listOf("let", "")
    ),

    // string value
    LessonPage.CodeQuestionPage(
      title = "Hello value",
      question = """
Assign the string `hello` to the variable `word`.
""",
      snippet = """
val ${p(0)} = ${p(1)}
""",
      explanation = """
`"word"` is a string, not a value name.

`hello` is a value name, not a string.
""",
      answer = listOf("word", """"hello""""),
      confusion = listOf(""""word"""", "hello")
    ),

    // good value names
    LessonPage.CodeQuestionPage(
      title = "Good names",
      question = """
Let's say you want to give a name to the number of cats in a house.

Choose the best value name.
""",
      snippet = """
val ${p(0)} = 3
""",
      explanation = """
`n` is not a good name because it's too short.
When the program is longer, it's harder to remember what single letter values mean.
""",
      answer = listOf("numberOfCatsInTheHouse"),
      confusion = listOf("n", "nCatHouse")
    ),

    // camel case
    LessonPage.CodeQuestionPage(
      title = "Camel case",
      question = """
Let's say you want to give a name to the number of mice in a house.

Choose the best value name.
""",
      snippet = """
val ${p(0)} = 20
""",
      explanation = """
`number_of_mice_in_the_house` is snake case. This is technically a valid name, but Kotlin uses camel case.

Following the naming conventions will make it easier to read code later.
Looking at the naming will tell its nature (function, value, keyword, ...).
""",
      answer = listOf("numberOfMiceInTheHouse"),
      confusion = listOf("number_of_mice_in_the_house")
    ),

    // print variable
    LessonPage.MultipleChoice(
      title = "Print time",
      question = """
What is printed?

```kotlin
val date = "today"
print(date)
```
""",
      explanation = """
`date` is a variable, when calling `print(someVariable)`,
the content of the variable will be printed.
""",
      choices = listOf("today", "date"),
      answer = setOf(0),
    ),

    // print string
    LessonPage.MultipleChoice(
      title = "Print time",
      question = """
What is printed?

```kotlin
val time = "$currentTimeString"
print("time")
```
""",
      explanation = """
`"time"` is a string, print will just print it.

The `time` variable doesn't do anything here.
""",
      choices = listOf("time", currentTimeString),
      answer = setOf(0),
    ),

    // reassign values
    LessonPage.MultipleChoice(
      title = "Copy paste",
      question = """
What is printed?

```kotlin
val original = "Hi! :)"
val copy = original

print(copy)
```
""",
      explanation = """
```kotlin
val original = "Hi! :)"
```

`original` contains `"Hi! :)"`.
 
`copy` contains a reference to the same `"Hi! :)"` as `original`.
 
`original` is a reference to the string `"Hi! :)"`.
`copy` is also a reference to the string `"Hi! :)"`.
 
They can both be used to access the exact same data.
 
`"Hi! :)"` is never duplicated, never copied. It exists only once.

This implies that any change to `"Hi! :)"` will affect both `original` and `copy`.

This mechanism is called **copy by reference**. More about that in later chapters.
""",
      choices = listOf("Hi! :)", "Goodbye :(", "original", "copy"),
      answer = setOf(0),
    ),

    // naming restrictions: standards
    LessonPage.CodeQuestionPage(
      title = "Value name",
      question = """
Choose a valid name.
""",
      snippet = """
val ${p(0)} = "English"
""",
      explanation = """
A variable may only contain letters, numbers and underscores `_`.

A variable can start with an underscore `_`.

A variable can't start with a number.

`language of Shakespeare` is invalid because it contains spaces.

`1_word` is invalid because it starts with a number.
""",
      answer = listOf("_english"),
      confusion = listOf("language of Shakespeare", "1_word"),
    ),

    // naming restrictions
    LessonPage.CodeQuestionPage(
      title = "Baguette fromage \uD83E\uDD56 \uD83E\uDDC0",
      question = """
Choose a valid name.
""",
      snippet = """
val ${p(0)} = "Hello"
""",
      explanation = """
Kotlin allows the use of letters that are not in the english alphabet.

This is a bad practice.

Always stick to english because that's the common part that everybody know how to use.
""",
      answer = listOf("english"),
      confusion = listOf("language of Shakespeare", "1word"),
    ),

    // unicode support
    LessonPage.CodeQuestionPage(
      title = "Valid names",
      question = """
Choose valid names.
""",
      snippet = """
// french
val ${p(0)} = "Bonjour"

// japanese
val ${p(1)} = "今日わ"
""",
      explanation = """
Even though Kotlin supports a large range of characters for its variables names,
you should only use the english alphabet because that's the
smallest common denominator between software developers.

You would not want to learn how to use a Thai, Chinese or Korean keyboard, right?
""",
      answer = listOf("français", "日本語"),
      confusion = listOf("some french", "ja.pa.ne.se"),
    ),

    // invalid names
    LessonPage.MultipleChoice(
      title = "Bad names",
      question = """
Which of the following variables are invalid?

```kotlin
val ไทย = "Thai"
val _1 = 12345
val 한글 = "Hangul" // korean
val __ = ""
val first_name = "John"
val middle.name = "Fitzgerald"
val lastName_ = "Kenedy"
```
""",
      explanation = """
`__` is reserved. The same is true for any number of `_` used together.

`middle.name` contains a point.
""",
      choices = listOf("__", "middle.name", "ไทย", "_1", "한글", "first_name", "lastName_"),
      answer = setOf(0, 1),
    ),

    // bad names
    LessonPage.MultipleChoice(
      title = "Bad names",
      question = """
Which of the following variables are bad?

Bad = can be technically valid, but should not be used.

```kotlin
val ไทย = "Thai"
val _1 = 12345
val 한글 = "Hangul" // korean
val first_name = "John"
val lastName_ = "Kenedy"
```
""",
      explanation = """
`ไทย` is not english.

`_1` doesn't describe what it contains.

`한글` is not english

`first_name` uses snake case (`_` instead of spaces).
Kotlin use lower camelCase for variables.
Lower camel case starts with a lower case letter 
capitalize letter that are between words.

`thisIsAnExampleOfCamelCase`
 
`lastName_` is lower camel case with has a trailing `_`
""",
      choices = listOf("ไทย", "_1", "한글", "first_name", "lastName_"),
      answer = setOf(0, 1, 2, 3, 4),
    ),

    LessonPage.InfoPage(
      "No repetition",
      """
Now, instead of writing the same value everywhere in the program, like that:

```kotlin
fun main() {
  print(
      if (116 > 0) "It's positive: " + 116
      else if(116 < 0) "It's negative: ${dollar}116"
      else "It's 0"
  )
}
```

We can factor that value into `n` and reuse it everywhere.

```kotlin
fun main() {
  val n = 116 // any integer
  print(
      if (n > 0) "It's positive: " + n
      else if(n < 0) "It's negative: ${dollar}n"
      else "It's 0"
  )
}
```

If we want to either change this value or read that value
from what the user of the program tells us, we can change
it everywhere by changing a single line.

This principle of factorization and deduplication will come
back very often in programming but also in the way that data
is stored.
"""
    ),

    // summary
    LessonPage.InfoPage(
      title = "Summary",
      """
Values are there to identify a result and reuse it later.

The better the name, the easier it is to understand what it contains.

They can contain integers, strings, booleans and many other kinds of data that you will discover later.
"""
    )
  )
)

