package net.lab0.grammar.kotlin

import net.lab0.grammar.kotlin.Highlights.Spot
import net.lab0.grammar.kotlin.KotlinHighlight.ANNOTATION
import net.lab0.grammar.kotlin.KotlinHighlight.BRACKET
import net.lab0.grammar.kotlin.KotlinHighlight.COMMA
import net.lab0.grammar.kotlin.KotlinHighlight.COMMENT
import net.lab0.grammar.kotlin.KotlinHighlight.FUNCTION
import net.lab0.grammar.kotlin.KotlinHighlight.KEYWORD
import net.lab0.grammar.kotlin.KotlinHighlight.MODIFIER
import net.lab0.grammar.kotlin.KotlinHighlight.NUMBER
import net.lab0.grammar.kotlin.KotlinHighlight.OPERATOR
import net.lab0.grammar.kotlin.KotlinHighlight.STRING
import net.lab0.grammar.kotlin.KotlinHighlight.STRING_ESCAPED_CHARACTER
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
    assertThat(code, spots).hasSpots()
  }

  @Test
  fun `can highlight decimal numbers in val declarations`() {
    // given
    val code = """val i = 10"""

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasSpots(
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
    assertThat(code, spots).hasSpots(
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
    assertThat(code, spots).hasSpots(
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
    assertThat(code, spots).hasSpots(
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
    assertThat(code, spots).hasSpots(
        Spot(KEYWORD, 0, 2),
        Spot(STRING, 8, 14),
    )
  }

  @Test
  fun `can highlight numbers in a map declaration`() {
    // given
    val code = """val m = mapOf(3 to "Fizz", 0x5 to "Buzz")"""

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasSpots(
        Spot(KEYWORD, 0, 2),
        Spot(NUMBER, 14, 14),
        Spot(STRING, 19, 24),
        Spot(NUMBER, 27, 29),
        Spot(STRING, 34, 39),
    )
  }

  @Test
  fun `can highlight package`() {
    // given
    val code = """package com.example"""

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasSpots(
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
    assertThat(code, spots).hasSpots(
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
    assertThat(code, spots).hasSpots(
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
    assertThat(code, spots).hasSpots(
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
    assertThat(code, spots).hasSpots(
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
    assertThat(code, spots).hasSpots(
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
    assertThat(code, spots).hasSpots(
        Spot(FUNCTION, 0, 6),
        Spot(STRING, 8, 12),
    )
  }

  @Test
  fun `can highlight function declaration params and return body`() {
    // given
    val code = "fun fizzBuzz(i: Int = 0): Int { return 0 }"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasSpots(
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
    assertThat(code, spots).hasSpots(
        Spot(KEYWORD, 0, 6),
        Spot(KEYWORD, 29, 31),
        Spot(STRING, 54, 68),
    )
  }

  @Test
  fun `can highlight if else true false null`() {
    // given
    val code = "val foo = if (true) false else null"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasSpots(
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
    assertThat(code, spots).hasSpots(
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
    assertThat(code, spots).hasSpots(
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
    assertThat(code, spots).hasSpots(
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
    assertThat(code, spots).hasSpots(
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
    assertThat(code, spots).hasSpots(
        Spot(KEYWORD, 0, 2),
        Spot(FUNCTION, 4, 7),
        Spot(BRACKET, 8, 8),
        Spot(BRACKET, 9, 9),
        Spot(BRACKET, 11, 11),
        Spot(BRACKET, 22, 22),
        Spot(STRING, 23, 37),
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
    assertThat(code, spots).hasSpots(
        Spot(KEYWORD, 0, 2),
        Spot(FUNCTION, 4, 7),
        Spot(BRACKET, 8, 8),
        Spot(BRACKET, 9, 9),
        Spot(BRACKET, 11, 11),
        Spot(BRACKET, 32, 32),
        Spot(STRING, 33, 47),
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
    assertThat(code, spots).hasSpots(
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
    assertThat(code, spots).hasSpots(
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
    assertThat(code, spots).hasSpots(
        Spot(STRING, 1, 10),
        Spot(STRING_ESCAPED_CHARACTER, 5, 6),
    )
  }

  @TestFactory
  fun `can highlight 1-char operators for ints`(): Iterable<DynamicTest> {
    // TODO: "<", ">"
    val operators = listOf("+", "-", "*", "/")

    return operators.map { operator ->
      DynamicTest.dynamicTest(operator) {
        // given
        val code = """val b = 1 $operator 2"""

        // when
        val spots = extractSpots(code)

        // then
        assertThat(code, spots).hasSpots(
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
        assertThat(code, spots).hasSpots(
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
    /// TODO test "${"${"\"}"}"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasSpots(
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
  fun `can highlight multiline string expression`() {
    // given
    val code = "\"\"\"\${1+1}\"\"\""

    // when
    val spots = extractSpots(code)

    // then
    assertThat(code, spots).hasSpots(
        Spot(STRING, 0, 2),
        Spot(STRING_ESCAPED_CHARACTER, 3, 4),
        Spot(NUMBER, 5, 5),
        Spot(OPERATOR, 6, 6),
        Spot(NUMBER, 7, 7),
        Spot(STRING_ESCAPED_CHARACTER, 8, 8),
        Spot(STRING, 9, 11),
    )
  }
}
