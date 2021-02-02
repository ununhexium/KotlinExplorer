package com.example.jetpackcomposeexplorer.business.course.implementation

import com.example.jetpackcomposeexplorer.business.course.abstraction.Chapter
import com.example.jetpackcomposeexplorer.business.course.abstraction.LessonData

open class ChapterImpl(
    override val id: String,
    override val title: String,
    override val description: String,
    override val lessons: List<LessonData>,
) : Chapter
