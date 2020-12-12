package com.example.jetpackcomposeexplorer.code

import com.example.jetpackcomposeexplorer.code.Highlights.Spot
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.ANNOTATION
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.CLASS_DECLARATION
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.CLASS_IDENTIFIER
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.CLASS_PARAMETER
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.COMMA
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.COMMENT
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.DECIMAL_INTEGER
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.FUNCTION_DECLARATION
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.HEXADECIMAL_INTEGER
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.KEYWORD
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.MODIFIER
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.STRING
import com.google.common.truth.Truth.assertThat
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
    assertThat(spots.sortedBy { it.start }).containsExactlyElementsIn(
      listOf(
        Spot(KEYWORD, 0, 2),
        Spot(DECIMAL_INTEGER, 8, 9),
      ).sortedBy { it.start }
    )
  }

  @Test
  fun `can extract hexadecimal numbers in var declarations`() {
    // given
    val code = """var i = 0xff"""

    // when
    val spots = extractSpots(code)

    // then
    assertThat(spots.sortedBy { it.start }).containsExactlyElementsIn(
      listOf(
        Spot(KEYWORD, 0, 2),
        Spot(HEXADECIMAL_INTEGER, 8, 11),
      ).sortedBy { it.start }
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
    assertThat(spots.sortedBy { it.start }).containsExactlyElementsIn(
      listOf(
        Spot(MODIFIER, 0, 6),
        Spot(MODIFIER, 8, 15),
        Spot(KEYWORD, 17, 19),
      ).sortedBy { it.start }
    )
  }

  @Test
  fun `can extract string declaration`() {
    // given
    val code = """val s = "hello""""

    // when
    val spots = extractSpots(code)

    // then
    assertThat(spots).containsExactlyElementsIn(
      listOf(
        Spot(KEYWORD, 0, 2),
        Spot(STRING, 8, 14),
      )
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
      Spot(KEYWORD, 0,2),
      Spot(DECIMAL_INTEGER, 14, 14),
      Spot(STRING, 19, 24),
      Spot(HEXADECIMAL_INTEGER, 27, 29),
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
    ).inOrder()
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
      Spot(MODIFIER, 23,30),
      Spot(KEYWORD, 32,34),
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
    assertThat(spots).containsExactlyElementsIn(
      listOf(
        Spot(COMMENT, 0, 8),
      )
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
    assertThat(spots).containsExactlyElementsIn(
      listOf(
        Spot(KEYWORD, 0, 6),
        Spot(KEYWORD, 29, 31),
        Spot(COMMA, 29, 31),
        Spot(STRING, 54, 68),
      )
    )
  }
}
