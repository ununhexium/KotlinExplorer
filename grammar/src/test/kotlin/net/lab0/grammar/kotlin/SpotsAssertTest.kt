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
      asserter.hasAtLeastSpotsH(
        Spot("one", 1, 1),
        Spot("two", 3, 4),
        Spot("four", 6, 11),
        Spot("ten", 13, 23),
      )
    }

    // then
    assertThat(error).hasMessage(
      """
      |Missing spots:
      |  Spot(highlight=one, start=1, end=1)
      |  Spot(highlight=two, start=3, end=4)
      |  Spot(highlight=four, start=6, end=11)
      |  Spot(highlight=ten, start=13, end=23)
      |A_23456789B_23456789C_23456789
      |abcdefghijklmnopqrstuvwxyzABCD
      | o <> <four> <ten------>
    """.trimMargin()
    )
  }

  @Test
  fun `dont write after the end of the code`() {
    val asserter = SpotsAssert<String>(
      "",
      listOf()
    )

    // when
    val error = assertThrows(AssertionError::class.java) {
      asserter.hasAtLeastSpotsH(
        Spot("one", 1, 1),
      )
    }

    // then
    assertThat(error).hasMessage(
      """
      |Missing spots:
      |  Spot(highlight=one, start=1, end=1)
      |
      |
      |
    """.trimMargin()
    )
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
      asserter.hasAtLeastSpotsH(
        Spot("x", 1, 1),
        Spot("two", 3, 4),
        Spot("four", 6, 11),
        Spot("ten", 13, 23),
      )
    }

    // then
    assertThat(error).hasMessage(
      """
      |Missing spots:
      |  Spot(highlight=x, start=1, end=1)
      |  Spot(highlight=two, start=3, end=4)
      |  Spot(highlight=four, start=6, end=11)
      |  Spot(highlight=ten, start=13, end=23)
      |A_23456789B_23456789C_23456789D_23456789E
      |fun main() {↵  println("Hello, World!")↵}
      | x <> <four> <ten------>
    """.trimMargin()
    )
  }

  @Test
  fun `multiline assertion vertical display`() {
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
      asserter.hasAtLeastSpots(
        Spot("x", 1, 1),
        Spot("two", 3, 4),
        Spot("four", 6, 11),
        Spot("ten", 13, 23),
      )
    }

    // then
    assertThat(error).hasMessage(
      """
      |Missing spots:
      |  Spot(highlight=x, start=1, end=1)
      |  Spot(highlight=two, start=3, end=4)
      |  Spot(highlight=four, start=6, end=11)
      |  Spot(highlight=ten, start=13, end=23)
      |A_23456789B_23456789C_23456789D_23456789E
      |fun main() {↵  println("Hello, World!")↵}
      | ^ <> <----> <--------->
      | x t    f         t
      |   w    o         e
      |   o    u         n
      |        r
    """.trimMargin()
    )
  }

  @Test
  fun `multiline assertion vertical display with some extra and some missing`() {
    // given
    val asserter = SpotsAssert<String>(
      """
          |fun main() {
          |  println("Hello, World!")
          |}
        """.trimMargin(),
      listOf(
        Spot("two", 3, 4),
        Spot("55555", 26, 30)
      )
    )

    // when
    val error = assertThrows(AssertionError::class.java) {
      asserter.hasExactlySpots(
        Spot("x", 1, 1),
        Spot("two", 3, 4),
        Spot("four", 6, 11),
        Spot("ten", 13, 23),
      )
    }

    // then
    assertThat(error).hasMessage(
      """
      |Missing spots:
      |  Spot(highlight=x, start=1, end=1)
      |  Spot(highlight=four, start=6, end=11)
      |  Spot(highlight=ten, start=13, end=23)
      |A_23456789B_23456789C_23456789D_23456789E
      |fun main() {↵  println("Hello, World!")↵}
      | ^    <----> <--------->
      | x      f         t
      |        o         e
      |        u         n
      |        r
      |
      |Extraneous spots:
      |  Spot(highlight=55555, start=26, end=30)
      |A_23456789B_23456789C_23456789D_23456789E
      |fun main() {↵  println("Hello, World!")↵}
      |                          <--->
      |                            5
      |                            5
      |                            5
      |                            5
      |                            5
    """.trimMargin()
    )
  }

  @Test
  fun `dont show extraneous spots entry when there are none`() {
    // given
    val asserter = SpotsAssert(
      """
          |fun main() {
          |  println("Hello, World!")
          |}
        """.trimMargin(),
      listOf<Spot<String>>()
    )

    // when
    val error = assertThrows(AssertionError::class.java) {
      asserter.hasExactlySpots(
        Spot("two", 3, 4),
      )
    }

    // then
    assertThat(error).hasMessage(
      """
      |Missing spots:
      |  Spot(highlight=two, start=3, end=4)
      |A_23456789B_23456789C_23456789D_23456789E
      |fun main() {↵  println("Hello, World!")↵}
      |   <>
      |   t
      |   w
      |   o
      |
    """.trimMargin()
    )
  }

  @Test
  fun `can show missing spots entry located after the code`() {
    // given
    val asserter = SpotsAssert(
      "a",
      listOf(Spot("two", 5, 5),)
    )

    // when
    val error = assertThrows(AssertionError::class.java) {
      asserter.hasExactlySpots()
    }

    // then
    assertThat(error).hasMessage(
      """
      |Extraneous spots:
      |  Spot(highlight=two, start=5, end=5)
      |A
      |a
      |     ^
      |     t
      |     w
      |     o
      |
    """.trimMargin()
    )
  }
}