import ankiitdev.deps.*

plugins {
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
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

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }

}

dependencies {
    implementation(Libs.coreKtx)
    implementation(Libs.lifecycleCommon)
    implementation(Libs.lifecycleExtension)
    implementation("com.jakewharton:disklrucache:2.0.2")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components.findByName("release"))
            groupId = "com.github.ankiitdev"
            artifactId = "viewpic"
            version = "1.0"
        }
    }
}