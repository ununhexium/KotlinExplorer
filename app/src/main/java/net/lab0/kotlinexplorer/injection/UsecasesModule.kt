package net.lab0.kotlinexplorer.injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import net.lab0.kotlinexplorer.business.interactor.abstraction.GetAllChapters
import net.lab0.kotlinexplorer.business.interactor.abstraction.GetLessonFeedback
import net.lab0.kotlinexplorer.business.interactor.abstraction.GetLessonsInProgress
import net.lab0.kotlinexplorer.business.interactor.abstraction.GetLessonsInProgressCount
import net.lab0.kotlinexplorer.business.interactor.abstraction.RequestExtraLessons
import net.lab0.kotlinexplorer.business.interactor.abstraction.SaveLessonProgress
import net.lab0.kotlinexplorer.business.interactor.abstraction.SendLessonFeedback
import net.lab0.kotlinexplorer.business.interactor.abstraction.SendProblemReport
import net.lab0.kotlinexplorer.business.interactor.abstraction.SendProblemReportImpl
import net.lab0.kotlinexplorer.business.interactor.implementation.GetAllChaptersImpl
import net.lab0.kotlinexplorer.business.interactor.implementation.GetLessonFeedbackImpl
import net.lab0.kotlinexplorer.business.interactor.implementation.GetLessonsInProgressCountImpl
import net.lab0.kotlinexplorer.business.interactor.implementation.GetLessonsInProgressImpl
import net.lab0.kotlinexplorer.business.interactor.implementation.RequestExtraLessonsImpl
import net.lab0.kotlinexplorer.business.interactor.implementation.SaveLessonProgressImpl
import net.lab0.kotlinexplorer.business.interactor.implementation.SendLessonFeedbackImpl
import net.lab0.kotlinexplorer.business.persistence.abstraction.LessonProgressDataSource
import net.lab0.kotlinexplorer.framework.firebase.abstraction.ExtraContentService
import net.lab0.kotlinexplorer.framework.firebase.abstraction.LessonFeedbackService
import net.lab0.kotlinexplorer.framework.firebase.abstraction.ProblemReportService
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

  @Singleton
  @Provides
  fun provideGetLessonFeedback(service: LessonFeedbackService): GetLessonFeedback =
      GetLessonFeedbackImpl(service)

  @Singleton
  @Provides
  fun provideSendLessonFeedback(service: LessonFeedbackService): SendLessonFeedback =
      SendLessonFeedbackImpl(service)

  @Singleton
  @Provides
  fun provideSendProblemReport(problemReportService: ProblemReportService): SendProblemReport =
      SendProblemReportImpl(problemReportService)

  @Singleton
  @Provides
  fun provideRequestExtraLessons(
      extraContentService: ExtraContentService,
      dataSource: LessonProgressDataSource,
  ): RequestExtraLessons =
      RequestExtraLessonsImpl(extraContentService, dataSource)

}
