package com.example.jetpackcomposeexplorer.framework.presentation.components.frame

data class ChapterCardData(
    val title:String,
    val completion:Float,
    val lessons:List<LessonListItemData>,
)