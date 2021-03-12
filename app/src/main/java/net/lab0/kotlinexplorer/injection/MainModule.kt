package net.lab0.kotlinexplorer.injection

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import net.lab0.kotlinexplorer.business.interactor.abstraction.GetAllChapters
import net.lab0.kotlinexplorer.business.interactor.abstraction.GetLessonsInProgress
import net.lab0.kotlinexplorer.business.interactor.abstraction.ReloadLessonFeedback
import net.lab0.kotlinexplorer.business.interactor.abstraction.RequestExtraLessons
import net.lab0.kotlinexplorer.business.interactor.abstraction.SaveLessonProgress
import net.lab0.kotlinexplorer.business.interactor.abstraction.SendLessonFeedback
import net.lab0.kotlinexplorer.business.interactor.abstraction.SendProblemReport
import net.lab0.kotlinexplorer.framework.presentation.common.JetpackExplorerFragmentFactory
import net.lab0.kotlinexplorer.framework.presentation.common.JetpackExplorerViewModelFactory
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@FlowPreview
@InstallIn(SingletonComponent::class)
@Module
object MainModule {
  @Singleton
  @Provides
  fun provideViewModelFactory(
    getAllChapters: GetAllChapters,
    getLessonsInProgress: GetLessonsInProgress,
    saveLessonProgress: SaveLessonProgress,
    sendLessonFeedback: SendLessonFeedback,
    reloadLessonFeedback: ReloadLessonFeedback,
    sendProblemReport: SendProblemReport,
    requestExtraLessons: RequestExtraLessons,
    firebaseAuth: FirebaseAuth,
  ): JetpackExplorerViewModelFactory =
    JetpackExplorerViewModelFactory(
      getAllChapters,
      getLessonsInProgress,
      saveLessonProgress,
      sendLessonFeedback,
      reloadLessonFeedback,
      sendProblemReport,
      requestExtraLessons,
      firebaseAuth,
    )

  @Singleton
  @Provides
  fun provideJetpackExplorerFragmentFactory(
    factory: JetpackExplorerViewModelFactory,
    requestExtraLessons: RequestExtraLessons
  ): JetpackExplorerFragmentFactory {
    return JetpackExplorerFragmentFactory(factory, requestExtraLessons)
  }
}
