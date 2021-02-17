package net.lab0.kotlinexplorer.mvi

sealed class Resource<out T> {
  object EmptyLoadedResource : Resource<Nothing>()
  data class EmptyLoadedResourceWithMessage(val message: String) : Resource<Nothing>()
  data class LoadedResource<T>(val resource: T) : Resource<T>()
  data class LoadedResourceWithMessage<T>(val resource: T, val message: String) : Resource<T>()
  data class FailedResource(val message: String) : Resource<Any?>()
  object Empty : Resource<Nothing>()
}
