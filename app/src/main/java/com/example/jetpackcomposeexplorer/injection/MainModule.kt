package com.example.jetpackcomposeexplorer.injection

import com.example.jetpackcomposeexplorer.business.interactor.abstraction.GetAllChapters
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.GetLessonsInProgressCount
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.SaveLessonProgress
import com.example.jetpackcomposeexplorer.framework.presentation.common.JetpackExplorerFragmentFactory
import com.example.jetpackcomposeexplorer.framework.presentation.common.JetpackExplorerViewModelFactory
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
object MainModule {
  @Singleton
  @Provides
  fun provideViewModelFactory(
      getAllChapters: GetAllChapters,
      getLessonsInProgressCount: GetLessonsInProgressCount,
      saveLessonProgress: SaveLessonProgress,
  ): JetpackExplorerViewModelFactory =
      JetpackExplorerViewModelFactory(getAllChapters, getLessonsInProgressCount, saveLessonProgress)

  @Singleton
  @Provides
  fun provideJetpackExplorerFragmentFactory(
      factory: JetpackExplorerViewModelFactory,
  ): JetpackExplorerFragmentFactory {
    return JetpackExplorerFragmentFactory(factory)
  }
}
