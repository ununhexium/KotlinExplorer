package net.lab0.kotlinexplorer.framework.util

import kotlin.math.max
import kotlin.math.min

/**
 * @return the part of `this` range that is overlapped by the `other` range,
 * or `null` if there is no overlap.
 */
fun IntRange.overlapedBy(other: IntRange): IntRange? {
  /**
   * A = this
   * B = other
   *
   * A:      <--->
   * B1:          <->    outside: higher
   * B2:  <->            outside: lower
   * A:      <--->
   * B3:    <----->      this fully contained
   * B4:      <->        other fully contained
   * A:      <--->
   * B5:    <->          other intersect lower part
   * B6:        <->      other intersect higher part
   * A:      <--->
   *
   */
  return when {
    // 1
    this.last < other.first -> null
    // 2
    other.last < this.first -> null
    // 3
    this.last < other.last && this.first > other.first -> this
    // 4
    this.first < other.first && this.last > other.last -> other
    // 5
    other.first <= this.first && other.last in this -> (this.first .. other.last)
    // 6
    this.first <= other.first && this.last in other -> (other.first .. this.last)

    else -> throw IllegalStateException("Missing condition in IntRange.overlap(IntRange)")
  }
}

/**
 * @return `true` if this range intersects with the `other` range.
 */
fun IntRange.isInRange(other: IntRange): Boolean {
  val gap = max(this.last, other.last) - min(this.first, other.first)
  val spans = (this.last - this.first) + (other.last - other.first)

  return gap < spans
}
