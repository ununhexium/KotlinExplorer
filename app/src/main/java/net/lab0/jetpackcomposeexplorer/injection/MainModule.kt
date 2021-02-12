package net.lab0.jetpackcomposeexplorer.injection

import net.lab0.jetpackcomposeexplorer.business.interactor.abstraction.GetAllChapters
import net.lab0.jetpackcomposeexplorer.business.interactor.abstraction.GetLessonsInProgress
import net.lab0.jetpackcomposeexplorer.business.interactor.abstraction.SaveLessonProgress
import net.lab0.jetpackcomposeexplorer.framework.presentation.common.JetpackExplorerFragmentFactory
import net.lab0.jetpackcomposeexplorer.framework.presentation.common.JetpackExplorerViewModelFactory
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
      getLessonsInProgress: GetLessonsInProgress,
      saveLessonProgress: SaveLessonProgress,
  ): JetpackExplorerViewModelFactory =
      JetpackExplorerViewModelFactory(getAllChapters, getLessonsInProgress, saveLessonProgress)

  @Singleton
  @Provides
  fun provideJetpackExplorerFragmentFactory(
      factory: JetpackExplorerViewModelFactory,
  ): JetpackExplorerFragmentFactory {
    return JetpackExplorerFragmentFactory(factory)
  }
}
