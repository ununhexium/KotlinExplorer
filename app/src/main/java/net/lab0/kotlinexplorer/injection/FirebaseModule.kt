package net.lab0.kotlinexplorer.injection

import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import net.lab0.kotlinexplorer.business.domain.feedback.Feedback
import net.lab0.kotlinexplorer.framework.firebase.mappers.FeedbackDocumentFromDomain
import net.lab0.kotlinexplorer.framework.firebase.mappers.FeedbackDocumentToDomain
import net.lab0.kotlinexplorer.framework.firebase.model.FeedbackDocument
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
  fun provideFromDomain(): FromDomain<FeedbackDocument, Feedback> =
      FeedbackDocumentFromDomain()

  @Singleton
  @Provides
  fun provideToDomain(): ToDomain<FeedbackDocument, Feedback> =
      FeedbackDocumentToDomain()
}
