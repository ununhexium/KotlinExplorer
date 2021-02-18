package net.lab0.grammar.kotlin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class VerticalWriterKtTest {
  @Test
  fun `write nothing`() {
    // given
    val strings = mapOf<Int,String>()

    // when
    val vertical = verticalWriter(strings)

    // then
    assertThat(vertical).isEqualTo("")
  }

  @Test
  fun `write foo`() {
    // given
    val strings = mapOf(0 to "foo")

    // when
    val vertical = verticalWriter(strings)

    // then
    assertThat(vertical).isEqualTo(
        """
          |f
          |o
          |o
        """.trimMargin()
    )
  }

  @Test
  fun `write foo and tata`() {
    // given
    val strings = mapOf(0 to "foo", 3 to "tata")

    // when
    val vertical = verticalWriter(strings)

    // then
    assertThat(vertical).isEqualTo(
        """
          |f  t
          |o  a
          |o  t
          |   a
        """.trimMargin()
    )
  }

  @Test
  fun `write foo and tata bar`() {
    // given
    val strings = mapOf(0 to "foo", 3 to "tata", 5 to "bar")

    // when
    val vertical = verticalWriter(strings)

    // then
    assertThat(vertical).isEqualTo(
        """
          |f  t b
          |o  a a
          |o  t r
          |   a
        """.trimMargin()
    )
  }
}