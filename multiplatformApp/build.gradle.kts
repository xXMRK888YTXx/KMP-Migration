import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    id("com.google.devtools.ksp")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    jvm("desktop")
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        val desktopMain by getting
        
        androidMain.dependencies {
            implementation(project(ProjectModules.CalculatorScreen))
            implementation(project(ProjectModules.PreferencesStorage))
            implementation(project(ProjectModules.CryptoManager))
            implementation(project(ProjectModules.PasswordCryptoManager))
            implementation(project(ProjectModules.SecureSpaceMainScreen))

            //Navigation
            implementation(Deps.Compose.Navigation)

            //System Ui Controller
            implementation(Deps.Compose.SystemUiController)
            configurations["ksp"].dependencies.add(implementation(Deps.Dagger.DaggerKaptCompiler))
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(project(ProjectModules.FirstConfigurationScreen))
            implementation(project(ProjectModules.LoginInSecureSpaceScreen))
            implementation(project(ProjectModules.Shared))
            implementation(compose.components.uiToolingPreview)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}

android {
    namespace = "com.xxmrk888ytxx.securespace"
    compileSdk = Config.compileSdk

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.xxmrk888ytxx.securespace"
        minSdk = Config.minSdk
        targetSdk = Config.compileSdk
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
        resources.excludes.add("META-INF/*")
        resources.excludes.add("MR/ru/strings.xml")
        resources.excludes.add("MR/base/strings.xml")

    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
        implementation(project(ProjectModules.CoreCompose))
    }
}

compose.desktop {
    application {
        mainClass = "com.xxmrk888ytxx.securespace.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb,TargetFormat.Rpm)
            packageName = "com.xxmrk888ytxx.securespace"
            packageVersion = "1.0.0"
        }
    }
}