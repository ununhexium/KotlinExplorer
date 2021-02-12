package net.lab0.jetpackcomposeexplorer.model

import net.lab0.jetpackcomposeexplorer.business.domain.KotlinCodeWithBlanks
import net.lab0.jetpackcomposeexplorer.business.domain.KotlinCodeWithBlanks.Companion.placeholder
import net.lab0.jetpackcomposeexplorer.business.domain.KotlinCodeWithBlanksImpl
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class KotlinCodeWithBlanksImplTest {
  @Test
  fun `can count the placeholders`() {
    // given
    val cases = listOf(
        0 to "example",
        1 to "val ${placeholder()}",
        2 to "val ${placeholder(0)} = ${placeholder(1)}",
        3 to "val ${placeholder(0)} = ${placeholder(1)} + ${placeholder(2)}",
        2 to "${placeholder(0)}${placeholder(0)}"
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
        listOf(-1) to "val ${placeholder()}",
        listOf(0, 1) to "val ${placeholder(0)} = ${placeholder(1)}",
        listOf(0, 116, 2) to "val ${placeholder(0)} = ${placeholder(116)} + ${placeholder(2)}",
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
    val code = "val ${placeholder(0)} = ${placeholder(1)}"

    // when
    val filled = KotlinCodeWithBlanksImpl(code).fill(
        mapOf()
    )

    // then
    assertThat(
        filled
    ).isEqualTo(
        code
    )
  }

  @Test
  fun `can fill the placeholders`() {
    // given
    val code = "val ${placeholder(0)} = ${placeholder(1)}"

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
    val code = "if(${placeholder(0)} == ${placeholder(0)})"

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
  fun `can split the raw by placeholders and code, placeholders outside`() {
    // given
    //          /**ANSWER(0)**/a/**ANSWER(1)**/
    //          0123456789012345678901234567890
    val code = "${placeholder(0)}a${placeholder(1)}"

    // when
    val split = KotlinCodeWithBlanksImpl(code).split()

    // then
    val phl = placeholder(0).length
    assertThat(split).isEqualTo(
        listOf(
            KotlinCodeWithBlanks.CodeType.PLACEHOLDER to (0 until phl),
            KotlinCodeWithBlanks.CodeType.CODE to (phl .. phl),
            KotlinCodeWithBlanks.CodeType.PLACEHOLDER to (phl + 1 until phl + 1 + phl),
        )
    )
  }

  @Test
  fun `can split the raw by placeholders and code, code outside`() {
    // given
    //          a/**ANSWER(0)**/b
    //          01234567890123456
    val code = "a${placeholder(0)}b"

    // when
    val split = KotlinCodeWithBlanksImpl(code).split()

    // then
    val phl = placeholder(0).length
    assertThat(split).isEqualTo(
        listOf(
            KotlinCodeWithBlanks.CodeType.CODE to (0 .. 0),
            KotlinCodeWithBlanks.CodeType.PLACEHOLDER to ((1) until (1 + phl)),
            KotlinCodeWithBlanks.CodeType.CODE to ((1 + phl) until (1 + phl + 1)),
        )
    )
  }

  @Test
  fun `can split the raw by placeholders and code, consecutive placeholder`() {
    // given

    //          a/**ANSWER(0)**//**ANSWER(1)**/b
    //          01234567890123456789012345678901
    val code = "a${placeholder(0)}${placeholder(1)}b"

    // when
    val split = KotlinCodeWithBlanksImpl(code).split()

    // then
    val phl = placeholder(0).length
    assertThat(split).isEqualTo(
        listOf(
            KotlinCodeWithBlanks.CodeType.CODE to (0 until 1),
            KotlinCodeWithBlanks.CodeType.PLACEHOLDER to (1 until phl + 1),
            KotlinCodeWithBlanks.CodeType.PLACEHOLDER to (phl + 1 until phl + 1 + phl),
            KotlinCodeWithBlanks.CodeType.CODE to (phl + 1 + phl until phl + 1 + phl + 1),
        )
    )
  }

  @Test
  fun `can split the raw by placeholders and code, no placeholder`() {
    // given
    val code = "a"

    // when
    val split = KotlinCodeWithBlanksImpl(code).split()

    // then
    assertThat(split).isEqualTo(
        listOf(
            KotlinCodeWithBlanks.CodeType.CODE to (0 .. 0),
        )
    )
  }

  @Test
  fun `can split the raw by placeholders and code, only one placeholder`() {
    // given
    val code = placeholder(0)

    // when
    val split = KotlinCodeWithBlanksImpl(code).split()

    // then
    val phl = placeholder(0).length
    assertThat(split).isEqualTo(
        listOf(
            KotlinCodeWithBlanks.CodeType.PLACEHOLDER to (0 until phl),
        )
    )
  }
}
