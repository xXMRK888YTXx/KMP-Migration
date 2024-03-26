enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://plugins.gradle.org/m2/")
    }
}
rootProject.name = "SecureSpace"
include(":multiplatformApp")
include(":CoreAndroid")
include(":CoreCompose")
include(":CalculatorScreen")
include(":LoginInSecureSpaceScreen")
include(":FirstConfigurationScreen")
include(":PreferencesStorage")
include(":CryptoManager")
include(":PasswordCryptoManager")
include(":SecureSpaceMainScreen")
include(":MathEngine")
include(":shared")

