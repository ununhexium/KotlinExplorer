package net.lab0.kotlinexplorer.injection

import net.lab0.kotlinexplorer.business.interactor.abstraction.GetAllChapters
import net.lab0.kotlinexplorer.business.interactor.abstraction.GetLessonsInProgress
import net.lab0.kotlinexplorer.business.interactor.abstraction.GetLessonsInProgressCount
import net.lab0.kotlinexplorer.business.interactor.abstraction.SaveLessonProgress
import net.lab0.kotlinexplorer.business.interactor.implementation.SaveLessonProgressImpl
import net.lab0.kotlinexplorer.business.interactor.implementation.GetAllChaptersImpl
import net.lab0.kotlinexplorer.business.interactor.implementation.GetLessonsInProgressCountImpl
import net.lab0.kotlinexplorer.business.interactor.implementation.GetLessonsInProgressImpl
import net.lab0.kotlinexplorer.business.persistence.abstraction.LessonProgressDataSource
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
