package net.lab0.grammar.kotlin

import net.lab0.grammar.kotlin.Highlights.Spot
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertThrows
import org.junit.jupiter.api.Test

class SpotsAssertTest {
  @Test
  fun `can show spots`() {
    // given
    val asserter = SpotsAssert<String>(
        "abcdefghijklmnopqrstuvwxyzABCD",
        listOf()
    )

    // when
    val error = assertThrows(AssertionError::class.java) {
      asserter.hasSpots(
          Spot("one", 1, 1),
          Spot("two", 3, 4),
          Spot("four", 6, 11),
          Spot("ten", 13, 23),
      )
    }

    // then
    assertThat(error).hasMessage("""
      |Missing spots:
      |  Spot(highlight=one, start=1, end=1)
      |  Spot(highlight=two, start=3, end=4)
      |  Spot(highlight=four, start=6, end=11)
      |  Spot(highlight=ten, start=13, end=23)
      |A_23456789B_23456789C_23456789
      |abcdefghijklmnopqrstuvwxyzABCD
      | o <> <four> <ten------>
    """.trimMargin())
  }

  @Test
  fun `dont write after the end of the code`() {
    val asserter = SpotsAssert<String>(
        "",
        listOf()
    )

    // when
    val error = assertThrows(AssertionError::class.java) {
      asserter.hasSpots(
          Spot("one", 1, 1),
      )
    }

    // then
    assertThat(error).hasMessage("""
      |Missing spots:
      |  Spot(highlight=one, start=1, end=1)
      |
      |
      |
    """.trimMargin())
  }

  @Test
  fun `multiline assertion`() {
    // given
    val asserter = SpotsAssert<String>(
        """
          |fun main() {
          |  println("Hello, World!")
          |}
        """.trimMargin(),
        listOf()
    )

    // when
    val error = assertThrows(AssertionError::class.java) {
      asserter.hasSpots(
          Spot("x", 1, 1),
          Spot("two", 3, 4),
          Spot("four", 6, 11),
          Spot("ten", 13, 23),
      )
    }

    // then
    assertThat(error).hasMessage("""
      |Missing spots:
      |  Spot(highlight=x, start=1, end=1)
      |  Spot(highlight=two, start=3, end=4)
      |  Spot(highlight=four, start=6, end=11)
      |  Spot(highlight=ten, start=13, end=23)
      |A_23456789B_23456789C_23456789D_23456789E
      |fun main() {↵  println("Hello, World!")↵}
      | x <> <four> <ten------>
    """.trimMargin())
  }
}