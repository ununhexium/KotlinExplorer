package net.lab0.kotlinexplorer.inject

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object TestModule {

  @Singleton
  @Provides
  fun provideFirestoreSettings(): FirebaseFirestoreSettings {
    return FirebaseFirestoreSettings.Builder()
        .setHost("10.0.2.2:9098")
        .setSslEnabled(false)
        .setPersistenceEnabled(false)
        .build()
  }

  @Singleton
  @Provides
  fun provideFirebaseFirestore(
      firestoreSettings: FirebaseFirestoreSettings
  ): FirebaseFirestore {
    val firestore = FirebaseFirestore.getInstance()
    firestore.firestoreSettings = firestoreSettings
    return firestore
  }

}
