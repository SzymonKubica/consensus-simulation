plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.26")
    implementation("com.google.code.gson:gson:2.10.1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(platform("com.google.cloud:libraries-bom:26.16.0"))
    implementation("com.google.cloud:google-cloud-bigquery")
}

tasks.test {
    useJUnitPlatform()
}