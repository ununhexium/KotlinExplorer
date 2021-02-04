package com.example.jetpackcomposeexplorer.business.interactors

import com.example.jetpackcomposeexplorer.business.data.course.abstraction.Chapter
import com.example.jetpackcomposeexplorer.business.data.course.abstraction.CourseRepository
import com.example.jetpackcomposeexplorer.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetNextChapter(
    private val courseRepository: CourseRepository,
) {
  operator fun invoke(currentChapter: Chapter): Flow<DataState<Chapter?>> = flow {
    courseRepository.findNextChapter(currentChapter)
  }
}
