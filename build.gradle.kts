
buildscript {
  repositories {
    google()
    jcenter()
  }
  dependencies {
    val navVersion = Versions.nav
    classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
    classpath("com.android.tools.build:gradle:7.0.0-alpha10")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
    classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.dagger}")
    classpath("com.google.gms:google-services:${Versions.googleServices}")
  }
}

allprojects {
  repositories {
    google()
    jcenter()
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
    maven { url = uri("https://androidx.dev/snapshots/builds/6932393/artifacts/repository") }
  }
}

tasks {
  withType<Delete> {
    delete(rootProject.buildDir)
  }

  val allTests by registering {
    dependsOn(":grammar:test")
    dependsOn(":app:test")
    dependsOn(":app:connectedAndroidTest")
  }
}
