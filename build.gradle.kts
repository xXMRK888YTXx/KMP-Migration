// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    id("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false

}
buildscript {
    dependencies {
        classpath("dev.icerock.moko:resources-generator:0.23.0")
    }
}