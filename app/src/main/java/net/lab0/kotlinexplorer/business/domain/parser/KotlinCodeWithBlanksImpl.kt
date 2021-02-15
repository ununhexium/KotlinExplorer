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

  override fun split(): List<Pair<CodeType, IntRange>> {
    return split(0)
  }

  private fun split(
      offset: Int
  ): List<Pair<CodeType, IntRange>> {
    if (offset >= raw.length) return listOf()

    val placeholder = ANSWER_REGEX.find(raw, offset)
        ?: return listOf(CodeType.CODE to (offset until raw.length))

    return if (placeholder.range.first == offset) {
      listOf(CodeType.PLACEHOLDER to (placeholder.range.first .. placeholder.range.last))
    } else {
      listOf(
          CodeType.CODE to (offset until placeholder.range.first),
          CodeType.PLACEHOLDER to (placeholder.range.first .. placeholder.range.last),
      )
    } + split(
        placeholder.range.last + 1,
    )
  }

  override fun parse() =
      parse(0)

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
}
