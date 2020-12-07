package com.example.jetpackcomposeexplorer.code

class Highlights<H> {

  data class Spot<H>(val highlight: H, val start: Int, val end: Int)

  val spots = mutableListOf<Spot<H>>()

  fun addHighlight(type: H, start: Int, end: Int) {
    spots.add(Spot(type, start, end))
  }

  fun addHighlight(type: H, range: IntRange) {
    spots.add(Spot(type, range.first, range.last))
  }

  fun nextHighlightAtOrAfter(index: Int): Spot<H>? {
    return spots
        .sortedBy { it.start }
        .dropWhile { it.start < index }
        .firstOrNull()
  }
}
