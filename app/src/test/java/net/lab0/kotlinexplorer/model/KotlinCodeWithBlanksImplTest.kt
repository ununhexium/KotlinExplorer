package net.lab0.kotlinexplorer.model

import com.google.common.truth.Truth.assertThat
import net.lab0.kotlinexplorer.business.domain.parser.Block
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanksImpl
import org.junit.jupiter.api.Test
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

class KotlinCodeWithBlanksImplTest {
  @Test
  fun `can count the placeholders`() {
    // given
    val cases = listOf(
        0 to "example",
        1 to "val ${p()}",
        2 to "val ${p(0)} = ${p(1)}",
        3 to "val ${p(0)} = ${p(1)} + ${p(2)}",
        2 to "${p(0)}${p(0)}"
    )

    // then
    cases.forEach {
      assertThat(
          KotlinCodeWithBlanksImpl(it.second).placeholdersCount
      ).isEqualTo(
          it.first
      )
    }
  }

  @Test
  fun `can list the placeholder ids`() {
    // given
    val cases = listOf(
        listOf<Int>() to "example",
        listOf(-1) to "val ${p()}",
        listOf(0, 1) to "val ${p(0)} = ${p(1)}",
        listOf(0, 116, 2) to "val ${p(0)} = ${p(116)} + ${p(2)}",
    )

    // then
    cases.forEach {
      assertThat(
          KotlinCodeWithBlanksImpl(it.second).placeholderIds
      ).isEqualTo(
          it.first
      )
    }
  }

  @Test
  fun `can fill with an empty map (noop)`() {
    // given
    val code = "val ${p(0)} = ${p(1)}"

    // when
    val filled = KotlinCodeWithBlanksImpl(code).fill(mapOf())

    // then
    assertThat(filled).isEqualTo(code)
  }

  @Test
  fun `can fill the placeholders`() {
    // given
    val code = "val ${p(0)} = ${p(1)}"

    // when
    val filled = KotlinCodeWithBlanksImpl(code).fill(
        mapOf(
            0 to "i",
            1 to "116"
        )
    )

    // then
    assertThat(
        filled
    ).isEqualTo(
        "val i = 116"
    )
  }

  @Test
  fun `can fill a duplicated placeholder`() {
    // given
    val code = "if(${p(0)} == ${p(0)})"

    // when
    val filled = KotlinCodeWithBlanksImpl(code).fill(
        mapOf(0 to "true")
    )

    // then
    assertThat(
        filled
    ).isEqualTo(
        "if(true == true)"
    )
  }

  @Test
  fun `can parse the raw by placeholders and code, placeholders outside`() {
    // given
    //          /**ANSWER(0)**/a/**ANSWER(1)**/
    //          0123456789012345678901234567890
    val code = "${p(0)}a${p(1)}"

    // when
    val parse = KotlinCodeWithBlanksImpl(code).parse()

    // then
    val phl = p(0).length
    assertThat(parse).isEqualTo(
        listOf(
            Block.PlaceholderBlock(0 until phl, 0),
            Block.CodeBlock(phl .. phl),
            Block.PlaceholderBlock(phl + 1 until phl + 1 + phl, 1),
        )
    )
  }

  @Test
  fun `can parse the raw by placeholders and code, code outside`() {
    // given
    //          a/**ANSWER(0)**/b
    //          01234567890123456
    val code = "a${p(0)}b"

    // when
    val parse = KotlinCodeWithBlanksImpl(code).parse()

    // then
    val phl = p(0).length
    assertThat(parse).isEqualTo(
        listOf(
            Block.CodeBlock(0 .. 0),
            Block.PlaceholderBlock((1) until (1 + phl), 0),
            Block.CodeBlock((1 + phl) until (1 + phl + 1)),
        )
    )
  }

  @Test
  fun `can parse the raw by placeholders and code, consecutive placeholder`() {
    // given

    //          a/**ANSWER(0)**//**ANSWER(1)**/b
    //          01234567890123456789012345678901
    val code = "a${p(0)}${p(1)}b"

    // when
    val parse = KotlinCodeWithBlanksImpl(code).parse()

    // then
    val phl = p(0).length
    assertThat(parse).isEqualTo(
        listOf(
            Block.CodeBlock(0 until 1),
            Block.PlaceholderBlock(1 until phl + 1, 0),
            Block.PlaceholderBlock(phl + 1 until phl + 1 + phl, 1),
            Block.CodeBlock(phl + 1 + phl until phl + 1 + phl + 1),
        )
    )
  }

  @Test
  fun `can parse the raw by placeholders and code, no placeholder`() {
    // given
    val code = "a"

    // when
    val parse = KotlinCodeWithBlanksImpl(code).parse()

    // then
    assertThat(parse).isEqualTo(
        listOf(
            Block.CodeBlock(0 .. 0),
        )
    )
  }

  @Test
  fun `can parse the raw by placeholders and code, only one placeholder`() {
    // given
    val code = p(0)

    // when
    val parse = KotlinCodeWithBlanksImpl(code).parse()

    // then
    val phl = p(0).length
    assertThat(parse).isEqualTo(
        listOf(
            Block.PlaceholderBlock(0 until phl, 0),
        )
    )
  }

  @Test
  fun `can give the real string indices after code replacement`() {
    // given
    val code = "val ${p(0)} ${p(1)} ${p(2)}${p(3)}"
    val fillings = mapOf(
        0 to "alpha",
        2 to "116",
        3 to ";",
    )
    val withBlanks = KotlinCodeWithBlanksImpl(code)

    // when
    val ranges = withBlanks.getRealStringIndices(fillings)

    // then
    // 0         10        20
    // 012345678901234567890123456789
    // val alpha /**ANSWER(1)**/ 116;
    assertThat(ranges).isEqualTo(
        mapOf(
            0 to listOf(4 .. 8),
            1 to listOf(10 .. 24),
            2 to listOf(26 .. 28),
            3 to listOf(29 .. 29),
        )
    )
  }

  @Test
  fun `can give the real string indices after code replacement 2`() {
    // given
    val code = """${p(0)} main${p(1)}${p(2)} ${p(3)}
  // next steps
${p(4)}
"""
    val fillings = mapOf(
        0 to "fun",
        1 to "{",
        2 to "}",
        3 to "[",
        4 to "]",
    )
    val withBlanks = KotlinCodeWithBlanksImpl(code)

    // when
    val ranges = withBlanks.getRealStringIndices(fillings)

    // then
    // 0         10        20
    // 012345678901234567890123456789
    // fun main() {␤  // next steps␤}
    assertThat(ranges).isEqualTo(
        mapOf(
            0 to listOf(0 .. 2),
            1 to listOf(8 .. 8),
            2 to listOf(9 .. 9),
            3 to listOf(11 .. 11),
            4 to listOf(29 .. 29),
        )
    )
  }

  @Test
  fun `can give the real string indices with duplicated placeholder id`() {
    // given
    val code = """${p(0)}Hello${p(0)}"""
    val fillings = mapOf(0 to "\"")
    val withBlanks = KotlinCodeWithBlanksImpl(code)

    // when
    val ranges = withBlanks.getRealStringIndices(fillings)

    // then
    // 0123456
    // "Hello"
    assertThat(ranges).isEqualTo(
        mapOf(
            0 to listOf(0 .. 0, 6 .. 6),
        )
    )
  }
}
