package com.example.jetpackcomposeexplorer.model.course

data class LessonData(
    override val pages: List<LessonPage>,
    override val dependencies: List<Lesson> = listOf(),
) : Lesson {

}