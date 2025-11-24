plugins {
    kotlin("jvm") version "2.2.21"
    kotlin("plugin.spring") version "1.9.10"
    id("org.springframework.boot") version("3.5.7")
    id("io.kotest") version("6.0.0")
}

apply(plugin = "io.spring.dependency-management")

repositories {
    mavenCentral()
}

java.sourceCompatibility = JavaVersion.VERSION_21

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.data:spring-data-mongodb:4.4.5")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-test")
    testImplementation("io.kotest:kotest-assertions-core:6.0.5")
    testImplementation("io.kotest:kotest-framework-engine:6.0.5")
    testImplementation("io.kotest:kotest-extensions-spring:6.0.5")
    testImplementation("org.wiremock:wiremock:3.13.2")
    testImplementation("com.h2database:h2")
    testImplementation("org.testcontainers:mongodb:1.19.3")
    testImplementation("org.mongodb:mongodb-driver-sync:5.2.1")
}
