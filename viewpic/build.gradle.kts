import ankiitdev.deps.*

plugins {
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 32

    defaultConfig {
        compileSdk = 21
        compileSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Libs.coreKtx)
    implementation(Libs.appcompat)
    implementation(Libs.MDC)
    implementation(Libs.lifecycleCommon)
    implementation(Libs.lifecycleExtension)
    implementation(Libs.lifecycleLiveDataKtx)
    implementation("com.jakewharton:disklrucache:2.0.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")

}