import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

import dependencies.TestDependencies

plugins {
  antlr
  java
  kotlin("jvm")
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
  implementation("junit:junit:4.13.1")

  implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")

  antlr("org.antlr:antlr4:4.7.1")

  // TEST

  testImplementation("org.assertj:assertj-core:3.18.1")
  testImplementation(TestDependencies.junit5_api)
  testImplementation(TestDependencies.junit5_params)
  testRuntimeOnly(TestDependencies.junit5_engine)
}


tasks {
  withType<KotlinCompile> {
    dependsOn(generateGrammarSource)
  }

  withType<Test> {
    useJUnitPlatform()
  }

  generateGrammarSource {
    arguments = arguments + listOf(
        "-visitor",
        "-long-messages",
        "-package", "net.lab0.grammar.kotlin",
        "-lib", "src/main/antlr/net/lab0/grammar/kotlin"
    )
  }
}
