package net.lab0.grammar.kotlin

import net.lab0.grammar.kotlin.Highlights.Spot
import net.lab0.grammar.kotlin.KotlinHighlight.ANNOTATION
import net.lab0.grammar.kotlin.KotlinHighlight.COMMA
import net.lab0.grammar.kotlin.KotlinHighlight.COMMENT
import net.lab0.grammar.kotlin.KotlinHighlight.KEYWORD
import net.lab0.grammar.kotlin.KotlinHighlight.MODIFIER
import net.lab0.grammar.kotlin.KotlinHighlight.NUMBER
import net.lab0.grammar.kotlin.KotlinHighlight.STRING
import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.Test


class KotlinSyntaxHighlighterVisitorTest {

  @Test
  fun `can extract decimal numbers in val declarations`() {
    // given
    val code = """val i = 10"""

    // when
    val spots = extractSpots(code)

    // then
    assertThat(spots).containsExactly(
        Spot(KEYWORD, 0, 2),
        Spot(NUMBER, 8, 9),
    )
  }

  @Test
  fun `can extract hexadecimal numbers in var declarations`() {
    // given
    val code = """var i = 0xff"""

    // when
    val spots = extractSpots(code)

    // then
    assertThat(spots).containsExactly(
        Spot(KEYWORD, 0, 2),
        Spot(NUMBER, 8, 11),
    )
  }

  @Test
  fun `can extract annotation`() {
    // given
    val code = "@A enum class E"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(spots).containsExactly(
        Spot(ANNOTATION, 0, 1),
        Spot(MODIFIER, 3, 6),
        Spot(KEYWORD, 8, 12),
    )
  }

  @Test
  fun `can extract functions`() {
    // given
    val code = "private override fun f(){}"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(spots).containsExactly(
        Spot(MODIFIER, 0, 6),
        Spot(MODIFIER, 8, 15),
        Spot(KEYWORD, 17, 19),
    )
  }

  @Test
  fun `can extract string declaration`() {
    // given
    val code = """val s = "hello""""

    // when
    val spots = extractSpots(code)

    // then
    assertThat(spots).containsExactly(
        Spot(KEYWORD, 0, 2),
        Spot(STRING, 8, 14),
    )
  }

  @Test
  fun `can extract numbers in a map declaration`() {
    // given
    val code = """val m = mapOf(3 to "Fizz", 0x5 to "Buzz")"""

    // when
    val spots = extractSpots(code)

    // then
    assertThat(spots).containsExactly(
        Spot(KEYWORD, 0, 2),
        Spot(NUMBER, 14, 14),
        Spot(STRING, 19, 24),
        Spot(NUMBER, 27, 29),
        Spot(STRING, 34, 39),
    )
  }

  @Test
  fun `can extract package`() {
    // given
    val code = """package com.example"""

    // when
    val spots = extractSpots(code)

    // then
    assertThat(spots).containsExactly(
        Spot(KEYWORD, 0, 6)
    )
  }

  @Test
  fun `can extract data class`() {
    // given
    val code = "data class Data(val a:Int, var b:String)"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(spots).containsExactly(
        Spot(MODIFIER, 0, 3),
        Spot(KEYWORD, 5, 9),
        Spot(KEYWORD, 16, 18),
        Spot(COMMA, 25, 25),
        Spot(KEYWORD, 27, 29),
    )
  }

  @Test
  fun `can extract data class with modifiers`() {
    // given
    val code = "data class Data(public override val a:Int)"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(spots).containsExactly(
        Spot(MODIFIER, 0, 3),
        Spot(KEYWORD, 5, 9),
        Spot(MODIFIER, 16, 21),
        Spot(MODIFIER, 23, 30),
        Spot(KEYWORD, 32, 34),
    )
  }

  @Ignore("Seems not implemented")
  @Test
  fun `can extract comments`() {
    // given
    val code = "//comment"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(spots).containsExactly(
        Spot(COMMENT, 0, 8),
    )
  }

  @Test
  fun `can highlight function declaration params and return body`() {
    // given
    val code = "fun fizzBuzz(i: Int = 0): Int { return 0 }"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(spots).containsExactly(
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
    assertThat(spots).containsExactly(
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
    assertThat(spots).containsExactly(
        Spot(KEYWORD, 0, 2),
        Spot(KEYWORD, 10, 11),
        Spot(KEYWORD, 14, 17),
        Spot(KEYWORD, 20, 24),
        Spot(KEYWORD, 26, 29),
        Spot(KEYWORD, 31, 34),
    )
  }
}
