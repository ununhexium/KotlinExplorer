package net.lab0.kotlinexplorer.console

import com.google.api.gax.paging.Page
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.cloud.storage.Bucket
import com.google.cloud.storage.Storage

import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import com.google.cloud.storage.StorageOptions


fun main() {
    val projectId = "kotlin-explorer"

    authImplicit()

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


fun authImplicit() {
    // If you don't specify credentials when constructing the client, the client library will
    // look for credentials via the environment variable GOOGLE_APPLICATION_CREDENTIALS.
    val storage: Storage = StorageOptions.getDefaultInstance().service
    println("Buckets:")
    val buckets: Page<Bucket> = storage.list()
    for (bucket in buckets.iterateAll()) {
        System.out.println(bucket.toString())
    }
}