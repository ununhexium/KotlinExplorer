import dependencies.AnnotationProcessing
import dependencies.Dependencies
import dependencies.Application
import dependencies.TestDependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

plugins {
  id("com.android.application")
  id("dagger.hilt.android.plugin")
  id("kotlin-android")
  id("kotlin-android-extensions")
  id("kotlin-kapt")
  id("androidx.navigation.safeargs.kotlin")
}

android {
  compileSdkVersion(Versions.compileSdk)

  defaultConfig {
    applicationId(Application.id)
    minSdkVersion(Versions.minSdk)
    targetSdkVersion(Versions.targetSdk)
    versionCode(Application.versionCode)
    versionName(Application.versionName)

    testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")

    javaCompileOptions {
      annotationProcessorOptions {
        arguments += mapOf("room.schemaLocation" to "$projectDir/schemas")
      }
    }

  }

  buildTypes {
    release {
      minifyEnabled(false)
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility(Versions.java)
    targetCompatibility(Versions.java)
  }
  kotlinOptions {
    jvmTarget = Versions.java
    useIR = true
    @Suppress("SuspiciousCollectionReassignment")
    freeCompilerArgs += listOf("-Xallow-jvm-ir-dependencies", "-Xskip-prerelease-check")
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerVersion = Versions.kotlin
    kotlinCompilerExtensionVersion = Versions.compose
  }
  packagingOptions {
    /**
     *    > 2 files found with path 'META-INF/LICENSE.md' from inputs:
     *       - /home/uuh/.gradle/caches/modules-2/files-2.1/org.junit.platform/junit-platform-commons/1.6.0/b0a75795cf03841d4f9cc54099557baffc11c727/junit-platform-commons-1.6.0.jar
     *       - /home/uuh/.gradle/caches/modules-2/files-2.1/org.junit.jupiter/junit-jupiter-api/5.6.0/f29e6318333d2303ce4965c9819cfad08de7d1e5/junit-jupiter-api-5.6.0.jar
     */
    exclude("META-INF/LICENSE.md")
    /**
     *    > 2 files found with path 'META-INF/LICENSE-notice.md' from inputs:
     *       - /home/uuh/.gradle/caches/modules-2/files-2.1/org.junit.platform/junit-platform-commons/1.6.0/b0a75795cf03841d4f9cc54099557baffc11c727/junit-platform-commons-1.6.0.jar
     *       - /home/uuh/.gradle/caches/modules-2/files-2.1/org.junit.jupiter/junit-jupiter-api/5.6.0/f29e6318333d2303ce4965c9819cfad08de7d1e5/junit-jupiter-api-5.6.0.jar
     */
    exclude("META-INF/LICENSE-notice.md")
    /**
     *    > 2 files found with path 'META-INF/AL2.0' from inputs:
     *       - /home/uuh/.gradle/caches/modules-2/files-2.1/net.java.dev.jna/jna-platform/5.5.0/af38e7c4d0fc73c23ecd785443705bfdee5b90bf/jna-platform-5.5.0.jar
     *       - /home/uuh/.gradle/caches/modules-2/files-2.1/net.java.dev.jna/jna/5.5.0/e0845217c4907822403912ad6828d8e0b256208/jna-5.5.0.jar
     */
    exclude("META-INF/AL2.0")
    /**
     *    > 2 files found with path 'META-INF/LGPL2.1' from inputs:
     *       - /home/uuh/.gradle/caches/modules-2/files-2.1/net.java.dev.jna/jna-platform/5.5.0/af38e7c4d0fc73c23ecd785443705bfdee5b90bf/jna-platform-5.5.0.jar
     *       - /home/uuh/.gradle/caches/modules-2/files-2.1/net.java.dev.jna/jna/5.5.0/e0845217c4907822403912ad6828d8e0b256208/jna-5.5.0.jar
     */
    exclude("META-INF/LGPL2.1")
  }

  sourceSets {
    getByName("test").java.srcDirs("$projectDir/src/testShared/java")
    getByName("androidTest").java.srcDirs("$projectDir/src/testShared/java")
  }
}

dependencies {
  implementation(Dependencies.app_compat)

  implementation(Dependencies.constraintLayout)

  implementation(Dependencies.compose_ui)
  implementation(Dependencies.compose_uiTooling)
  implementation(Dependencies.compose_foundation)
  implementation(Dependencies.compose_livedata)
  implementation(Dependencies.compose_rxJava2)
  implementation(Dependencies.compose_material)
  implementation(Dependencies.compose_materialIconsCore)
  implementation(Dependencies.compose_materialIconsExtended)

  implementation(Dependencies.navigation_fragment)
  implementation(Dependencies.navigation_ui)

//    def nav_compose = "1.0.0-SNAPSHOT"
//    implementation("androidx.navigation:navigation-compose:$nav_compose")

  implementation(Dependencies.room)
  implementation(Dependencies.room_runtime)
  kapt(AnnotationProcessing.room_compiler)


  implementation(Dependencies.atlassian_commonMark)

  implementation(Dependencies.material)

  implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
  implementation("org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}")

  implementation(Dependencies.hilt)
  kapt(AnnotationProcessing.hilt_compiler)


  // add scripting to show the result to the user
//    def kotlin_version = kotlinVersion
//    implementation("org.jetbrains.kotlin:kotlin-script-util:$kotlin_version")
//    implementation("org.jetbrains.kotlin:kotlin-script-runtime:$kotlin_version")


  // SUBPROJECTS

  implementation(project(":grammar"))

  // DEBUG
  debugImplementation(Dependencies.canary_leak)

  // TEST

  testImplementation(TestDependencies.junit4_junit)
  testImplementation(TestDependencies.truth)
  testImplementation(TestDependencies.androidx_test_ext)
  testImplementation(TestDependencies.mockk)

  androidTestImplementation(TestDependencies.androidx_test_ext)
  androidTestImplementation(TestDependencies.espresso_core)
  androidTestImplementation(TestDependencies.espresso_contrib)
  androidTestImplementation(TestDependencies.junit5_api)
  androidTestImplementation(TestDependencies.kotlinx_coroutines)
  androidTestImplementation(TestDependencies.mockk_android)
  androidTestImplementation(TestDependencies.room)
  androidTestImplementation(TestDependencies.truth)
}
