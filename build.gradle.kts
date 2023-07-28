plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("java")
    id("xyz.jpenilla.run-paper") version "2.1.0"
}

group = "fr.arax-gaming"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
    implementation("cloud.commandframework", "cloud-paper", "1.8.3")
    implementation("cloud.commandframework:cloud-core:1.8.3")
    implementation("cloud.commandframework:cloud-annotations:1.8.3")
    annotationProcessor("cloud.commandframework:cloud-annotations:1.8.3")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    processResources {
        expand("version" to version)
    }
    jar {
        dependsOn(shadowJar)
        archiveClassifier.set("default")
    }
    shadowJar {
        archiveClassifier.set("")
    }
    runServer {
        minecraftVersion("1.20.1")
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}