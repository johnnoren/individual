plugins {
    java
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

val intTestImplementation = configurations.create("intTestImplementation") {
    extendsFrom(configurations.named("implementation").get())
}
val intTestRuntimeOnly = configurations.create("intTestRuntimeOnly") {
    extendsFrom(configurations.named("runtimeOnly").get())
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.rest-assured:rest-assured:5.3.2")

    intTestImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    intTestRuntimeOnly("org.junit.platform:junit-platform-launcher")
    intTestImplementation("io.rest-assured:rest-assured:5.3.2")
}

sourceSets.create("intTest") {
    compileClasspath += sourceSets.named("main").get().output
    runtimeClasspath += sourceSets.named("main").get().output
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    jvmArgs("-XX:+EnableDynamicAgentLoading")
    testLogging {
        events("PASSED", "SKIPPED", "FAILED", "STANDARD_OUT", "STANDARD_ERROR")
    }
}

tasks.register<Test>("integrationTest") {
    description = "Runs integration tests."
    group = "verification"
    testClassesDirs = sourceSets.named("intTest").get().output.classesDirs
    classpath = sourceSets.named("intTest").get().runtimeClasspath
    shouldRunAfter("test")
    useJUnitPlatform()
    testLogging {
        events("PASSED", "SKIPPED", "FAILED", "STANDARD_OUT", "STANDARD_ERROR")
    }
}

tasks {
    bootJar {
        archiveFileName.set("${archiveBaseName.get()}.${archiveExtension.get()}")
    }
}
