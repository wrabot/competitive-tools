plugins {
    kotlin("jvm") version "1.7.10" // version to comply with competitive requirements
}

group = "wrabot.competitive"
version = 0.47

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:1.7.10")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.8.10")
}

tasks.jar {    
    from(sourceSets["main"].allSource)
}

tasks.test {
    useJUnitPlatform()
}
