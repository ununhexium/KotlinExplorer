package net.lab0.kotlinexplorer.framework.firebase.model

import com.google.firebase.Timestamp

data class ExtraContentRequestDocument(
    val globalSuccess: Int,
    val globalFailure: Int,
    val globalNeutral: Int,
    val timestamp: Timestamp = Timestamp.now(),
)
