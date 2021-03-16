package net.lab0.grammar.kotlin

import net.lab0.grammar.kotlin.Highlights.Spot
import net.lab0.grammar.kotlin.KotlinHighlight.ANNOTATION
import net.lab0.grammar.kotlin.KotlinHighlight.BRACKET
import net.lab0.grammar.kotlin.KotlinHighlight.CHARACTER
import net.lab0.grammar.kotlin.KotlinHighlight.CHARACTER_ESCAPED
import net.lab0.grammar.kotlin.KotlinHighlight.COMMA
import net.lab0.grammar.kotlin.KotlinHighlight.COMMENT
import net.lab0.grammar.kotlin.KotlinHighlight.FUNCTION
import net.lab0.grammar.kotlin.KotlinHighlight.KEYWORD
import net.lab0.grammar.kotlin.KotlinHighlight.MODIFIER
import net.lab0.grammar.kotlin.KotlinHighlight.NUMBER
import net.lab0.grammar.kotlin.KotlinHighlight.OPERATOR
import net.lab0.grammar.kotlin.KotlinHighlight.STRING
import net.lab0.grammar.kotlin.KotlinHighlight.STRING_ESCAPED_CHARACTER
import net.lab0.grammar.kotlin.KotlinHighlight.TYPE
import net.lab0.grammar.kotlin.SpotsAssert.Companion.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory


class KotlinSyntaxHighlighterVisitorTest {

  @Test
  fun `cont crash on invalid input`() {
    // given
    val code = """jhdfu8q233754  ;p30 [2]=4p 7&*^%- {)"""

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasAtLeastSpotsH()
  }

  @Test
  fun `can highlight decimal numbers in val declarations`() {
    // given
    val code = """val i = 10"""

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasAtLeastSpotsH(
      Spot(KEYWORD, 0, 2),
      Spot(NUMBER, 8, 9),
    )
  }

  @Test
  fun `can highlight hexadecimal numbers in var declarations`() {
    // given
    val code = """var i = 0xff"""

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasAtLeastSpotsH(
      Spot(KEYWORD, 0, 2),
      Spot(NUMBER, 8, 11),
    )
  }

  @Test
  fun `can highlight annotation`() {
    // given
    val code = "@A enum class E"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasAtLeastSpotsH(
      Spot(ANNOTATION, 0, 1),
      Spot(MODIFIER, 3, 6),
      Spot(KEYWORD, 8, 12),
    )
  }

  @Test
  fun `can highlight functions`() {
    // given
    val code = "private override fun f(){}"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasAtLeastSpotsH(
      Spot(MODIFIER, 0, 6),
      Spot(MODIFIER, 8, 15),
      Spot(KEYWORD, 17, 19),
      Spot(FUNCTION, 21, 21),
    )
  }

  @Test
  fun `can highlight string declaration`() {
    // given
    val code = """val s = "hello""""

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasAtLeastSpotsH(
      Spot(KEYWORD, 0, 2),
      Spot(STRING, 8, 8),
      Spot(STRING, 9, 13),
      Spot(STRING, 14, 14),
    )
  }

  @Test
  fun `can highlight numbers in a map declaration`() {
    // given
    val code = """val m = mapOf(3 to "Fizz", 0x5 to "Buzz")"""

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasAtLeastSpotsH(
      Spot(KEYWORD, 0, 2),
      Spot(NUMBER, 14, 14),
      Spot(STRING, 19, 19),
      Spot(STRING, 20, 23),
      Spot(STRING, 24, 24),
      Spot(NUMBER, 27, 29),
      Spot(STRING, 34, 34),
      Spot(STRING, 35, 38),
      Spot(STRING, 39, 39),
    )
  }

  @Test
  fun `can highlight package`() {
    // given
    val code = """package com.example"""

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasAtLeastSpotsH(
      Spot(KEYWORD, 0, 6)
    )
  }

  @Test
  fun `can highlight data class`() {
    // given
    val code = "data class Data(val a:Int, var b:String)"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasAtLeastSpotsH(
      Spot(MODIFIER, 0, 3),
      Spot(KEYWORD, 5, 9),
      Spot(KEYWORD, 16, 18),
      Spot(COMMA, 25, 25),
      Spot(KEYWORD, 27, 29),
    )
  }

  @Test
  fun `can highlight data class with modifiers`() {
    // given
    val code = "data class Data(public override val a:Int)"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasAtLeastSpotsH(
      Spot(MODIFIER, 0, 3),
      Spot(KEYWORD, 5, 9),
      Spot(MODIFIER, 16, 21),
      Spot(MODIFIER, 23, 30),
      Spot(KEYWORD, 32, 34),
    )
  }

  @Test
  fun `can highlight line comments`() {
    // given
    val code = "//comment"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasAtLeastSpotsH(
      Spot(COMMENT, 0, 8),
    )
  }

  @Test
  fun `can highlight delimited comments`() {
    // given
    val code = "/* hey! */"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasAtLeastSpotsH(
      Spot(COMMENT, 0, 9),
    )
  }

  @Test
  fun `can highlight a simple println of a number`() {
    // given
    val code = "println(1)"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasAtLeastSpotsH(
      Spot(FUNCTION, 0, 6),
      Spot(NUMBER, 8, 8),
    )
  }

  @Test
  fun `can highlight a simple println of a string`() {
    // given
    val code = """println("foo")"""

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasExactlySpots(
      Spot(FUNCTION, 0, 6),
      Spot(BRACKET, 7, 7),
      Spot(STRING, 8, 8),
      Spot(STRING, 9, 11),
      Spot(STRING, 12, 12),
      Spot(BRACKET, 13, 13),
    )
  }

  @Test
  fun `can highlight function declaration params and return body`() {
    // given
    val code = "fun fizzBuzz(i: Int = 0): Int { return 0 }"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasAtLeastSpotsH(
      Spot(KEYWORD, 0, 2),
      Spot(NUMBER, 22, 22),
      Spot(KEYWORD, 32, 37),
      Spot(NUMBER, 39, 39)
    )
  }

  @Test
  fun `can highlight basic kotlin main`() {
    // given
    val code = """
            |package org.kotlinlang.play
            |
            |fun main() {
            |    println("Hello, World!")
            |}
        """.trimMargin()

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasAtLeastSpotsH(
      Spot(KEYWORD, 0, 6),
      Spot(KEYWORD, 29, 31),
      Spot(STRING, 54, 54),
      Spot(STRING, 55, 67),
      Spot(STRING, 68, 68),
    )
  }

  @Test
  fun `can highlight if else true false null`() {
    // given
    val code = "val foo = if (true) false else null"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasAtLeastSpotsH(
      Spot(KEYWORD, 0, 2),
      Spot(KEYWORD, 10, 11),
      Spot(KEYWORD, 14, 17),
      Spot(KEYWORD, 20, 24),
      Spot(KEYWORD, 26, 29),
      Spot(KEYWORD, 31, 34),
    )
  }

  @Test
  fun `can highlight parenthesis and square brackets`() {
    // given
    val code = "val x = (a[0])"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasAtLeastSpotsH(
      Spot(KEYWORD, 0, 2),
      Spot(BRACKET, 8, 8),
      Spot(BRACKET, 10, 10),
      Spot(NUMBER, 11, 11),
      Spot(BRACKET, 12, 12),
      Spot(BRACKET, 13, 13),
    )
  }

  @Test
  fun `can highlight parenthesis in if`() {
    // given
    val code = "val foo = (true)"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasAtLeastSpotsH(
      Spot(BRACKET, 10, 10),
      Spot(BRACKET, 15, 15),
    )
  }

  @Test
  fun `can highlight angle and curly brackets`() {
    // given
    val code = "fun <X> foo(){ return 0 }"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasAtLeastSpotsH(
      Spot(KEYWORD, 0, 2),
      Spot(BRACKET, 4, 4),
      Spot(BRACKET, 6, 6),
      Spot(BRACKET, 11, 11),
      Spot(BRACKET, 12, 12),
      Spot(BRACKET, 13, 13),
      Spot(BRACKET, 24, 24),
    )
  }

  @Test
  fun `can highlight where`() {
    // given
    val code = "interface Interface<I> where I:Interface<I>"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasAtLeastSpotsH(
      Spot(KEYWORD, 23, 27),
    )
  }

  @Test
  fun `can highlight main`() {
    // given
    val code = """
        |fun main() {
        |  println("Hello, World!")
        |}
      """.trimMargin()

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasExactlySpots(
      Spot(KEYWORD, 0, 2),
      Spot(FUNCTION, 4, 7),
      Spot(BRACKET, 8, 8),
      Spot(BRACKET, 9, 9),
      Spot(BRACKET, 11, 11),
      Spot(FUNCTION, 15, 21),
      Spot(BRACKET, 22, 22),
      Spot(STRING, 23, 23),
      Spot(STRING, 24, 36),
      Spot(STRING, 37, 37),
      Spot(BRACKET, 38, 38),

      Spot(BRACKET, 40, 40),
    )
  }

  @Test
  fun `can highlight main with comment`() {
    // given
    val code = """
        |fun main() {
        |  /*****/
        |  println("Hello, World!")
        |}
      """.trimMargin()

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasExactlySpots(
      Spot(KEYWORD, 0, 2),
      Spot(FUNCTION, 4, 7),
      Spot(BRACKET, 8, 8),
      Spot(BRACKET, 9, 9),
      Spot(BRACKET, 11, 11),
      Spot(COMMENT, 15, 21),
      Spot(FUNCTION, 25, 31),
      Spot(BRACKET, 32, 32),
      Spot(STRING, 33, 33),
      Spot(STRING, 34, 46),
      Spot(STRING, 47, 47),
      Spot(BRACKET, 48, 48),
      Spot(BRACKET, 50, 50),

      )
  }

  @Test
  fun `failsafe on garbage data`() {
    // given
    val code = """
      |jhdij iero8t0389- ;dba[ 35=-
    """.trimMargin()

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).isEqualTo(listOf<Highlights<KotlinHighlight>>())
  }

  @Test
  fun `can highlight floats`() {
    // given
    val code = "val f = 3.1415f"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasAtLeastSpotsH(
      Spot(KEYWORD, 0, 2),
      Spot(NUMBER, 8, 14),
    )
  }

  @Test
  fun `can highlight doubles`() {
    // given
    val code = "val d = 3.1415"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasAtLeastSpotsH(
      Spot(KEYWORD, 0, 2),
      Spot(NUMBER, 8, 13),
    )
  }

  @Test
  fun `can highlight escaped chars in strings`() {
    // given
    val code = """ "quo\"ted" """

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasAtLeastSpotsH(
      Spot(STRING, 1, 1),
      Spot(STRING, 2, 4),
      Spot(STRING_ESCAPED_CHARACTER, 5, 6),
      Spot(STRING, 7, 9),
      Spot(STRING, 10, 10),
    )
  }

  @TestFactory
  fun `can highlight 1-char operators for ints`(): Iterable<DynamicTest> {
    val operators = listOf("+", "-", "*", "/", "<", ">")

    return operators.map { operator ->
      DynamicTest.dynamicTest(operator) {
        // given
        val code = """val b = 1 $operator 2"""

        // when
        val spots = extractSpots(code)

        // then
        assertThat(code, spots).hasAtLeastSpotsH(
          Spot(KEYWORD, 0, 2),
          Spot(OPERATOR, 6, 6),
          Spot(NUMBER, 8, 8),
          Spot(OPERATOR, 10, 10),
          Spot(NUMBER, 12, 12),
        )
      }
    }
  }

  @TestFactory
  fun `can highlight 2-char operators for ints`(): Iterable<DynamicTest> {
    val operators = listOf("==", "!=", ">=", "<=")

    return operators.map { operator ->
      DynamicTest.dynamicTest(operator) {
        // given
        val code = """val b = 1 $operator 2"""

        // when
        val spots = extractSpots(code)

        // then
        assertThat(code, spots).hasAtLeastSpotsH(
          Spot(KEYWORD, 0, 2),
          Spot(OPERATOR, 6, 6),
          Spot(NUMBER, 8, 8),
          Spot(OPERATOR, 10, 11),
          Spot(NUMBER, 13, 13),
        )
      }
    }
  }

  @Test
  fun `can highlight single line string expression`() {
    // given
    val code = "\"\${1+1}\""

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasAtLeastSpotsH(
      Spot(STRING, 0, 0),
      Spot(STRING_ESCAPED_CHARACTER, 1, 2),
      Spot(NUMBER, 3, 3),
      Spot(OPERATOR, 4, 4),
      Spot(NUMBER, 5, 5),
      Spot(STRING_ESCAPED_CHARACTER, 6, 6),
      Spot(STRING, 7, 7),
    )
  }

  @Test
  fun `can highlight verbatim string expression`() {
    // given
    val code = "\"\"\"\${1+1}\"\"\""

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasExactlySpots(
      Spot(STRING, 0, 2),
      Spot(STRING_ESCAPED_CHARACTER, 3, 4),
      Spot(NUMBER, 5, 5),
      Spot(OPERATOR, 6, 6),
      Spot(NUMBER, 7, 7),
      Spot(STRING_ESCAPED_CHARACTER, 8, 8),
      Spot(STRING, 9, 11),
    )
  }

  @Test
  fun `can highlight multiline string expression`() {
    // given
    val code = "\"\"\"\n\${1+1}\n\"\"\""

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasExactlySpots(
      Spot(STRING, 0, 2),
      Spot(STRING, 3, 3),
      Spot(STRING_ESCAPED_CHARACTER, 4, 5),
      Spot(NUMBER, 6, 6),
      Spot(OPERATOR, 7, 7),
      Spot(NUMBER, 8, 8),
      Spot(STRING_ESCAPED_CHARACTER, 9, 9),
      Spot(STRING, 10, 10),
      Spot(STRING, 11, 13),
    )
  }

  @Test
  fun `import is a keyword`() {
    // given
    val code = "import com.example.Class"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasExactlySpots(
      Spot(KEYWORD, 0, 5),
    )
  }

  @Test
  fun `highlight while keyword`() {
    // given
    val code = "while(true){}"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasExactlySpots(
      Spot(KEYWORD, 0, 4),
      Spot(BRACKET, 5, 5),
      Spot(KEYWORD, 6, 9),
      Spot(BRACKET, 10, 10),
      Spot(BRACKET, 11, 11),
      Spot(BRACKET, 12, 12),
    )
  }

  @Test
  fun `support ++ and -- postfix operators`() {
    // given
    val code = "a++;b--"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasExactlySpots(
      Spot(OPERATOR, 1, 2),
      Spot(OPERATOR, 5, 6),
    )
  }

  @Test
  fun `support !! assertion`() {
    // given
    val code = "a!!"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasExactlySpots(
      Spot(OPERATOR, 1, 2),
    )
  }

  @Test
  fun `support when keyword`() {
    // given
    val code = "when(true){}"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasExactlySpots(
      Spot(KEYWORD, 0, 3),
      Spot(BRACKET, 4, 4),
      Spot(KEYWORD, 5, 8),
      Spot(BRACKET, 9, 9),
      Spot(BRACKET, 10, 10),
      Spot(BRACKET, 11, 11),
    )
  }

  @Test
  fun `can higlight trailing comas`() {
    // given
    val code = "val l = listOf(1, 2, 3,)"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasExactlySpots(
      Spot(highlight = KEYWORD, start = 0, end = 2),
      Spot(highlight = OPERATOR, start = 6, end = 6),
      Spot(highlight = BRACKET, start = 14, end = 14),
      Spot(highlight = NUMBER, start = 15, end = 15),
      Spot(highlight = NUMBER, start = 18, end = 18),
      Spot(highlight = NUMBER, start = 21, end = 21),
      Spot(highlight = BRACKET, start = 23, end = 23),
    )
  }

  @Test
  fun `support comment inside parentheses`() {
    // given
    val code = "val i = (1/**/)/**/"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasExactlySpots(
      // "val"
      Spot(highlight = KEYWORD, start = 0, end = 2),
      // "="
      Spot(highlight = OPERATOR, start = 6, end = 6),
      // "("
      Spot(highlight = BRACKET, start = 8, end = 8),
      // "1"
      Spot(highlight = NUMBER, start = 9, end = 9),
      // "/**/" inside comment
      Spot(highlight = COMMENT, start = 10, end = 13),
      // ")"
      Spot(highlight = BRACKET, start = 14, end = 14),
      // "/**/" outside comment
      Spot(highlight = COMMENT, start = 15, end = 18),
    )
  }

  @Test
  fun `can highlight parameter types`() {
    // given
    val code = "fun f(a:Int){}"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasExactlySpots(
      // "Int" param type
      Spot(highlight = TYPE, start = 8, end = 10),

      // other elements
      Spot(highlight = KEYWORD, start = 0, end = 2),
      Spot(highlight = FUNCTION, start = 4, end = 4),
      Spot(highlight = BRACKET, start = 5, end = 5),
      Spot(highlight = BRACKET, start = 11, end = 11),
      Spot(highlight = BRACKET, start = 12, end = 12),
      Spot(highlight = BRACKET, start = 13, end = 13),
    )
  }

  @Test
  fun `can highlight character literals`() {
    // given
    val code = "val c = 'c'"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasExactlySpots(
      // "val"
      Spot(highlight = KEYWORD, start = 0, end = 2),
      // "="
      Spot(highlight = OPERATOR, start = 6, end = 6),
      // "'c'"
      Spot(highlight = CHARACTER, start = 8, end = 10),
    )
  }

  @Test
  fun `can highlight escaped character literals`() {
    // given
    val code = "val c = '\\''"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasExactlySpots(
      // "val"
      Spot(highlight = KEYWORD, start = 0, end = 2),
      // "="
      Spot(highlight = OPERATOR, start = 6, end = 6),
      // "'"
      Spot(highlight = CHARACTER, start = 8, end = 8),
      // "\'"
      Spot(highlight = CHARACTER_ESCAPED, start = 9, end = 10),
      // "'"
      Spot(highlight = CHARACTER, start = 11, end = 11),
    )
  }

  @Test
  fun `can highlight plus and minus as prefix`() {
    // given
    val code = "val p = +1"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasExactlySpots(
      // "val"
      Spot(highlight = KEYWORD, start = 0, end = 2),
      // "="
      Spot(highlight = OPERATOR, start = 6, end = 6),
      // "+"
      Spot(highlight = OPERATOR, start = 8, end = 8),
      // "1"
      Spot(highlight = NUMBER, start = 9, end = 9),
    )
  }
}
