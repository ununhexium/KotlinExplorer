package net.lab0.kotlinexplorer.console.command

import com.github.ajalt.clikt.core.CliktCommand
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient

class ListProblemReports : CliktCommand() {
  private val projectId = "kotlin-explorer-7824c"

  override fun run() {
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
}
