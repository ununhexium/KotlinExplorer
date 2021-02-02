package com.example.jetpackcomposeexplorer.business.domain

import com.example.jetpackcomposeexplorer.business.course.abstraction.LessonData

data class Exercise(
    val lessonData: LessonData,
    val completed: Boolean,
)
