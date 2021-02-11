package com.example.jetpackcomposeexplorer.mvi

sealed class Resource<T> {
  class LoadedResource<T>(val resource: T) : Resource<T>()
  class FailedResource(val message: String) : Resource<Any?>()
  object Empty : Resource<Any?>()
}
