package com.example.jetpackcomposeexplorer.injection

import com.example.jetpackcomposeexplorer.business.interactor.abstraction.GetAllChapters
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.GetLessonsInProgress
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.GetLessonsInProgressCount
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.SaveLessonProgress
import com.example.jetpackcomposeexplorer.business.interactor.implementation.SaveLessonProgressImpl
import com.example.jetpackcomposeexplorer.business.interactor.implementation.GetAllChaptersImpl
import com.example.jetpackcomposeexplorer.business.interactor.implementation.GetLessonsInProgressCountImpl
import com.example.jetpackcomposeexplorer.business.interactor.implementation.GetLessonsInProgressImpl
import com.example.jetpackcomposeexplorer.business.persistence.abstraction.LessonProgressDataSource
import com.example.jetpackcomposeexplorer.framework.db.mappers.LessonProgressMapperFromEntity
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
  fun provideGetLessonsInProgressCount(dataSource: LessonProgressDataSource): GetLessonsInProgressCount {
    return GetLessonsInProgressCountImpl(dataSource)
  }

  @Singleton
  @Provides
  fun provideGetAllChapters(): GetAllChapters {
    return GetAllChaptersImpl()
  }

  @Singleton
  @Provides
  fun provideSaveLessonProgress(dataSource: LessonProgressDataSource): SaveLessonProgress =
      SaveLessonProgressImpl(dataSource)

  @Singleton
  @Provides
  fun provideGetLessonsInProgress(dataSource: LessonProgressDataSource): GetLessonsInProgress =
    GetLessonsInProgressImpl(dataSource)
}
