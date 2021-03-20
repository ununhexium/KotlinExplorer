package net.lab0.kotlinexplorer.business.course.data.kotlin.pocketcalculator

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object Integers : LessonImpl(
  id = "kotlin.pocketcalculator.integers",
  title = "Integers",
  pages = listOf(

    // Declare an int
    LessonPage.CodeQuestionPage(
      title = "Declaration",
      question = """
How to declare an *integer value* named *example* that contains *42*
""",
      snippet = """
${p(0)} ${p(1)} ${p(2)} ${p(3)}
""",
      explanation = """
For a value declaration, it's always `val` *name* `=` *value*
""",
      answer = listOf("val", "example", "=", "42"),
      confusion = listOf("var", "is"),
    ),

    // Minimum
    LessonPage.CodeQuestionPage(
      title = "Minimum Int",
      question = """
Declare the smallest `Int`eger.
""",
      snippet = """
val minimum:Int = ${p(0)}
""",
      explanation = """
`Int.MIN_VALUE` is a constant that contains the smallest possible `Int`.

Its value is accessible via the `Int` *property* `MIN_VALUE`.

Its value is `-2,147,483,648` or `-2^31`

`-9999999999` is too small for an `Int`.

`-2145483648` is not the right value, notice the `5` instead of `7`.
Because of this kind of typos, **always** use the constant instead of typing the value manually.
""",
      answer = listOf("Int.MIN_VALUE"),
      confusion = listOf("-999999999", "-2149483648"),
    ),

    // 2 words about properties
    LessonPage.InfoPage(
      title = "Properties?",
      """
`Properties` are related to `classes`.
You will see later in detail what these are exactly.

For the moment, you can see `classes` as a named group of elements.

These `classes` contain other named elements, they can be
 properties, functions, or other types of elements.
 
To access an element in these groups, the point `.` operator is used.

`Int.MIN_VALUE` means "Access the `MIN_VALUE` property inside the `Int` class"

The `Int` object is similar to

```kotlin
class Int {
  val MIN_VALUE: Int = -2147483648
  // many other things.
}
```

"""
    ),

    // Maximum
    LessonPage.CodeQuestionPage(
      title = "Maximum Int",
      question = """
Declare the largest `Int`eger.
""",
      snippet = """
val maximum:Int = ${p(0)}
""",
      explanation = """
`Int.MAX_VALUE` is a constant that contains the smallest possible `Int`.

Its value is `2,147,483,647` or `2^31 -1`.

Why `-1` ?

`Int`s are encoded on 32 bits. That's `2,147,483,648` positive and `2,147,483,648` negative numbers.

But we also need a place for 0, and it is on the positive side.
Therefore we have positive numbers in the range `0` - `2,147,483,647`
and negative numbers in the range `-1` - `-2,147,483,648` 

`9999999999` is too big for an `Int`.

`2147403648` is not the right value, notice the `0` in place of the usual `8` in the middle.
Because of this kind of typos, always use the constant instead of typing the value manually.
""",
      answer = listOf("Int.MAX_VALUE"),
      confusion = listOf("999999999", "2147403648"),
    ),

    // Underscore for big numbers
    LessonPage.CodeQuestionPage(
      title = "Big numbers",
      question = """
Group digits
""",
      snippet = """
val prime = ${p(0)}
""",
      explanation = """
To make larger number more readable, you can use underscores `_`
to split the digits as you like. The position of the underscore
is flexible.

These are all the same values:

```kotlin
val noSpacer = 567629137
val european = 567_629_137
val indian = 56_76_29_137
val japanese = 5_6762_9137
val fancy = 5_67__629___137
```
""",
      answer = listOf("567_629_137"),
      confusion = listOf("56_76_29_137", "5_6762_9137"),
      validator = { true },
    ),

    // Bigger
    LessonPage.CodeQuestionPage(
      title = "Long",
      question = """
To go higher than the maximum possible value on an integer, the `Long` type is available.
""",
      snippet = """
val long: ${p(0)} = 999_999_999_999
""",
      explanation = """
`Long` is a type of integer using 64 bits.

It can hold any integer value between 
`-2^63` (`-9223372036854775808`) and `2^63 -1` (`9223372036854775807`).
        
`BigInteger` is for unlimited size integers.
It can grow as much as needed, but it is limited by the amount of available memory.
However, you must initialize them differently.

`Long Int` can't be valid because of the space.
""",
      answer = listOf("Long"),
      confusion = listOf("BigInteger", "Long Int"),
    ),

    // Sizes
    LessonPage.CodeQuestionPage(
      title = "Sizes",
      question = """
Assign the right sizes
""",
      snippet = """
val babyByte: ${p(0)} = 127
val smallShort: ${p(1)} = 32767
val intermediateInt: ${p(2)} = 2147483647
val largeLong: ${p(3)} = 9223372036854775807
""",
      explanation = """
There are actually 4 main integers sizes, differentiated by the number of bits they use.

`Byte` on `8` bits.

`Short` on `16` bits.

`Int` on `32` bits.

`Long` on `64` bits.

`BigInteger`s are a different thing. They are not natively
 supported by CPUs and are therefore slower. Prefer using any of the above whenever possible.
""",
      answer = listOf("Byte", "Short", "Int", "Long"),
      confusion = listOf(),
    ),

    // UInt
    LessonPage.CodeQuestionPage(
      title = "No sign",
      question = """
Declare an unsigned integer. 
""",
      snippet = """
val unsigned: ${p(0)} = 4_000_000_000u
""",
      explanation = """
If you don't need the sign, you can do so with unsigned integers.
That way you can go twice as high on the same number of bits and/or
guarantee to never go below 0.

`u` indicates that the number is unsigned.

`Unsigned Int` is not possible because types can't have spaces.
""",
      answer = listOf("UInt"),
      confusion = listOf("Unsigned Int"),
    ),

    // MAX + 1
    LessonPage.MultipleChoice(
      title = "A bit more?",
      question = """
What will happen here?

```kotlin
val what = UInt.MAX_VALUE // 4294967295
val more = what + 1u

print(more)
```
""",
      explanation = """
The number reached the maximum possible value, 
like `1111 1111` = `255` if it were using 8 bits.

By adding 1, all the bits are reset to 0 because of
 the carry in the addition.
 
`1111 1111 + 1 = 1 0000 0000`

Forget the highest bit, as it's limited to `8` bits, you get.

`00000000` = `0`

This behaviours reflects the way your CPU works with integers.
""",
      choices = listOf(
        "0",
        "Some overflow error",
        "4294967296: It will be converted to a ULong",
        "The same value: it can't go higher"
      ),
      answer = setOf(0),
    ),

    // MIN - 1
    LessonPage.MultipleChoice(
      title = "A bit less?",
      question = """
What will happen here?

```kotlin
val what = UInt.MIN_VALUE // 0
val less = what - 1u

print(less)
```
""",
      explanation = """
The data types will not be converted automatically.
It can't transform from `UInt` to `Int` without an explicit instruction do to so.

The number reached the minimum possible value, 
subtracting 1, all the bits are now 1.

This is just the opposite operation of adding 1 to the maximum value.
""",
      choices = listOf(
        "4294967295: The MAX possible value because it's looping",
        "0: Can't go lower",
        "-1: It can be converted to a signed integer."
      ),
      answer = setOf(0),
    ),

    // Type suffixes
    LessonPage.CodeQuestionPage(
      title = "Suffixes",
      question = """
Give the right suffix to each number.
""",
      snippet = """
val integer: Int = 1${p(0)}
val long: Long = 1${p(1)}
val unsigned: UInt = 1${p(2)}
val unsignedLong: UInt = 1${p(3)}
""",
      explanation = """
The suffixes are there for type inference.

`1` is an `Int` by default. Using these suffixes tells 
Kotlin what number type they should be.

This exercise indicated the types in 2 ways: 

* with the explicit type
* with the suffixes

Only 1 of them is required to enforce a type. The code above can also be written:

```kotlin
val integer = 1
val long = 1L
val unsigned = 1u
val unsignedLong = 1uL
```

or

```kotlin
val integer: Int = 1
val long: Long = 1
val unsigned: UInt = 1u
val unsignedLong: UInt = 1u
```

`l` / `L` indicates that it's a `Long`.

`u` / `U` indicates that it's a unsigned `U`.
""",
      answer = listOf("", "L", "u", "uL"),
      confusion = listOf(),
    ),

    // type conversion
    LessonPage.CodeQuestionPage(
      title = "Type cast",
      question = """
Convert `1` into the right type.
""",
      snippet = """
val integer: Int = 1${p(0)}
val long: Long = 1${p(1)}
val unsigned: UInt = 1${p(2)}
val unsignedLong: ULong = 1${p(3)}
""",
      explanation = """
This is how to explicitly convert from one type of number to another one.

The difference with the previous notation is that here, `1` is converted 
when the program is executing, while previously it was already the right 
type before execution.

Always prefer the previous method for declaration and this method when necessary.

As for the nature of these, they are `members` of the `Int` class.
""",
      answer = listOf(".toInt()", ".toLong()", ".toUInt()", ".toULong()"),
      confusion = listOf(),
    ),

    // members?
    LessonPage.InfoPage(
      title = "Members",
      """
Just like properties that are variables declared in a class, a `member`
 function is a function declared inside a class.
 
The `Int` class now looks like
 
```kotlin
class Int {
  val MIN_VALUE: Int = -2147483648
  
  fun toLong(): Long {
    // some code for conversion
  }
  // many other things.
}
```

The other number types also have their own conversion members `.toByte()`, `toInt()` etc.
"""
    ),

    // passing the right ype
    LessonPage.CodeQuestionPage(
      title = "Convert to Byte",
      question = """
Convert the value to fit the function's definition.
""",
      snippet = """
fun add(a: Byte, b: Short): Int {
  return a + b
}

val potatoes = 53
val carrots = 12

add(potatoes${p(0)}, carrots${p(1)})
""",
      explanation = """
`a: Byte, b: Short` the function requires 2 arguments, a `Byte` and a `Short`.

Kotlin will never automatically loose precision to fit a function's call.
""",
      answer = listOf(".toByte()", ".toShort()"),
      confusion = listOf(".toInt()", ".toLong()"),
    ),

    LessonPage.InfoPage(
      title = "Summary",
      """
Integers can be expressed in 5 formats:

* `Byte` 8 bits
* `Short` 16 bits
* `Int` 32 bits
* `Long` 64 bits
* `BigInteger` unlimited bits

And 2 modes:

* Signed, the default
* Unsigned

When overflowing or underflowing, integers loop back to the other end of their number range.

Signed (`u`, `U`), and long (`l`, `L`) suffixes explicit the number type at declaration.

`.toByte()`, `.toShort()`, `.toInt()`, `.toLong()` convert the numbers at execution.

You may have a look at the 8-bit integer explorer in the tools.
"""
    )
  )
)



// TODO: notations, 0b, 0x


