package com.example.jetpackcomposeexplorer.business.course

open class ChapterImpl(
    override val id: String,
    override val title: String,
    override val description: String,
    override val lessons: List<LessonData>,
) : Chapter
