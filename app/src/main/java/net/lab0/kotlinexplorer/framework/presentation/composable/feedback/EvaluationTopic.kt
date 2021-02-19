package net.lab0.kotlinexplorer.framework.presentation.composable.feedback

data class EvaluationTopic(
    val topic: String,
    val options: List<String>,
    val onSelection: (Int?) -> Unit,
)
