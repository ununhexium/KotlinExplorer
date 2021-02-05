
buildscript {
  repositories {
    google()
    jcenter()
  }
  dependencies {
    val navVersion = Versions.nav
    classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
    classpath("com.android.tools.build:gradle:7.0.0-alpha05")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.21")
    classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.dagger}")
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
}

