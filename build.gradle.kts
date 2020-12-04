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
