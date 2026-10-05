plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
kotlin {
    jvmToolchain(21)
    compilerOptions {
        optIn.add("kotlin.RequiresOptIn")
    }
}

configurations.all {
    exclude(group = "com.intellij", module = "annotations")
}

dependencies {
    api(project(":core:domain"))
    implementation(libs.kotlinx.coroutines.core)
}
