package com.example.jetpackcomposeexplorer.mvi

sealed class Resource<out T> {
  object EmptyLoadedResource : Resource<Nothing>()
  data class EmptyLoadedResourceWithMessage(val message: String) : Resource<Nothing>()
  class LoadedResource<T>(val resource: T) : Resource<T>()
  class LoadedResourceWithMessage<T>(val resource: T, val message: String) : Resource<T>()
  class FailedResource(val message: String) : Resource<Any?>()
  object Empty : Resource<Nothing>()
}
