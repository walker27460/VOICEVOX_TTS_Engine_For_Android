
import kotlinx.coroutines.*
import java.io.BufferedOutputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "dev.ztssst.voicevox_tts"
    compileSdk = 35

    defaultConfig {
        applicationId = "dev.ztssst.voicevox_tts"
        minSdk = 29
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}
fun ZipInputStream.forEach(fn: (ZipEntry)->Unit){
    var entry = nextEntry
    while (entry != null){
        fn(entry)
        entry = nextEntry
    }
}

task("downloadVoicevox") {
    val voicevoxZip = uri("https://github.com/VOICEVOX/voicevox_core/releases/download/${project.libs.voicevox.core.get().version}/java_packages.zip")
    ZipInputStream(voicevoxZip.toURL().openStream()).use { zis -> zis.forEach {
        val dest = File(project.repositories.mavenLocal().url.path, it.name)
        it.isDirectory && (dest.exists() || dest.mkdirs()) || run {
            zis.copyTo(BufferedOutputStream(FileOutputStream(dest)))
            dest.parentFile?.mkdirs() == true
        }
    }
    }
}
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.media3.common.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // onnx runtime
    implementation(libs.onnxruntime.android)

    // voicevox runtime
    implementation(libs.voicevox.core)

    // gson
    implementation(libs.gson)
}