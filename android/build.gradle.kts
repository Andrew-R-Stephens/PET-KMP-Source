import com.sun.xml.fastinfoset.util.StringArray

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.google.gms.services)
    alias(libs.plugins.google.firebase.crashlytics)
    alias(libs.plugins.google.firebase.perf)
}
val playcoreDir = file("G:\\Programs\\AndroidStudioRepositories\\play-core-native-sdk-1.10.0\\play-core-native-sdk")

android {

    namespace = "com.TritiumGaming.phasmophobiaevidencepicker"
    compileSdk = 34

    @Suppress("UnstableApiUsage")
    bundle.language.enableSplit = false

    defaultConfig {
        applicationId = "com.TritiumGaming.phasmophobiaevidencepicker"
        minSdk = 21
        targetSdk = 34
        versionCode = 91
        versionName = "8.2.2.1"

        @Suppress("UnstableApiUsage")
        externalNativeBuild {
            cmake {
                arguments ("-DANDROID_STL=c++_static", "-DPLAYCORE_LOCATION=$playcoreDir")
            }
        }
        ndk {
            abiFilters.addAll(arrayOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64"))
        }

        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables.useSupportLibrary = true
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            // PLAYCORE
            proguardFile ("$playcoreDir/proguard/common.pgcfg")
            proguardFile ("$playcoreDir/proguard/per-feature-proguard-files")
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            // PLAYCORE
            proguardFile ("$playcoreDir/proguard/common.pgcfg")
            proguardFile ("$playcoreDir/proguard/per-feature-proguard-files")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    lint {
        disable.add("RestrictedApi")
    }

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/java")
            java.srcDir("src/main/kotlin")
        }
    }

    kotlin {
        jvmToolchain(17)
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildToolsVersion = "30.0.2"
}

dependencies {
    implementation(projects.shared)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.composeUi.core)
    implementation(libs.androidx.composeUi.toolingPreview)
    implementation(libs.androidx.activityCompose)
    debugImplementation(libs.androidx.composeUi.tooling)



    implementation (libs.firebase.analytics)
    implementation (libs.androidx.core.ktx)

    implementation (libs.android.support.multidex)

    //noinspection GradleDependency
    implementation (libs.kotlin.stdlib)
    implementation (libs.google.gson)

    // PRIMARY
    implementation (libs.androidx.work.runtime)
    implementation (libs.androidx.appcompat.core)
    implementation (libs.androidx.constraintlayout)
    implementation (libs.androidx.navigation.fragmentKtx)
    implementation (libs.androidx.navigation.uiKtx)
    implementation (libs.androidx.cardview.core)
    implementation (libs.androidx.legacy.supportV4)
    implementation (libs.android.material)
    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.testExt.junit)
    androidTestImplementation (libs.androidx.espresso.core)

    // --- Google Credential Manager ----
    implementation(libs.androidx.credentials.core)
    implementation(libs.androidx.credentials.playServicesAuth)
    implementation (libs.firebaseUi.auth)
    // Phillip Lackner suggested for OTC (one-tap client)
    implementation (libs.firebase.authKtx)
    implementation (libs.android.playServices.auth)
    // ----------------------------------

    // GOOGLE FIREBASE
    // Import the BoM for the Firebase platform
    implementation (libs.firebase.auth)
    implementation (platform(libs.firebase.bom))
    // GOOGLE FIREBASE FIRESTORE
    implementation (libs.firebase.firestore)
    // GOOGLE BILLING API
    implementation (libs.android.billing.core)


    // Declare the dependencies for the Crashlytics and Analytics libraries
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation (libs.firebase.crashlyticsCore)
    implementation (libs.firebase.analytics)
    implementation (libs.firebase.perfCore)
    // GOOGLE ADS
    implementation (libs.android.playServices.ads)
    implementation (libs.android.ump.core)

    //GOOGLE GSON
    implementation (libs.google.gson)

    // IN-APP REVIEWS
    /* This dependency is downloaded from the Google’s Maven repository.
     * So, make sure you also include that repository in your project's build.gradle file.*/
    implementation (libs.android.play.core)
    // For Kotlin users also add the Kotlin extensions library for Play Core:
    implementation (libs.android.play.coreKtx)
    // This dependency is downloaded from the Google’s Maven repository.
    // So, make sure you also include that repository in your project's build.gradle file.

    // FRAGMENTS
    // Java language implementation
    // implementation libs.androidx.fragment
    implementation (libs.androidx.fragmentKtx)

    // For use with animated gifs
    implementation (libs.droidsonroids.gifDrawable)

    // DO NOT UPDATE THIS -- BREAKING CHANGES
    implementation (libs.commonsIo.core)

    /*
        ---- START----
        ANDROIDX LEANBACK
    */
    implementation (libs.androidx.leanback.core)
    // leanback-preference is an add-on that provides a settings UI for TV apps.
    implementation (libs.androidx.leanback.preference)
    // leanback-paging is an add-on that simplifies adding paging support to a RecyclerView Adapter.
    implementation (libs.androidx.leanback.paging)
    // leanback-tab is an add-on that provides customized TabLayout to be used as the top navigation bar.
    implementation (libs.androidx.leanback.tab)

    /*
        ---- START----
        ANDROID COMPOSE
    */
    implementation (platform(libs.androidx.compose.bom))
    androidTestImplementation (platform(libs.androidx.compose.bom))
    androidTestImplementation (libs.androidx.composeUi.testJunit4)
    debugImplementation (libs.androidx.composeUi.testManifest)

    // Material Design 3
    implementation (libs.androidx.compose.material3)
    // Android Studio Preview support
    implementation (libs.androidx.composeUi.toolingPreview)
    debugImplementation (libs.androidx.composeUi.tooling)

    // Optional - Included automatically by material, only add when you need
    // the icons but not the material library (e.g. when using Material3 or a
    // custom design system based on Foundation)
    implementation (libs.androidx.compose.materialIconsCore)
    // Optional - Add full set of material icons
    implementation (libs.androidx.compose.materialIconsExtended)
    // Optional - Add window size utils
    implementation (libs.androidx.compose.material3WindowSizeClass)
    // Optional - Integration with LiveData
    implementation (libs.androidx.compose.runtime.liveData)
    // Optional - Integration with RxJava
    implementation (libs.androidx.compose.runtime.rxJava2)

    implementation (libs.androidx.composeUi.core)
    implementation (libs.androidx.composeMaterial.core)

    implementation (libs.androidx.activityCompose)
    implementation (libs.androidx.lifecycle.runtimeKtx)
    implementation (libs.androidx.lifecycle.viewmodelCompose)
    implementation (libs.androidx.lifecycle.runtimeCompose)
    implementation (libs.androidx.navigation.compose)

    implementation (libs.coilKt.compose)

}