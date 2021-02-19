package net.lab0.kotlinexplorer.framework.firebase

import com.google.firebase.auth.FirebaseAuth

interface WithUidMixin {
  val firebaseAuth: FirebaseAuth

  suspend fun <T> withUid(action: suspend (String) -> T): T {
    val uid = firebaseAuth.uid
    return if (uid != null) {
      action(uid)
    } else {
      TODO("Write test")
      // crashlytics?, logs?, user must be at least auth anonymously
    }
  }
}
