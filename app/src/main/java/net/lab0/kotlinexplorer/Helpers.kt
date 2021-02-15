package net.lab0.kotlinexplorer

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LiveData<T>.nonNullObserve(owner: LifecycleOwner, observer: (t: T) -> Unit) {
  this.observe(owner) { it?.let(observer) }
}

fun <T> MutableState<List<T>>.findAndEdit(e: T, change: (T) -> T) {
  val mutableAnswers = this.value.toMutableList()
  val index = mutableAnswers.indexOf(e)

  if (index == -1) return // nothing to edit

  val edited = mutableAnswers.removeAt(index)
  val changed = change(edited)
  mutableAnswers.add(index, changed)
  this.value = mutableAnswers
}

