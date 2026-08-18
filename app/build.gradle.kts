plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.room.ksp)
    alias(libs.plugins.androidx.room)
    kotlin("plugin.serialization") version "2.0.21"
    id("androidx.navigation.safeargs.kotlin")
    alias(libs.plugins.koin.compiler)
}

android {
    namespace = "com.example.gymusAndroid"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.examdple.gymus_android"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }


    //noinspection WrongGradleMethod
    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {
    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.11.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.11.0")

    // google fonts
    implementation("androidx.compose.ui:ui-text-google-fonts:1.11.2")

    // supabase
    implementation(platform("io.github.jan-tennert.supabase:bom:3.6.0"))
    implementation("io.github.jan-tennert.supabase:postgrest-kt")
    implementation("io.ktor:ktor-client-android:3.4.3")

    // koin
    implementation(libs.io.insert.koin)
    implementation(libs.io.insert.koin.compose)
    implementation(libs.io.insert.koin.annotations)
    implementation(libs.io.insert.koin.core)

    // ktor
    implementation(libs.io.ktor.client.core)
    implementation(libs.io.ktor.client.cio)
    implementation(libs.io.ktor.client.content.negotiation)
    implementation(libs.io.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.logging)
    testImplementation(libs.ktor.client.mock)

    // room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    ksp(libs.androidx.room.compiler)

    // datastore
    implementation(libs.androidx.datastore.preferences)

    //viewmodel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
    implementation(libs.androidx.lifecycle.service)
    implementation(libs.androidx.lifecycle.process)
    testImplementation(libs.androidx.lifecycle.runtime.testing)

    // navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    androidTestImplementation(libs.androidx.navigation.testing)

    // material 3
    implementation(libs.androidx.compose.adaptive)
    implementation(libs.androidx.compose.adaptive.layout)
    implementation(libs.androidx.compose.adaptive.navigation)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.material3.window.size.class1)
    implementation(libs.androidx.compose.material3.adaptive.navigation.suite)

    // sqlite
    implementation(libs.androidx.sqlite.ktx)
    implementation(libs.androidx.sqlite.framework)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    //saved state
    implementation(libs.androidx.savedstate.ktx)

    // paging
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)
    testImplementation(libs.androidx.paging.common)

    // coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // testing
    testImplementation(libs.junit)
    testImplementation(libs.adnroidx.room.testing)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

}

configurations.all {
    exclude(group = "com.intellij", module = "annotations")
}

koinCompiler {
    compileSafety = false
}