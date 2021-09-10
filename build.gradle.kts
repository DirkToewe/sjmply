import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.IntStream
import kotlin.math.max
import kotlin.math.min

plugins {
  java
}

group = "org.jengineering"
version = "1.0.2"

repositories {
  mavenCentral()
}

java {
  sourceCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
}

val v_jqwik = "1.5.4"
val v_assertj = "3.20.2"

tasks.test {
  useJUnitPlatform {
    includeEngines(
      "assertj-core",
       "jqwik"
    )
  }
  enableAssertions = true
  jvmArgs = listOf(
    "-ea",
    "--illegal-access=permit",
    "-XX:MaxInlineLevel=15"
  )
  include(
    "**/*Properties.class",
    "**/*Test.class",
    "**/*Tests.class"
  )
}

dependencies {
  testImplementation("net.jqwik:jqwik:$v_jqwik")
  testImplementation("net.jqwik:jqwik-engine:$v_jqwik")
  testImplementation("org.assertj:assertj-core:$v_assertj")
}
