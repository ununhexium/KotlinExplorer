package net.lab0.jetpackcomposeexplorer.framework.presentation.components.frame

data class LessonListItemData(
    val id:String,
    val title: String,
    val completed: Boolean,
    val progress: Float? = null,
)
