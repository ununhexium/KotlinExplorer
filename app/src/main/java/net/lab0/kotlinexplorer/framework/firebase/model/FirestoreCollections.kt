package net.lab0.kotlinexplorer.framework.firebase.model

import com.google.firebase.firestore.FirebaseFirestore

fun FirebaseFirestore.usersCollection() =
    this.collection("users")

fun FirebaseFirestore.feedbackCollection(uid:String) =
    this
        .usersCollection()
        .document(uid)
        .collection("feedbacks")

fun FirebaseFirestore.problemReportCollection(uid:String) =
    this
        .usersCollection()
        .document(uid)
        .collection("problemReports")

fun FirebaseFirestore.extraLessonRequestDocument(uid:String) =
    this
        .usersCollection()
        .document(uid)
        .collection("extraLessonRequest")
        .document("singleton")
