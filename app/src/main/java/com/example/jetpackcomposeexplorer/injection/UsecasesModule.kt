package com.example.jetpackcomposeexplorer.injection

import com.example.jetpackcomposeexplorer.business.interactor.abstraction.GetLessonsInProgress
import com.example.jetpackcomposeexplorer.business.interactor.implementation.GetLessonsInProgressImpl
import com.example.jetpackcomposeexplorer.business.persistence.abstraction.LessonProgressDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@FlowPreview
@InstallIn(ApplicationComponent::class)
@Module
object UsecasesModule {
  @Singleton
  @Provides
  fun provideGetLessonsInProgress(dao: LessonProgressDataSource): GetLessonsInProgress {
    return GetLessonsInProgressImpl(dao)
  }
}
