plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.xxmrk888ytxx.cryptomanager"
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = Config.isR8ProGuardEnableForRelease
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
        }

        debug {
            isMinifyEnabled = Config.isR8ProGuardEnableForDebug
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = Config.sourceCompatibility
        targetCompatibility = Config.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
    packaging {
        resources.excludes.add("META-INF/*")
    }
}

dependencies {
    //Instrumental Test
    androidTestImplementation (Deps.InstrumentalTest.espresso)
    androidTestImplementation (Deps.InstrumentalTest.testRunner)
    androidTestImplementation (Deps.InstrumentalTest.testCore)
    androidTestImplementation (Deps.InstrumentalTest.jUnit)
    androidTestImplementation (Deps.InstrumentalTest.testRules)
    androidTestImplementation(Deps.TestAndroid.MockkAndroid)
    androidTestImplementation(Deps.TestAndroid.MockkAgent)
}