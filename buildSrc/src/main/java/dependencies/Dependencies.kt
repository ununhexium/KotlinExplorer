package dependencies

object Dependencies {
  object AppCompat {
    val version = Versions.app_compat

    val app_compat = "androidx.appcompat:appcompat:$version"
  }

  object Atlassian {
    val version = Versions.commonMark
    val commonMark = "com.atlassian.commonmark:commonmark:$version"
  }

  object Compose {

    val version = Versions.compose


    val foundation = "androidx.compose.foundation:foundation:$version"

    val material = "androidx.compose.material:material:$version"
    val materialIconsCore = "androidx.compose.material:material-icons-core:$version"
    val materialIconsExtended = "androidx.compose.material:material-icons-extended:$version"

    val livedata = "androidx.compose.runtime:runtime-livedata:$version"
    val rxJava2 = "androidx.compose.runtime:runtime-rxjava2:$version"

    val ui = "androidx.compose.ui:ui:$version"
    val uiTooling = "androidx.compose.ui:ui-tooling:$version"
  }

  object ConstraintLayout {
    val version = Versions.constraintLayout

    val constraintLayout = "androidx.constraintlayout:constraintlayout:$version"
  }

  object Espresso {
    val version = Versions.espresso_core

    val core = "androidx.test.espresso:espresso-core:$version"
    val contrib = "androidx.test.espresso:espresso-contrib:$version"
  }

  object JUnit4 {
    val version = Versions.junit_4

    val junit = "junit:junit:${Versions.junit_4}"
  }

  object JUnit5 {
    val version = Versions.jupiter

    val api = "org.junit.jupiter:junit-jupiter-api:$version"
    val params = "org.junit.jupiter:junit-jupiter-params:$version"
    val engine = "org.junit.jupiter:junit-jupiter-engine:$version"
  }

  object Material {
    val version = Versions.material

    val material = "com.google.android.material:material:$version"
  }

  object Navigation {
    val version = Versions.nav

    val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
    val ui = "androidx.navigation:navigation-ui-ktx:$version"
  }

  object Room {
    val version = Versions.room

    val runtime = "androidx.room:room-runtime:$version"
    val room = "androidx.room:room-ktx:$version"
  }


  object Truth {
    val version = Versions.truth

    val truth = "com.google.truth:truth:$version"
  }
}
