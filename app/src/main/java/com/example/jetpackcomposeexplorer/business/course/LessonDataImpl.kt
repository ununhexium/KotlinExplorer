package com.example.jetpackcomposeexplorer.business.course

open class LessonDataImpl(
    override val id:String,
    override val title:String,
    override val chapter: Chapter,
    override val pages: List<LessonPage>,
    override val dependencies: List<Prerequisite> = listOf(),
) : LessonData {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as LessonDataImpl

    if (id != other.id) return false

    return true
  }

  override fun hashCode(): Int {
    return id.hashCode()
  }
}
