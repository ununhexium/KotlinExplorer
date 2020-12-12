package com.example.jetpackcomposeexplorer.code

class Highlights<H> {

  data class Spot<H>(val highlight: H, val start: Int, val end: Int)

  val spots = mutableListOf<Spot<H>>()

  fun add(type: H, start: Int, end: Int) {
    spots.add(Spot(type, start, end))
  }

  fun add(type: H, range: IntRange) {
    add(type, range.first, range.last)
  }

  fun add(highlights: Highlights<H>) {
    this.spots.addAll(highlights.spots)
  }

  fun nextHighlightAtOrAfter(index: Int): Spot<H>? {
    return spots
      .sortedBy { it.start }
      .dropWhile { it.start < index }
      .firstOrNull()
  }
}
