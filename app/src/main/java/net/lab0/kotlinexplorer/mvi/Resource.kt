package net.lab0.kotlinexplorer.mvi

// TODO: wrap T do tell if the resturned data is clean (successful call) or dirty (failed somthing)
sealed class Resource<out T> {
  @Deprecated("Crappy architecture, needs rewrite")
  object EmptyLoadedResource : Resource<Unit>()
  data class EmptyLoadedResourceWithMessage(val message: String) : Resource<Nothing>()
  data class LoadedResource<T>(val resource: T) : Resource<T>()
  data class LoadedResourceWithMessage<T>(val resource: T, val message: String) : Resource<T>()
  data class FailedResource(val message: String) : Resource<Any?>()
}
