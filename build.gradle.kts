plugins {
    kotlin("jvm") version "1.7.10" // version to comply with competitive requirements
}

group = "wrabot.competitive"
version = 0.26

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.8.10")
}

tasks.test {
    useJUnitPlatform()
}
