package net.lab0.kotlinexplorer.injection

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object FirestoreInstanceModule {
  @Singleton
  @Provides
  fun provideFirebaseFirestore(): FirebaseFirestore =
      FirebaseFirestore.getInstance()
}
