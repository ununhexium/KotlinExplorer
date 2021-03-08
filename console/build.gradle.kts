import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

import dependencies.TestDependencies

plugins {
  kotlin("jvm")
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
  implementation("com.google.firebase:firebase-admin:${Versions.firebaseAdmin}")

  implementation("org.slf4j:slf4j-jdk14:${Versions.slf4j}")
  
  // TEST

  testImplementation("org.assertj:assertj-core:3.18.1")
  testImplementation(TestDependencies.junit5_api)
  testImplementation(TestDependencies.junit5_params)
  testRuntimeOnly(TestDependencies.junit5_engine)
}


tasks {
  withType<Test> {
    useJUnitPlatform()
  }
}
