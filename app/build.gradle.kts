plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-android")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("kotlin-kapt")
}

android {
    compileOptions.incremental = false
    namespace = "com.example.fetchingthumbnailsorlogos"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.fetchingthumbnailsorlogos"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    packaging {
        resources {
            excludes += "META-INF/*"
        }
    }
    kapt {
        correctErrorTypes =  true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    // androidx-fragment, activity
    implementation(libs.androidx.activity)
    implementation(libs.androidx.fragment)

    // appcompat
    implementation(libs.androidx.appcompat)

    // material
    implementation(libs.material)

    // constraintlayout
    implementation(libs.androidx.constraintlayout)

    // databinding
    kapt(libs.databinding.compiler)

    // coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // gson
    implementation(libs.gson)

    // moshi
    implementation(libs.moshi.kotlin)

    // retrofit 2
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // retrofit 2 converters
    implementation(libs.converter.scalars)
    api(libs.converter.moshi.api)
    implementation(libs.retrofit2.kotlin.coroutines.adapter)
    implementation(libs.converter.gson)
    implementation(libs.converter.moshi)

    //Glide
    implementation(libs.glide)
    ksp(libs.glide.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.jsoup)
}