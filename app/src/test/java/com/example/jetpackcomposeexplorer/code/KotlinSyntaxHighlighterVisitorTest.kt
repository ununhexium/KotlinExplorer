package com.example.jetpackcomposeexplorer.code

import com.example.jetpackcomposeexplorer.code.Highlights.Spot
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.ANNOTATION
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.CLASS_DECLARATION
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.CLASS_IDENTIFIER
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.CLASS_PARAMETER
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.COMMA
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.FUNCTION_DECLARATION
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.KEYWORD
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.MODIFIER
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.STRING
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.VAL
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.VAR
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.VISIBILITY_MODIFIER
import com.google.common.truth.Truth.assertThat
import net.lab0.grammar.kotlin.parseKotlin
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.junit.Test


class KotlinSyntaxHighlighterVisitorTest {

  private fun extractSpots(code: String): List<Spot<KotlinHighlight>> {
    val parser = parseKotlin(code)

    val walker = ParseTreeWalker()
    val kotlinListener = KotlinSyntaxHighlighterVisitor()
    walker.walk(kotlinListener, parser.kotlinFile())
    val spots = kotlinListener.highlights.spots
    return spots
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

    val parser = parseKotlin(code)

    val walker = ParseTreeWalker()
    val kotlinListener = KotlinSyntaxHighlighterVisitor()

    // when
    walker.walk(kotlinListener, parser.kotlinFile())

    // then
    assertThat(kotlinListener.highlights.spots).isEqualTo(
        listOf(
            Spot(KEYWORD, 0, 6),
            Spot(KEYWORD, 29, 31),
            Spot(FUNCTION_DECLARATION, 33, 36),
            Spot(COMMA, 29, 31),
            Spot(STRING, 54, 68),
        )
    )
  }

  @Test
  fun `can extract package`() {
    // given
    val code = "data class Data(val a:Int, var b:String)"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(spots).containsAtLeast(
        Spot(MODIFIER, 0, 3),
        Spot(CLASS_DECLARATION, 5, 9),
        Spot(CLASS_IDENTIFIER, 11, 14),
        Spot(VAL, 16, 18),
        Spot(CLASS_PARAMETER, 20, 20),
        Spot(COMMA, 25, 25),
        Spot(VAR, 27, 29),
        Spot(CLASS_PARAMETER, 31, 31),
    )
  }

  @Test
  fun `can extract annotation`() {
    // given
    val code = "@A enum class E"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(spots).containsAtLeast(
        Spot(ANNOTATION, 0, 1),
        Spot(MODIFIER, 3, 6),
        Spot(CLASS_DECLARATION, 8, 12),
        Spot(CLASS_IDENTIFIER, 14, 14),
    )
  }

  @Test
  fun `can extract functions`() {
    // given
    val code = "private override fun f(){}"

    // when
    val spots = extractSpots(code)

    // then
    assertThat(spots.sortedBy { it.start }).containsAtLeastElementsIn(
        listOf(
            Spot(MODIFIER, 0, 6),
            Spot(MODIFIER, 8, 15),
            Spot(KEYWORD, 17, 19),
            Spot(FUNCTION_DECLARATION, 21, 21),
        ).sortedBy { it.start }
    )
  }
}
