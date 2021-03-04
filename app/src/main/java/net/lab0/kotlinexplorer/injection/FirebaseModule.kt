package net.lab0.kotlinexplorer.injection

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import net.lab0.kotlinexplorer.business.domain.extracontent.ExtraContentRequest
import net.lab0.kotlinexplorer.business.domain.feedback.LessonFeedback
import net.lab0.kotlinexplorer.business.domain.problemreport.ProblemReport
import net.lab0.kotlinexplorer.framework.firebase.abstraction.ExtraContentService
import net.lab0.kotlinexplorer.framework.firebase.abstraction.LessonFeedbackService
import net.lab0.kotlinexplorer.framework.firebase.abstraction.ProblemReportService
import net.lab0.kotlinexplorer.framework.firebase.implementation.ExtraContentServiceImpl
import net.lab0.kotlinexplorer.framework.firebase.implementation.LessonFeedbackServiceImpl
import net.lab0.kotlinexplorer.framework.firebase.implementation.ProblemReportServiceImpl
import net.lab0.kotlinexplorer.framework.firebase.mappers.ExtraContentRequestFromDomain
import net.lab0.kotlinexplorer.framework.firebase.mappers.ExtraContentRequestToDomain
import net.lab0.kotlinexplorer.framework.firebase.mappers.FeedbackDocumentFromDomain
import net.lab0.kotlinexplorer.framework.firebase.mappers.FeedbackDocumentToDomain
import net.lab0.kotlinexplorer.framework.firebase.mappers.ProblemReportDocumentFromDomain
import net.lab0.kotlinexplorer.framework.firebase.mappers.ProblemReportDocumentToDomain
import net.lab0.kotlinexplorer.framework.firebase.model.ExtraContentRequestDocument
import net.lab0.kotlinexplorer.framework.firebase.model.FeedbackDocument
import net.lab0.kotlinexplorer.framework.firebase.model.ProblemReportDocument
import net.lab0.kotlinexplorer.framework.util.FromDomain
import net.lab0.kotlinexplorer.framework.util.ToDomain
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object FirebaseModule {
  @Singleton
  @Provides
  fun provideFirebaseAuth(): FirebaseAuth =
      FirebaseAuth.getInstance()

  @Singleton
  @Provides
  fun provideFeedbackFromDomain(): FromDomain<FeedbackDocument, LessonFeedback> =
      FeedbackDocumentFromDomain()

  @Singleton
  @Provides
  fun provideFeedbackToDomain(): ToDomain<FeedbackDocument, LessonFeedback> =
      FeedbackDocumentToDomain()

  @Singleton
  @Provides
  fun provideProblemReportFromDomain(): FromDomain<ProblemReportDocument, ProblemReport> =
      ProblemReportDocumentFromDomain()

  @Singleton
  @Provides
  fun provideProblemReportToDomain(): ToDomain<ProblemReportDocument, ProblemReport> =
      ProblemReportDocumentToDomain()

  @Singleton
  @Provides
  fun provideLessonFeedbackService(
      firestore: FirebaseFirestore,
      fromDomain: FromDomain<FeedbackDocument, LessonFeedback>,
      toDomain: ToDomain<FeedbackDocument, LessonFeedback>,
  ): LessonFeedbackService =
    LessonFeedbackServiceImpl(
            firestore,
            fromDomain,
            toDomain,
    )

  @Singleton
  @Provides
  fun provideProblemReportService(
      firebaseAuth: FirebaseAuth,
      firestore: FirebaseFirestore,
      fromDomain: FromDomain<ProblemReportDocument, ProblemReport>,
  ): ProblemReportService =
    ProblemReportServiceImpl(
        firebaseAuth,
            firestore,
            fromDomain,
    )

  @Singleton
  @Provides
  fun provideExtraContentRequestFromDomain(): FromDomain<ExtraContentRequestDocument, ExtraContentRequest> =
      ExtraContentRequestFromDomain()


  @Singleton
  @Provides
  fun provideExtraContentRequestToDomain(): ToDomain<ExtraContentRequestDocument, ExtraContentRequest> =
      ExtraContentRequestToDomain()

  @Singleton
  @Provides
  fun provideExtraContentService(
      firestore: FirebaseFirestore,
      fromDomain: FromDomain<ExtraContentRequestDocument, ExtraContentRequest>,
  ): ExtraContentService =
    ExtraContentServiceImpl(
        firestore,
        fromDomain,
    )

}
