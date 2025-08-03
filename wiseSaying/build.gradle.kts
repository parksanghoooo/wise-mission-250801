plugins {
    id("java")
}

group = "com.ll"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    /**
     * DB 의존성
     * - MySQL
     */
    implementation("com.mysql:mysql-connector-j:8.3.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}