package net.lab0.jetpackcomposeexplorer.business.domain

import net.lab0.jetpackcomposeexplorer.business.domain.KotlinCodeWithBlanks.Companion.ANSWER_REGEX
import net.lab0.jetpackcomposeexplorer.business.domain.KotlinCodeWithBlanks.Companion.placeholder

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

  override fun split(): List<Pair<KotlinCodeWithBlanks.CodeType, IntRange>> {
    return split(0)
  }

  private fun split(
      offset: Int
  ): List<Pair<KotlinCodeWithBlanks.CodeType, IntRange>> {
    if (offset >= raw.length) return listOf()

    val placeholder = ANSWER_REGEX.find(raw, offset)
        ?: return listOf(KotlinCodeWithBlanks.CodeType.CODE to (offset until raw.length))

    return if (placeholder.range.first == offset) {
      listOf(KotlinCodeWithBlanks.CodeType.PLACEHOLDER to (placeholder.range.first .. placeholder.range.last))
    } else {
      listOf(
          KotlinCodeWithBlanks.CodeType.CODE to (offset until placeholder.range.first),
          KotlinCodeWithBlanks.CodeType.PLACEHOLDER to (placeholder.range.first .. placeholder.range.last),
      )
    } + split(
        placeholder.range.last + 1,
    )
  }
}
