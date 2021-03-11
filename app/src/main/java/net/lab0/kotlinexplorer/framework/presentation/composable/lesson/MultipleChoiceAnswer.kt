package net.lab0.kotlinexplorer.framework.presentation.composable.lesson

data class MultipleChoiceAnswer(
  val id: Int,
  val text: String,
  val used: Boolean,
  val correct: Boolean? = null,
)
