package com.example.jetpackcomposeexplorer.di

import androidx.fragment.app.FragmentFactory
import com.example.jetpackcomposeexplorer.business.course.abstraction.CourseRepository
import com.example.jetpackcomposeexplorer.framework.datasource.service.LessonDaoService
import com.example.jetpackcomposeexplorer.framework.presentation.ui.codequestion.QuizFragmentFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(ApplicationComponent::class)
object FragmentModule {

  @Singleton
  @Provides
  fun provideQuizFragmentFactory(
      courseRepository: CourseRepository,
      lessonDaoService: LessonDaoService,
  ): FragmentFactory {
    return QuizFragmentFactory(courseRepository, lessonDaoService)
  }
}

