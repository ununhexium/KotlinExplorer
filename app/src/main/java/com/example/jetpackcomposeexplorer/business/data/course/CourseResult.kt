package com.example.jetpackcomposeexplorer.business.data.course

sealed class CourseResult<out T> where T:Any{

    data class Success<out T>(val value: T): CourseResult<T>() where T:Any

    data class GenericError(
        val errorMessage: String? = null
    ): CourseResult<Nothing>()
}
