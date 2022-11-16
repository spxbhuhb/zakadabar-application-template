/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

val brotliVersion = "1.7.1"

kotlin {
    dependencies {
        implementation("com.aayushatharva.brotli4j:brotli4j:$brotliVersion")
        implementation("com.aayushatharva.brotli4j:native-windows-x86_64:$brotliVersion")
        implementation("com.aayushatharva.brotli4j:native-osx-x86_64:$brotliVersion")
        implementation("com.aayushatharva.brotli4j:native-linux-x86_64:$brotliVersion")
        implementation("com.aayushatharva.brotli4j:native-linux-aarch64:$brotliVersion")
    }
}
