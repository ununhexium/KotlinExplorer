package net.lab0.kotlinexplorer.injection

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import net.lab0.kotlinexplorer.business.domain.feedback.Feedback
import net.lab0.kotlinexplorer.business.domain.problemreport.ProblemReport
import net.lab0.kotlinexplorer.framework.firebase.mappers.FeedbackDocumentFromDomain
import net.lab0.kotlinexplorer.framework.firebase.mappers.FeedbackDocumentToDomain
import net.lab0.kotlinexplorer.framework.firebase.mappers.ProblemReportDocumentFromDomain
import net.lab0.kotlinexplorer.framework.firebase.mappers.ProblemReportDocumentToDomain
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
  fun provideFeedbackFromDomain(): FromDomain<FeedbackDocument, Feedback> =
      FeedbackDocumentFromDomain()

  @Singleton
  @Provides
  fun provideFeedbackToDomain(): ToDomain<FeedbackDocument, Feedback> =
      FeedbackDocumentToDomain()

  @Singleton
  @Provides
  fun provideProblemReportFromDomain(): FromDomain<ProblemReportDocument, ProblemReport> =
      ProblemReportDocumentFromDomain()

  @Singleton
  @Provides
  fun provideProblemReportToDomain(): ToDomain<ProblemReportDocument, ProblemReport> =
      ProblemReportDocumentToDomain()

}
