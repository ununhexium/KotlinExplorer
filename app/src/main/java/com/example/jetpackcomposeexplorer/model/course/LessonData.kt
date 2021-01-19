package com.example.jetpackcomposeexplorer.model.course

data class LessonData(
    override val id: Int,
    override val pages: List<LessonPage>,
    override val dependencies: List<Lesson> = listOf(),
) : Lesson {
  companion object {
    var id = 0

    fun new(
        pages: List<LessonPage>,
        dependencies: List<Lesson> = listOf(),
    ) =
        synchronized(this) {
          LessonData(
              id++,
              pages,
              dependencies
          )
        }
  }
}
