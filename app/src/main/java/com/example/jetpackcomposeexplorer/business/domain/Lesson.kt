package com.example.jetpackcomposeexplorer.business.domain

import com.example.jetpackcomposeexplorer.business.course.LessonData

data class Lesson(
    val lessonData: LessonData,
    val completed: Boolean,
    // TODO: add answers success rate
)
