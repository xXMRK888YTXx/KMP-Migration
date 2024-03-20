plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id (Deps.Dagger.DaggerKaptPlugin)
}

android {
    namespace = "com.xxmrk888ytxx.securespace"
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.packageName
        minSdk = Config.minSdk
        targetSdk = Config.compileSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = Config.isR8ProGuardEnableForRelease
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = Config.isR8ProGuardEnableForDebug
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Config.sourceCompatibility
        targetCompatibility = Config.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Deps.Compose.ComposeKotlinCompiler
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(ProjectModules.CoreCompose))
    implementation(project(ProjectModules.CalculatorScreen))
    implementation(project(ProjectModules.LoginInSecureSpaceScreen))
    implementation(project(ProjectModules.FirstConfigurationScreen))
    implementation(project(ProjectModules.PreferencesStorage))
    implementation(project(ProjectModules.CryptoManager))
    implementation(project(ProjectModules.PasswordCryptoManager))
    implementation(project(ProjectModules.SecureSpaceMainScreen))

    kapt(Deps.Dagger.DaggerKaptCompiler)

    //Navigation
    implementation(Deps.Compose.Navigation)

    //System Ui Controller
    implementation(Deps.Compose.SystemUiController)
}