package net.lab0.kotlinexplorer.framework.firebase.model

import com.google.firebase.firestore.FirebaseFirestore

fun FirebaseFirestore.usersCollection() =
    this.collection("users")

fun FirebaseFirestore.usersFeedbackCollection() =
    this
        .collection("users")
        .document("root") // TODO: no hardcoding
        .collection("feedbacks")
