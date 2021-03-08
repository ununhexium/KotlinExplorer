package net.lab0.kotlinexplorer.console

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient


fun main() {
  val projectId = "kotlin-explorer-7824c"

  // Use the application default credentials
  val credentials = GoogleCredentials.getApplicationDefault()
  val options = FirebaseOptions.builder()
    .setCredentials(credentials)
    .setProjectId(projectId)
    .build()
  FirebaseApp.initializeApp(options)

  val db = FirestoreClient.getFirestore()

  val docs = db.collectionGroup("problemReports")
    .whereEqualTo("status", "new")
    .get()
    .get()
    .documents

  println(docs.size)

  docs.forEach {
    println(it.id)
    println("Status: " + it["status"])
    println("Location: " + it["problemLocationDescription"])
    println("Feedback: " + it["userFeedback"])
    println()
  }
}

fun Firestore.usersCollection() =
  this.collection("users")

fun Firestore.userDocument(uid: String) =
  this.usersCollection().document(uid)

fun Firestore.feedbackCollection(uid: String) =
  this
    .userDocument(uid)
    .collection("feedbacks")

fun Firestore.problemReportCollection(uid: String) =
  this
    .userDocument(uid)
    .collection("problemReports")

fun Firestore.extraLessonRequestDocument(uid: String) =
  this
    .userDocument(uid)
    .collection("extraLessonRequest")
    .document("singleton")

