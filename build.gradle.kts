plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java.sourceSets["main"].java {
    srcDir("support")
}

dependencies {
    // Add jar files to compile java code
    implementation(files("iterati/MrYoungIterati.jar"))

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}