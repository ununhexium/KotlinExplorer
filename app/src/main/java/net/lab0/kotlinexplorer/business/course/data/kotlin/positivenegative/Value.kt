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
      title = "Labeling",
      question = "Declare a value named `number`",
      snippet = """
${p(0)} number = 1
""",
      explanation = """
`val` declares a value.

The name of this value is `number`.
""",
      answer = listOf("val"),
    ),

    // string value
    LessonPage.CodeQuestionPage(
      title = "Hello value",
      question = """
Assign the *string* `hello` to the *variable* `hello`.
""",
      snippet = """
val ${p(0)} = ${p(1)}
""",
      explanation = """
`"hello"` is a string.

`hello` is a an identifier.
""",
      answer = listOf("hello", "\"hello\""),
    ),

    // naming restrictions: technical standards
    LessonPage.CodeQuestionPage(
      title = "Value name",
      question = """
Choose a valid name.
""",
      snippet = """
val ${p(0)} = "English"
""",
      explanation = """
A variable name may only contain letters, numbers and underscores `_`.

A variable name can start with an underscore `_` or a letter, **not a number**.

`language of Shakespeare` is invalid because it contains spaces.

`1_word` is invalid because it starts with a number.

`en-glish` is invalid because it contains a minus sign `-`.
""",
      answer = listOf("_english"),
      confusion = listOf("language of Shakespeare", "1_word", "en-glish"),
    ),

    // multi choices technically valid val names
    LessonPage.MultipleChoice(
      title = "Valid names",
      question = """
Which of the following names are valid?
""",
      explanation = """
`lower Case` is invalid because it contains a space ` `.
`sugar-free` is invalid because it contains a minus sign `-`.
""",
      choices = listOf("__double_underscore", "UpperCase", "valueName", "lower Case", "sugar-free"),
      answer = setOf(0, 1, 2),
    ),

    // unicode support
    LessonPage.CodeQuestionPage(
      title = "Baguette 火山 \uD83E\uDD56 \uD83D\uDDFB",
      question = """
Choose valid names.
""",
      snippet = """
// french
val ${p(0)} = "Bonjour, ça va?"

// japanese
val ${p(1)} = "今日わ"
""",
      explanation = """
Even though Kotlin supports a large range of characters for its variables names,
you should only use the english alphabet because that's the
smallest common denominator between software developers.

You would not want to learn how to use a Thai, Chinese or Korean keyboard, right?

```kotlin
val ไทย = "Thai"
val 한글 = "Hangul" // korean
```
""",
      answer = listOf("français", "日本語"),
      confusion = listOf("some french", "ja.pa.ne.se"),
    ),

    // longer val names
    LessonPage.CodeQuestionPage(
      title = "Long identifiers",
      question = """
Use camel case to separate words.
""",
      snippet = """
val ${p(0)} = "🐪"
""",
      explanation = """
`alazydehydratedcamel` is hard to read and spaces are not allowed.

To solve that problem, `camel case` is a naming style that uses uppercase letters to separate words.

It can be `upper camel case`, starting with an upper case letter,
like `U`pperCamelCase or `lower camel case`, starting with a lower case letter,
like `l`owerCamelCase.

Alternative naming conventions, generally not used in Kotlin, are
* `snake_case`, separating words with underscores: `a_lazy_dehydrated_camel`.
* `kebab-case`, separating words with dashes: `a-lazy-dehydrated-camel`.
""",
      answer = listOf("aLazyDehydratedCamel"),
    ),

    // valid identifiers
    LessonPage.MultipleChoice(
      title = "Find camel case identifiers",
      question = """
Select the camel case identifiers
""",
      explanation = """
`fancyFeathers` is lower camel case.

`BaldBoa` is upper camel case

`giveItATry` is lower camel case. *Give it a try* has a word made of a single letter.
 Therefore the corresponding variable name will have 2 upper case letters in a row.
 
`sneaky_snake` is snake case.
 
`halfWay_there` is not camel case but a mix of snake and camel case.
It's technically correct but does not follow the naming convention.
""",
      choices = listOf("fancyFeathers", "BaldBoa", "giveItATry", "sneaky_snake", "halfWay_there"),
      answer = setOf(0, 1, 2),
    ),

    // camel case
    LessonPage.CodeQuestionPage(
      title = "Good names \uD83D\uDC2D",
      question = """
Let's say you want to give a name to the number of mice in a house.

Choose the best name.
""",
      snippet = """
val ${p(0)} = 20
""",
      explanation = """
`number_of_mice_in_the_house` is snake case. This is technically a valid name, but Kotlin uses camel case.

Following the naming conventions will make it easier to read code later.
Looking at the naming convention will tell its nature (function, value, keyword, ...).
""",
      answer = listOf("numberOfMiceInTheHouse"),
      confusion = listOf("number_of_mice_in_the_house")
    ),

    // good value names
    LessonPage.CodeQuestionPage(
      title = "Good names \uD83D\uDC08",
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

`nHouseCat` it that the number of house cats, by opposition to stray or wild cats?
This meaning is different from the intended meaning. 
""",
      answer = listOf("numberOfCatsInTheHouse"),
      confusion = listOf("n", "nHouseCat")
    ),

    // print variable
    LessonPage.MultipleChoice(
      title = "Print date \uD83D\uDCC5",
      question = """
What is printed?

```kotlin
val date = "today"
print(date)
```
""",
      explanation = """
`date` is a variable, when calling `print(date)`,
the content of the `date` variable is be printed.
""",
      choices = listOf("today", "date"),
      answer = setOf(0),
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

`한글` is not english, it's korean.

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

    // print time
    LessonPage.MultipleChoice(
      title = "Print time \uD83D\uDD51",
      question = """
What is printed?

```kotlin
val time = "$currentTimeString"
print("time")
```
""",
      explanation = """
`"time"` is a string, print will just print it.

The `time` value is not used here.
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
 
`copy` contains a **reference** to the same `"Hi! :)"` as `original`.
 
They can both be used to access the exact same data.
 
`"Hi! :)"` is never duplicated, never copied. It exists only once.

This implies that any change to `"Hi! :)"` will affect both `original` and `copy`.

This mechanism is called **copy by reference**. More about that in a later chapter.
""",
      choices = listOf("Hi! :)", "Goodbye :(", "original", "copy"),
      answer = setOf(0),
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

Now it's easy to change the value, it's all in one place.

This principle of factorization / deduplication comes
back very often in programming but also in the way that data
is stored.
"""
    ),

    // summary
    LessonPage.InfoPage(
      title = "Summary",
      """
Values are there to **identify** a result and **reuse** it later.

The **better the name**, the **easier** it is to **understand** what it contains.

Values use `theCamelCase` as a naming convention.

Values can contain integers, strings, booleans and many other kinds of data that you will discover later.
"""
    )
  )
)

