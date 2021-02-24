package net.lab0.kotlinexplorer.injection

import net.lab0.kotlinexplorer.business.interactor.abstraction.GetAllChapters
import net.lab0.kotlinexplorer.business.interactor.abstraction.GetLessonsInProgress
import net.lab0.kotlinexplorer.business.interactor.abstraction.SaveLessonProgress
import net.lab0.kotlinexplorer.framework.presentation.common.JetpackExplorerFragmentFactory
import net.lab0.kotlinexplorer.framework.presentation.common.JetpackExplorerViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import net.lab0.kotlinexplorer.business.interactor.abstraction.SendLessonFeedback
import net.lab0.kotlinexplorer.business.interactor.abstraction.SendProblemReport
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
      sendLessonFeedback: SendLessonFeedback,
      sendProblemReport: SendProblemReport,
  ): JetpackExplorerViewModelFactory =
      JetpackExplorerViewModelFactory(
          getAllChapters,
          getLessonsInProgress,
          saveLessonProgress,
          sendLessonFeedback,
          sendProblemReport,
      )

  @Singleton
  @Provides
  fun provideJetpackExplorerFragmentFactory(
      factory: JetpackExplorerViewModelFactory,
  ): JetpackExplorerFragmentFactory {
    return JetpackExplorerFragmentFactory(factory)
  }
}
