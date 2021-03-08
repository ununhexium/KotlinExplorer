package net.lab0.kotlinexplorer.business.domain.parser

import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.ANSWER_REGEX
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder

class KotlinCodeWithBlanksImpl(override val raw: String) : KotlinCodeWithBlanks {
  override val placeholdersCount by lazy {
    ANSWER_REGEX.findAll(raw).count()
  }

  override val placeholderIds by lazy {
    ANSWER_REGEX.findAll(raw).mapNotNull {
      it.groups[1]?.value?.toInt()
    }.toList()
  }

  override fun fill(fillings: Map<Int, String>) =
    placeholderIds
      .filter { it in fillings.keys }
      .fold(raw) { code, index ->
        code.replace(
          placeholder(index),
          fillings[index] ?: error("Expected id $index to be present in the fillings map.")
        )
      }

  override fun parse() =
    if (raw.isEmpty()) {
      listOf(Block.CodeBlock(0 until 0))
    } else {
      parse(0)
    }

  private fun parse(
    offset: Int
  ): List<Block> {
    if (offset >= raw.length) return listOf()

    val placeholder = ANSWER_REGEX.find(raw, offset)
      ?: return listOf(Block.CodeBlock(offset until raw.length))

    val indexAsInt = placeholder.groups[1]!!.value.toInt()

    return if (placeholder.range.first == offset) {
      listOf(
        Block.PlaceholderBlock(
          placeholder.range.first .. placeholder.range.last,
          indexAsInt,
        )
      )
    } else {
      listOf(
        Block.CodeBlock(offset until placeholder.range.first),
        Block.PlaceholderBlock(
          placeholder.range.first .. placeholder.range.last,
          indexAsInt,
        ),
      )
    } + parse(
      placeholder.range.last + 1,
    )
  }

  override fun getRealStringIndices(
    fillings: Map<Int, String>
  ): Map<Int, List<IntRange>> {
    val result = mutableMapOf<Int, MutableList<IntRange>>()

    // complement the missing IDs with the placeholder's value
    val refillings = placeholderIds.map { id ->
      if (id in fillings.keys) {
        id to (fillings[id] ?: throw IllegalStateException(
          "Can't happen, was filtered 1 line above"
        ))
      } else {
        id to placeholder(id)
      }
    }.toMap()

    placeholderIds
      .fold(raw) { code, index ->
        val placeholderStart = code.indexOf(placeholder(index))
        val replacement = refillings[index] ?: throw IllegalStateException(
          "Must not happen because the indices have been re-filled."
        )
        val end = placeholderStart + replacement.length

        result.computeIfAbsent(index) {
          mutableListOf()
        }.add(placeholderStart until end)

        code.replaceFirst(
          placeholder(index),
          replacement
        )
      }

    return result
  }
}
