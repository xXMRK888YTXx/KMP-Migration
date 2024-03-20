pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SecureSpace"
include(":app")
include(":CoreAndroid")
include(":CoreCompose")
include(":CalculatorScreen")
include(":LoginInSecureSpaceScreen")
include(":FirstConfigurationScreen")
include(":PreferencesStorage")
include(":AndroidCryptoManager")
include(":PasswordCryptoManager")
include(":SecureSpaceMainScreen")
