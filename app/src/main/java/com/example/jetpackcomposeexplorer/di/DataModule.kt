package com.example.jetpackcomposeexplorer.di

import com.example.jetpackcomposeexplorer.business.data.course.abstraction.CourseRepository
import com.example.jetpackcomposeexplorer.business.data.course.implementation.CourseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {
  @Singleton
  @Provides
  fun provideFinder(): CourseRepository {
    return CourseRepositoryImpl()
  }
}
