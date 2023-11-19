plugins {
    kotlin("jvm") version "1.9.10"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
val coroutineVersion = "1.7.3"
val vertxVersion = "4.5.0"
val postgresVersion = "42.3.8"
val jooqVersion = "3.18.7"
val jdbcClient = "4.2.1"
val slf4j = "1.7.30"

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
    implementation ("io.vertx:vertx-lang-kotlin-coroutines:$vertxVersion")
    implementation("io.vertx:vertx-core:$vertxVersion")
    implementation("io.vertx:vertx-web:$vertxVersion")
    implementation("io.vertx:vertx-config:$vertxVersion")
    implementation("io.vertx:vertx-config-hocon:$vertxVersion")
    implementation("org.postgresql:postgresql:$postgresVersion")
    implementation("io.vertx:vertx-jdbc-client:$jdbcClient")
    implementation("org.jooq:jooq:$jooqVersion")
    implementation("org.slf4j:slf4j-api:$slf4j")
    implementation("ch.qos.logback:logback-classic:1.2.9")
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}