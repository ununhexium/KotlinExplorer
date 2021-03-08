package net.lab0.kotlinexplorer.business.domain.parser

sealed class Block {
  abstract val range: IntRange

  data class PlaceholderBlock(
    override val range: IntRange,
    val index: Int,
  ) : Block()

  data class CodeBlock(
    override val range: IntRange,
  ) : Block()
}
