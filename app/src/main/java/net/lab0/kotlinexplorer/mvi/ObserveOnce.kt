package net.lab0.kotlinexplorer.mvi

class ObserveOnce<T>(
  private val data: T,
  private var observed: Boolean = false
) {
  /**
   * Executes `action` only if the variable wasn't already looked at.
   */
  fun observe(action: (T) -> Unit) {
    if (!observed) {
      observed = true
      action(data)
    }
  }
}
