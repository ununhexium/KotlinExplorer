
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        val kotlinVersion: String by project
        classpath("com.android.tools.build:gradle:4.2.0-alpha15")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
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

