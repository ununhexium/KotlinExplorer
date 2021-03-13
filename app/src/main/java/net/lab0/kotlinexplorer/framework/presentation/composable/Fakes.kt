package net.lab0.kotlinexplorer.framework.presentation.composable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.lab0.kotlinexplorer.business.course.data.kotlin.KOTLIN
import net.lab0.kotlinexplorer.business.domain.Chapter
import net.lab0.kotlinexplorer.business.domain.LessonProgress
import net.lab0.kotlinexplorer.business.interactor.abstraction.GetAllChapters
import net.lab0.kotlinexplorer.business.interactor.abstraction.GetLessonsInProgress
import net.lab0.kotlinexplorer.mvi.Resource


fun fakeGetAllChapters() = object : GetAllChapters {
  override fun invoke(): Flow<Resource<List<Chapter>>> = flow {
    emit(Resource.LoadedResource(KOTLIN))
  }
}

fun fakeGetLessonsInProgress(list: List<LessonProgress> = listOf()):
    GetLessonsInProgress {
  return object : GetLessonsInProgress {
    override fun invoke(): Flow<Resource<List<LessonProgress>>> = flow {
      emit(Resource.LoadedResource(list))
    }
  }
}

fun <T> fakeFactory(f: () -> T): ViewModelProvider.Factory {
  return object : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
      return f as T
    }
  }
}
