package com.example.jetpackcomposeexplorer.model.course

open class ChapterImpl(
    override val id: String,
    override val title: String,
    override val description: String,
    override val lessons: List<Lesson>,
) : Chapter {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as ChapterImpl

    if (id != other.id) return false

    return true
  }

  override fun hashCode(): Int {
    return id.hashCode()
  }
}
