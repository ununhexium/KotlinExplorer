import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    antlr
    java
    kotlin("jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    implementation("junit:junit:4.13.1")

    val kotlinVersion: String by project
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")

    antlr("org.antlr:antlr4:4.7.1")

    testImplementation("org.assertj:assertj-core:3.18.1")
}


tasks {
    withType<KotlinCompile> {
        dependsOn(generateGrammarSource)
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
