/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    java
    `java-library`
    `maven-publish`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-web:3.2.1")
    api("com.netflix.graphql.dgs:graphql-dgs:9.1.2")
    api("org.springframework.kafka:spring-kafka:3.0.11")
    runtimeOnly("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter:9.1.2")
    implementation("com.google.protobuf:protobuf-java:4.29.2")
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")

    testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    testImplementation("org.spockframework:spock-core:2.4-M1-groovy-4.0")
    testImplementation("org.apache.groovy:groovy-all:4.0.14")
    testCompileOnly("org.projectlombok:lombok:1.18.36")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.36")
}

group = "com.imshakthi"
version = "0.0.1-SNAPSHOT"
description = "java-test-server"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}