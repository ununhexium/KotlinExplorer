package net.lab0.kotlinexplorer.framework.presentation.composable.visualizer.floatingpoint

import java.util.*

/**
 * Workaround when working with NaN and compose refuses
 * to update a value that "didn't change",
 * when working with different NaN values.
 */
data class DatedFloat(
  val float: Float,
  val editor: FloatEditor = FloatEditor(float),
  val date: Date = Date()
)
