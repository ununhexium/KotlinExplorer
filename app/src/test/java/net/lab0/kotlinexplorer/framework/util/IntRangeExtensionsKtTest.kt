package net.lab0.kotlinexplorer.framework.util

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

internal class IntRangeExtensionsKtTest {
  @Test
  fun `not overlapping 1`() {
    // given
    val a = 0..1
    val b = 2..3

    // when
    val split = a.overlapedBy(b)

    // then
    assertThat(split).isNull()
  }

  @Test
  fun `not overlapping 2`() {
    // given
    val a = 2..3
    val b = 0..1

    // when
    val split = a.overlapedBy(b)

    // then
    assertThat(split).isNull()
  }

  @Test
  fun `full overlap`() {
    // given
    val a = 1..2
    val b = 0..3

    // when
    val split = a.overlapedBy(b)

    // then
    assertThat(split).isEqualTo(a)
  }

  @Test
  fun `fully contained overlap`() {
    // given
    val a = 0..3
    val b = 1..2

    // when
    val split = a.overlapedBy(b)

    // then
    assertThat(split).isEqualTo(b)
  }

  @Test
  fun `intersect the lower part`() {
    // given
    val a = 1..3
    val b = 0..2

    // when
    val split = a.overlapedBy(b)

    // then
    assertThat(split).isEqualTo(1..2)
  }

  @Test
  fun `intersect exactly by the lower part`() {
    // given
    val a = 1..3
    val b = 0..3

    // when
    val split = a.overlapedBy(b)

    // then
    assertThat(split).isEqualTo(1..3)
  }

  @Test
  fun `intersect the higher part`() {
    // given
    val a = 1..3
    val b = 2..4

    // when
    val split = a.overlapedBy(b)

    // then
    assertThat(split).isEqualTo(2..3)
  }

  @Test
  fun `intersect exactly by the higher part`() {
    // given
    val a = 1..3
    val b = 1..4

    // when
    val split = a.overlapedBy(b)

    // then
    assertThat(split).isEqualTo(1..3)
  }

  @Test
  fun `intersect exactly`() {
    // given
    val a = 1..3
    val b = 1..3

    // when
    val split = a.overlapedBy(b)

    // then
    assertThat(split).isEqualTo(1..3)
  }

  @TestFactory
  fun `in range`(): Iterable<DynamicTest> =
      listOf<IntRange>(
          (1..1),
          (0..4),
          (2..4),
          (0..2),
      ).map{
        DynamicTest.dynamicTest(it.toString()) {
          // given
          val range = (1..3)

          // then
          assertThat(range.isInRange(it))
        }
      }
}