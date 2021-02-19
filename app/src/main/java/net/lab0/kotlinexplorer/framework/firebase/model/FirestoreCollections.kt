package net.lab0.kotlinexplorer.framework.firebase.model

import com.google.firebase.firestore.FirebaseFirestore

fun FirebaseFirestore.usersCollection() =
    this.collection("users")

fun FirebaseFirestore.feedbackCollection(user:String) =
    this
        .collection("users")
        .document(user)
        .collection("feedbacks")

fun FirebaseFirestore.problemReportCollection(user:String) =
    this
        .collection("users")
        .document(user)
        .collection("problemReports")

fun FirebaseFirestore.anonymousProblemReportCollection() =
    this
        .collection("anonymousData")
        .document("root")
        .collection("problemReports")
