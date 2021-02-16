package net.lab0.kotlinexplorer.framework.presentation.components.frame

data class ChapterCardData(
    val id:String,
    val title:String,
    val completion:Float,
    val lessons:List<LessonListItemData>,
)