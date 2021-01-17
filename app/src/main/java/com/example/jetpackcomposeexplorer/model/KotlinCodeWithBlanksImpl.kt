package com.example.jetpackcomposeexplorer.model

import com.example.jetpackcomposeexplorer.model.KotlinCodeWithBlanks.Companion.ANSWER_REGEX
import com.example.jetpackcomposeexplorer.model.KotlinCodeWithBlanks.Companion.placeholder

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
            code.replace(placeholder(index),
                fillings[index] ?: error("Expected id $index to be present in the fillings map."))
          }
}
