package net.lab0.kotlinexplorer.framework.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import net.lab0.kotlinexplorer.business.domain.LessonProgress

class UserService(
    val firestore: FirebaseFirestore
) {
  fun saveProgress(lessonProgress: LessonProgress): Task<DocumentReference> {
    return firestore
        .collection("lesson_progress")
        .add(lessonProgress)
  }
}
