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
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.kotest:kotest-assertions-core:6.0.4")
    testImplementation("io.kotest:kotest-framework-engine:6.0.0")
}

