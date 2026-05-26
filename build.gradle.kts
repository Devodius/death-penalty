plugins {
    id("com.gradleup.shadow") version "9.4.1"
    id("java")
    id("xyz.jpenilla.run-paper") version "3.0.2"
}

group = "fr.arax-gaming"

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
        inputs.property("version", version)
        filesMatching("**/*.yml") {
            expand("version" to version)
        }
    }
    classes {
        dependsOn(getByName("BundleResourcePack"))
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

tasks.register<Zip>("BundleResourcePack") {
    description = "Take the ressource pack in the ressource folder archive it and output it in resourcePack folder"
    dependsOn(tasks.processResources)

    archiveAppendix.set("resource-pack")
    destinationDirectory.set(layout.buildDirectory.dir("resourcePack"))

    from(layout.buildDirectory.dir("resources/main/death_penalty_resource_pack"))
}
