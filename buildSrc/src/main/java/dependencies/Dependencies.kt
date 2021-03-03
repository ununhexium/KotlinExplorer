package dependencies

object Dependencies {
  val app_compat = "androidx.appcompat:appcompat:${Versions.app_compat}"
  val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"

  val compose_foundation = "androidx.compose.foundation:foundation:${Versions.compose}"

  val compose_material = "androidx.compose.material:material:${Versions.compose}"
  val compose_materialIconsCore = "androidx.compose.material:material-icons-core:${Versions.compose}"
  val compose_materialIconsExtended = "androidx.compose.material:material-icons-extended:${Versions.compose}"

  val compose_livedata = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
  val compose_rxJava2 = "androidx.compose.runtime:runtime-rxjava2:${Versions.compose}"

  val compose_ui = "androidx.compose.ui:ui:${Versions.compose}"
  val compose_uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"

  val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayout}"

  val firebase = "com.google.firebase:firebase-bom:${Versions.firebase}"
  val firebaseAuth = "com.google.firebase:firebase-auth"
  val firebaseAuthUi = "com.firebaseui:firebase-ui-auth:${Versions.firebaseUi}"
  val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
  val firebaseFirestore = "com.google.firebase:firebase-firestore-ktx"

  val hilt_android = "com.google.dagger:hilt-android:${Versions.dagger}"
  val hilt_lifecycle_viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt}"

  val kotlinCoroutinesPlayServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutines}"

  val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

  val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.nav}"
  val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.nav}"

  val room_runtime = "androidx.room:room-runtime:${Versions.room}"
  val room = "androidx.room:room-ktx:${Versions.room}"

  val atlassian_commonMark = "com.atlassian.commonmark:commonmark:${Versions.commonMark}"

  val material = "com.google.android.material:material:${Versions.material}"
}
