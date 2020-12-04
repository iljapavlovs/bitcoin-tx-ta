plugins {
    java
}

group = "io.iljapavlovs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.3")
    testImplementation("com.github.briandilley.jsonrpc4j:jsonrpc4j:1.5.3")
    testImplementation("org.assertj:assertj-core:3.11.1")
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
            org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
        )
    }
}
