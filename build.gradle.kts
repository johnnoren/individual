plugins {
    java
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
    jacoco
    application
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

application {
    mainClass = "com.example.ciditest.CiditestApplication"
    applicationDefaultJvmArgs = listOf("-XX:+EnableDynamicAgentLoading", "-Xshare:off")
}

@Suppress("UnstableApiUsage")
testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }

        register<JvmTestSuite>("integrationTest") {
            dependencies {
                implementation(project())
                implementation("org.springframework.boot:spring-boot-starter-test")
                implementation("io.rest-assured:rest-assured:5.3.2")
            }

            sources {
                java {
                    setSrcDirs(listOf("src/intTest/java"))
                }
            }

            targets {
                all {
                    testTask.configure {
                        shouldRunAfter(test)
                    }
                }
            }

            testType.set(TestSuiteType.INTEGRATION_TEST)
        }
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.junit.jupiter:junit-jupiter:5.9.2")
}

// Enforce code coverage of 80% for all classes
tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = 0.8.toBigDecimal()
            }
        }
    }
}

tasks.withType<Test>().configureEach {
    jvmArgs("-XX:+EnableDynamicAgentLoading", "-Xshare:off")
    testLogging {
        events("PASSED", "SKIPPED", "FAILED", "STANDARD_ERROR")
    }
}

tasks {
    bootJar {
        archiveFileName.set("${archiveBaseName.get()}.${archiveExtension.get()}")
    }
}
