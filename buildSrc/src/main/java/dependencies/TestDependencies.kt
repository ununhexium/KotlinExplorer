package dependencies

object TestDependencies {
  val androidx_test_ext = "androidx.test.ext:junit-ktx:${Versions.androidx_test_ext}"

  val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"
  val espresso_contrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso_core}"

  val junit4_junit = "junit:junit:${Versions.junit_4}"

  val junit5_api = "org.junit.jupiter:junit-jupiter-api:${Versions.jupiter}"
  val junit5_params = "org.junit.jupiter:junit-jupiter-params:${Versions.jupiter}"
  val junit5_engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.jupiter}"

  val mockk = "io.mockk:mockk:${Versions.mockk}"

  val truth = "com.google.truth:truth:${Versions.truth}"
}