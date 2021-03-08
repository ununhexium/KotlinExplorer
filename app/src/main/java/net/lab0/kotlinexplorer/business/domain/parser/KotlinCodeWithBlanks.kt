package net.lab0.kotlinexplorer.business.domain.parser

interface KotlinCodeWithBlanks {

  companion object {
    /**
     * @param index Must be in the range [0,1000]
     */
    fun placeholder(@androidx.annotation.IntRange(from = -1, to = 999) index: Int = -1) =
      "/**ANSWER($index)**/"

    val ANSWER_REGEX = Regex("""/\*\*ANSWER\((-?[0-9]{1,3})\)\*\*/""")
  }

  /**
   * The raw input string, with the placeholder comments
   */
  val raw: String

  /**
   * The number of placeholders in the raw input.
   * If 2 place holders have the same identifier, they count as 2.
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

  fun parse(): List<Block>

  /**
   * Fills the code with the given placeholders and returns the indices
   * in the filled string where each placeholder is (after replacement or
   * keeping its placeholder value).
   *
   * If the fillings map is not complete, the placeholder with be kept as is.
   *
   * @return The positions of the indices in the code after replacing
   * the placeholders with the given fillings.
   */
  fun getRealStringIndices(
    fillings: Map<Int, String>
  ): Map<Int, List<IntRange>>
}
