package com.example.jetpackcomposeexplorer.business.domain

import com.example.jetpackcomposeexplorer.business.data.course.abstraction.LessonData

data class Exercise(
    val lessonData: LessonData,
    val completed: Boolean,
)
