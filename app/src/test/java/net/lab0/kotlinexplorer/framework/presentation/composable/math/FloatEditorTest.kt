package net.lab0.kotlinexplorer.framework.presentation.composable.math

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNegative
import assertk.assertions.isNotEqualTo
import assertk.assertions.isPositive
import assertk.assertions.isTrue
import net.lab0.kotlinexplorer.framework.presentation.composable.visualizer.floatingpoint.FloatEditor
import org.junit.jupiter.api.Test

internal class FloatEditorTest {

  @Test
  fun `can make negative`() {
    // given
    val a = 116f
    val aBits = a.toRawBits()
    val x = FloatEditor(a)

    // when
    val b = x.setNegative()
    val bBits = b.value.toRawBits()

    // then
    assertThat(b.value).isNegative()
    assertThat(aBits xor bBits).isEqualTo(FloatEditor.signMask)
  }

  @Test
  fun `can make positive`() {
    // given
    val a = -116f
    val aBits = a.toRawBits()
    val x = FloatEditor(a)

    // when
    val b = x.setPositive()
    val bBits = b.value.toRawBits()

    // then
    assertThat(b.value).isPositive()
    assertThat(aBits xor bBits).isEqualTo(FloatEditor.signMask)
  }

  @Test
  fun `can set the mantissa to 0`() {
    // given
    val a = 0.1f
    val x = FloatEditor(a)

    // when
    val b = x.setRawMantissa(0f)

    // then
    assertThat(b.mantissaBits).isEqualTo("0".repeat(23))
  }

  @Test
  fun `can set exponent to 127`() {
    // given
    val a = 1f
    val x = FloatEditor(a)

    // when
    val b = x.setExponent(254)

    // then
    assertThat(b.exponentBits).isEqualTo("11111110")
  }

  @Test
  fun `can set exponent to subnormal`() {
    // given
    val a = 1f
    val x = FloatEditor(a)

    // when
    val b = x.setExponent(1)

    // then
    assertThat(b.exponentBits).isEqualTo("00000001")
  }

  @Test
  fun `can set +inf`() {
    // given
    val a = 0f
    val x = FloatEditor(a)

    // when
    val b = x.setExponent(255)

    // then
    assertThat(b.exponentBits).isEqualTo("11111111")
  }

  @Test
  fun `can set -inf`() {
    // given
    val a = 0f
    val x = FloatEditor(a)

    // when
    val b = x.setExponent(255).setNegative()

    // then
    assertThat(b.exponentBits).isEqualTo("11111111")
  }

  @Test
  fun `can set NaN`() {
    // given
    val a = 0f
    val x = FloatEditor(a)

    // when
    val b = x.setExponent(255).setNegative().setRawMantissa(0.1f)

    // then
    // NaN != NaN is true
    assertThat(b.value.isNaN()).isTrue()
  }

  @Test
  fun `can get bit data for 0`() {
    // given
    val f = FloatEditor(0f)

    // then
    assertThat(f.signBit).isEqualTo("0")
    assertThat(f.exponentBits).isEqualTo("0".repeat(8))
    assertThat(f.mantissaBits).isEqualTo("0".repeat(23))
  }

  @Test
  fun `can get bit data for min`() {
    // given
    val f = FloatEditor(Float.MIN_VALUE)

    // then
    assertThat(f.signBit).isEqualTo("0")
    assertThat(f.exponentBits).isEqualTo("00000000")
    assertThat(f.mantissaBits).isEqualTo("0".repeat(22) + "1")
  }

  @Test
  fun `can get bit data for max`() {
    // given
    val f = FloatEditor(Float.MAX_VALUE)

    // then
    assertThat(f.signBit).isEqualTo("0")
    assertThat(f.exponentBits).isEqualTo("11111110")
    assertThat(f.mantissaBits).isEqualTo("1".repeat(23))
  }

  @Test
  fun `get exponent in decimal`() {

    assertThat(FloatEditor(0.1f).exponentDec).isEqualTo("-1")
    assertThat(FloatEditor(0f).exponentDec).isEqualTo("0")
    assertThat(FloatEditor(1f).exponentDec).isEqualTo("0")
    assertThat(FloatEditor(10f).exponentDec).isEqualTo("1")
    assertThat(FloatEditor(99f).exponentDec).isEqualTo("1")
    assertThat(FloatEditor(100f).exponentDec).isEqualTo("2")

    assertThat(FloatEditor(Float.MAX_VALUE).exponentDec).isEqualTo("38")
    assertThat(FloatEditor(Float.MIN_VALUE).exponentDec).isEqualTo("-45")
  }

  @Test
  fun `get mantissa in decimal`() {

    assertThat(FloatEditor(1.2345678f).mantissaDec).isEqualTo("1.2345678")
    assertThat(FloatEditor(0.1f).mantissaDec).isEqualTo("1.0000000")
    assertThat(FloatEditor(0f).mantissaDec).isEqualTo("0.0000000")
    assertThat(FloatEditor(1f).mantissaDec).isEqualTo("1.0000000")
    assertThat(FloatEditor(10f).mantissaDec).isEqualTo("1.0000000")
    assertThat(FloatEditor(99f).mantissaDec).isEqualTo("9.9000000")
    assertThat(FloatEditor(100f).mantissaDec).isEqualTo("1.0000000")

    assertThat(FloatEditor(Float.MAX_VALUE).mantissaDec).isEqualTo("3.4028235")
    assertThat(FloatEditor(Float.MIN_VALUE).mantissaDec).isEqualTo("1.4012985")
  }

  @Test
  fun `set mantissa by int 1`() {
    // given
    val a = 0f
    val x = FloatEditor(a)

    // when
    val z = x.setMantissa(0x7fffff)

    // then
    assertThat(z.mantissaBits).isEqualTo("1".repeat(23))
  }

  @Test
  fun `set mantissa by int 2`() {
    // given
    val a = 1f
    val x = FloatEditor(a)

    // when
    val z = x.setMantissa(0)

    // then
    assertThat(z.mantissaBits).isEqualTo("0".repeat(23))
  }

  @Test
  fun `set mantissa by int 3`() {
    // given
    val a = 0f
    val x = FloatEditor(a)

    // when
    val z = x.setMantissa(0x555555)

    // then
    assertThat(z.mantissaBits).isEqualTo("10".repeat(11) + "1")
  }

  @Test
  fun `set mantissa by int in NaN`() {
    // given
    val a = Float.NaN
    val x = FloatEditor(a)
    val original = x.mantissaBits

    // when
    val z = x.setMantissa(0x555555)
    val result = z.mantissaBits

    // then
    assertThat(z.mantissaBits).isEqualTo("10".repeat(11) + "1")
    assertThat(original).isNotEqualTo(result)
  }

  @Test
  fun `can get negative zero`() {
    // given
    val zero = 0f
    val e = FloatEditor(zero)

    // when
    val n = e.setNegative()
    val minZero = e.setNegative().value

    // then
    assertThat(n.signBit).isEqualTo("1")
  }

  @Test
  fun `can set negative when already negative`() {
    // given
    val zero = -1f
    val e = FloatEditor(zero)

    // when
    val n = e.setNegative()

    // then
    assertThat(n.value).isEqualTo(-1f)
  }
}
