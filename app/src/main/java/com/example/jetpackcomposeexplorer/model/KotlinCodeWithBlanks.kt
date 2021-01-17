package com.example.jetpackcomposeexplorer.model

import androidx.annotation.FloatRange
import androidx.annotation.IntRange

interface KotlinCodeWithBlanks {

  companion object {
    /**
     * @param index Must be in the range [0,1000]
     */
    fun placeholder(@IntRange(from = -1, to = 999) index: Int = -1) = "/**ANSWER($index)**/"
    val ANSWER_REGEX = Regex("""/\*\*ANSWER\((-?[0-9]{1,3})\)\*\*/""")
  }

  /**
   * The raw input string, with the placeholder comments
   */
  val raw: String

  /**
   * The number of placeholders in the raw input
   */
  val placeholdersCount: Int

  /**
   * The IDS of the placeholders.
   */
  val placeholderIds: List<Int>

  /**
   * Replaces the placeholders with real values.
   * Each placeholder has its own index.
   * If the fillings map doesn't contain the index for a placeholder,
   * the placeholder comment is preserved.
   *
   * @return The raw input with placeholders replaced by the values contained in the map.
   */
  fun fill(fillings: Map<Int, String>): String
}
