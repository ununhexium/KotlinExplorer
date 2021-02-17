package net.lab0.kotlinexplorer.framework.firebase

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import net.lab0.kotlinexplorer.business.domain.LessonProgress

fun saveProgress(lessonProgress: LessonProgress) {
  Firebase
      .firestore
      .collection("lessonProgress")
      .add(lessonProgress)
}