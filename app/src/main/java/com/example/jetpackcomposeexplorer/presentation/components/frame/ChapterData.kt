package com.example.jetpackcomposeexplorer.presentation.components.frame

data class ChapterData(
    val title:String,
    val completion:Float,
    val lessons:List<LessonData>,
)