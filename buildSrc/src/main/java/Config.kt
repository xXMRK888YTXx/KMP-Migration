import org.gradle.api.JavaVersion

object Config {
    const val compileSdk = 34
    const val minSdk = 24
    const val packageName = "com.xxmrk888ytxx.securespace"
    const val isR8ProGuardEnableForRelease = false
    const val isR8ProGuardEnableForDebug = false
    const val versionName = "develop"

    //JDK Config
    val sourceCompatibility = JavaVersion.VERSION_17
    val targetCompatibility = JavaVersion.VERSION_17
    const val jvmTarget = "17"
}