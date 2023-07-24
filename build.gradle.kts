plugins {
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
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}


tasks {
    runServer {
        minecraftVersion("1.20.1")
    }
}

tasks.processResources {
    expand("version" to version)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}