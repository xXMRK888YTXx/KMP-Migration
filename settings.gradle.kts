enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
rootProject.name = "SecureSpace"
include(":androidApp")
include(":CoreAndroid")
include(":CoreCompose")
include(":CalculatorScreen")
include(":LoginInSecureSpaceScreen")
include(":FirstConfigurationScreen")
include(":PreferencesStorage")
include(":AndroidCryptoManager")
include(":PasswordCryptoManager")
include(":SecureSpaceMainScreen")
include(":shared")
