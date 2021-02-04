package com.example.jetpackcomposeexplorer.business.data.course.implementation

import com.example.jetpackcomposeexplorer.business.data.course.abstraction.Chapter
import com.example.jetpackcomposeexplorer.business.data.course.abstraction.LessonData

open class ChapterImpl(
    override val id: String,
    override val title: String,
    override val description: String,
    override val lessons: List<LessonData>,
) : Chapter
