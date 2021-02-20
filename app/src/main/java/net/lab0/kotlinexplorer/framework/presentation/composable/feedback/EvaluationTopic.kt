package net.lab0.kotlinexplorer.framework.presentation.composable.feedback

data class EvaluationTopic<T>(
    val topic: String,
    val options: List<T>,
    val onSelection: (Int?) -> Unit,
) where T : Any {
  companion object {
    fun <T> empty() where T : Any = EvaluationTopic("", listOf<T>()) {}
  }

  fun <X> map(toString: (T) -> String): EvaluationTopic<String> where X : Any {
    return EvaluationTopic(
        this.topic,
        this.options.map { toString(it) },
        this.onSelection
    )
  }
}
