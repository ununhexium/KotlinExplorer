import dependencies.AnnotationProcessing
import dependencies.Application
import dependencies.Dependencies
import dependencies.TestDependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("com.android.application")
  id("com.google.gms.google-services")
  id("dagger.hilt.android.plugin")
  id("kotlin-android")
  id("kotlin-android-extensions")
  id("kotlin-kapt")
  id("androidx.navigation.safeargs.kotlin")

  id("org.barfuin.gradle.taskinfo") version "1.0.5"
}

android {
  compileSdkVersion(Versions.compileSdk)

  defaultConfig {
    applicationId = Application.id
    minSdkVersion(Versions.minSdk)
    targetSdkVersion(Versions.targetSdk)
    versionCode = Application.versionCode
    versionName = Application.versionName

    testInstrumentationRunner(
        "net.lab0.kotlinexplorer.injection.CustomTestRunner"
    )

    javaCompileOptions {
      annotationProcessorOptions {
        arguments += listOf(
            "room.schemaLocation" to "$projectDir/schemas"
        )
      }
    }
  }

  buildFeatures {
    compose = true
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

  composeOptions {
    kotlinCompilerVersion = Versions.kotlin
    kotlinCompilerExtensionVersion = Versions.compose
  }

  kotlinOptions {
    jvmTarget = Versions.java
    useIR = true
  }


  packagingOptions {
    resources.excludes.addAll(
        listOf(
            "META-INF/LICENSE.md",
            "META-INF/LICENSE-notice.md",
            "META-INF/AL2.0",
            "META-INF/LGPL2.1"
        )
    )
  }

}


tasks {
  withType<Test> {
    useJUnitPlatform()
  }

  withType<KotlinCompile> {
    kotlinOptions {
      jvmTarget = "1.8"
      freeCompilerArgs = freeCompilerArgs + listOf(
          "-Xallow-jvm-ir-dependencies",
          "-Xskip-prerelease-check",
          // to disable @ExperimentalCoroutinesApi warnings
          "-Xuse-experimental=kotlin.Experimental"
      )
    }
  }
}


dependencies {
  implementation(Dependencies.app_compat)
  implementation(Dependencies.activityCompose)

  implementation(Dependencies.constraintLayout)

  implementation(Dependencies.compose_ui)
  implementation(Dependencies.compose_uiTooling)
  implementation(Dependencies.compose_foundation)
  implementation(Dependencies.compose_livedata)
  implementation(Dependencies.compose_rxJava2)
  implementation(Dependencies.compose_material)
  implementation(Dependencies.compose_materialIconsCore)
  implementation(Dependencies.compose_materialIconsExtended)

  implementation(platform(Dependencies.firebase))
  implementation(Dependencies.firebaseAuth)
  implementation(Dependencies.firebaseAuthUi)
  implementation(Dependencies.firebaseAnalytics)
  implementation(Dependencies.firebaseFirestore)

  implementation(Dependencies.navigation_fragment)
  implementation(Dependencies.navigation_ui)

  implementation(Dependencies.hilt_android)
  implementation(Dependencies.hilt_lifecycle_viewmodel)
  kapt(AnnotationProcessing.hilt_android_compiler)
  kapt(AnnotationProcessing.hilt_compiler)

  implementation(Dependencies.kotlinCoroutinesPlayServices)

  implementation(Dependencies.lifecycleViewModel)


//    def nav_compose = "1.0.0-SNAPSHOT"
//    implementation("androidx.navigation:navigation-compose:$nav_compose")

  implementation(Dependencies.room)
  implementation(Dependencies.room_runtime)
  kapt(AnnotationProcessing.room_compiler)


  implementation(Dependencies.atlassian_commonMark)

  implementation(Dependencies.material)

  implementation(kotlin("stdlib-jdk8"))


  // add scripting to show the result to the user
//    def kotlin_version = kotlinVersion
//    implementation("org.jetbrains.kotlin:kotlin-script-util:$kotlin_version")
//    implementation("org.jetbrains.kotlin:kotlin-script-runtime:$kotlin_version")


  // SUBPROJECTS

  implementation(project(":grammar"))

  // TEST

  testImplementation(TestDependencies.androidx_test_ext)
  testImplementation(TestDependencies.assertJ)
  testImplementation(TestDependencies.assertK)
  testImplementation(TestDependencies.hiltAndroidTesting)
  testImplementation(TestDependencies.junit4_junit)
  testImplementation(TestDependencies.junit5_api)
  testImplementation(TestDependencies.junit5_params)
  testImplementation(TestDependencies.kotlinCoroutinesTest)
  testImplementation(TestDependencies.mockk)
  testImplementation(TestDependencies.truth)
  testRuntimeOnly(TestDependencies.junit5_engine)

  androidTestImplementation(TestDependencies.androidx_test_ext)
  androidTestImplementation(TestDependencies.assertJ)
  androidTestImplementation(TestDependencies.assertK)
  androidTestImplementation(TestDependencies.espresso_core)
  androidTestImplementation(TestDependencies.espresso_contrib)
  androidTestImplementation(TestDependencies.hiltAndroidTesting)
  androidTestImplementation(TestDependencies.junit5_api)
  androidTestImplementation(TestDependencies.kotlinCoroutinesTest)
  androidTestImplementation(TestDependencies.mockkAndroid)
  androidTestImplementation(TestDependencies.truth)

  kaptTest(AnnotationProcessing.hilt_compiler)
  kaptAndroidTest(AnnotationProcessing.hilt_android_compiler)
}
