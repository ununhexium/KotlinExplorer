package net.lab0.kotlinexplorer.framework.presentation.composable.chapter

data class LessonListItemData(
    val id:String,
    val title: String,
    val completed: Boolean,
    val highlighted: Boolean = false,
    val progress: Float? = null,
)
