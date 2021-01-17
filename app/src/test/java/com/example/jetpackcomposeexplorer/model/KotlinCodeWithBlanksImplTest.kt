package com.example.jetpackcomposeexplorer.model

import com.example.jetpackcomposeexplorer.model.KotlinCodeWithBlanks.Companion.placeholder
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class KotlinCodeWithBlanksImplTest {
  @Test
  fun `can count the placeholders`() {
    // given
    val cases = listOf(
        0 to "example",
        1 to "val ${placeholder()}",
        2 to "val ${placeholder(0)} = ${placeholder(1)}",
        3 to "val ${placeholder(0)} = ${placeholder(1)} + ${placeholder(2)}",
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
}