plugins {
    id("com.gradleup.shadow") version "9.4.1"
    id("java")
    id("xyz.jpenilla.run-paper") version "3.0.2"
}

group = "fr.arax-gaming"
version = "0.2.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.1.2.build.+")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
}

tasks {
    processResources {
        expand("version" to version)
    }
    jar {
        enabled = false
        dependsOn(shadowJar)
    }
    shadowJar {
        archiveClassifier.set("")
    }
    runServer {
        minecraftVersion("26.1.2")
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
