package dependencies

object Dependencies {
  val app_compat = "androidx.appcompat:appcompat:${Versions.app_compat}"

  val compose_foundation = "androidx.compose.foundation:foundation:${Versions.compose}"

  val compose_material = "androidx.compose.material:material:${Versions.compose}"
  val compose_materialIconsCore = "androidx.compose.material:material-icons-core:${Versions.compose}"
  val compose_materialIconsExtended = "androidx.compose.material:material-icons-extended:${Versions.compose}"

  val compose_livedata = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
  val compose_rxJava2 = "androidx.compose.runtime:runtime-rxjava2:${Versions.compose}"

  val compose_ui = "androidx.compose.ui:ui:${Versions.compose}"
  val compose_uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"

  val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

  val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.nav}"
  val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.nav}"

  val hilt_android = "com.google.dagger:hilt-android:${Versions.dagger}"
  val hilt_lifecycle_viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt}"

  val room_runtime = "androidx.room:room-runtime:${Versions.room}"
  val room = "androidx.room:room-ktx:${Versions.room}"


  val atlassian_commonMark = "com.atlassian.commonmark:commonmark:${Versions.commonMark}"

  val material = "com.google.android.material:material:${Versions.material}"
}
