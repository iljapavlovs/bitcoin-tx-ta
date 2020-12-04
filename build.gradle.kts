import io.qameta.allure.gradle.AllureExtension
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED

plugins {
    java
    maven
    id("io.qameta.allure") version "2.8.1"
}

group = "io.iljapavlovs"
version = "1.0-SNAPSHOT"

val allureVersion = "2.13.7"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.3")
    testImplementation("com.github.briandilley.jsonrpc4j:jsonrpc4j:1.5.3")
    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("org.testcontainers:testcontainers:1.15.0")
    testImplementation("org.testcontainers:junit-jupiter:1.15.0")
    testImplementation("org.projectlombok:lombok:1.18.10")
    testImplementation("org.awaitility:awaitility:4.0.3")
    testImplementation("io.qameta.allure:allure-java-commons:$allureVersion")
}


tasks.withType(JavaCompile::class) {
    sourceCompatibility = "${JavaVersion.VERSION_15}"
    targetCompatibility = "${JavaVersion.VERSION_15}"
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }
    compileTestJava {
        options.encoding = "UTF-8"
    }
}

tasks.withType(Test::class) {
    ignoreFailures = true
    useJUnitPlatform()
    testLogging {
        events = setOf(
           PASSED,
           SKIPPED,
           FAILED
        )
    }
}

configure<AllureExtension> {
    autoconfigure = true
    aspectjweaver = true
    version = allureVersion

    clean = true

    useJUnit5 {
        version = allureVersion
    }
}
