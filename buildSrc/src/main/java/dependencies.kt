package com.samuelrmos.podcastapp.buildsrc

object Libs {

    const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.0-alpha08"

    object Accompanist {
        private const val version = "0.7.0"
        const val coil = "com.google.accompanist:accompanist-coil:$version"
        const val insets = "com.google.accompanist:accompanist-insets:$version"
        const val pager = "com.google.accompanist:accompanist-pager:$version"
    }

    object Kotlin {
        private const val version = "1.4.30"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Core {
        private const val version = "1.3.2"
        const val coreKtx = "androidx.core:core-ktx:$version"
    }

    object Coroutines {
        private const val version = "1.4.2"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Androidx {
        const val appcompat = "androidx.appcompat:appcompat:1.2.0"
        const val palette = "androidx.palette:palette:1.0.0"

        object AppCompat {
            private const val version = "1.2.0'"
            const val appcompat = "androidx.appcompat:appcompat:$version"
        }

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.3.0-alpha03"
        }

        object Constraint {
            const val constraintLayoutCompose =
                "androidx.constraintlayout:constraintlayout-compose:1.3.0-alpha03"
        }

        object Compose {

            private const val snapshot = ""
            const val version = "1.0.0-beta01"

            @get:JvmStatic
            val snapshotUrl: String
                get() = "https://androidx.dev/snapshots/builds/$snapshot/artifacts/repository/"

            const val material = "androidx.compose.material:material:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val ui = "androidx.compose.ui:ui:$version"
            const val materialExtended =
                "androidx.compose.material:material-icons-extended:$version"
        }

        object LifeCycle {
            private const val version = "2.3.0"
            const val viewModelCompose =
                "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha03"
            const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
        }
    }

    object MaterialDesign {
        private const val version = "1.3.0"
        const val materialDesign = "com.google.android.material:material:$version"
    }

    object OkHttp {
        private const val version = "4.9.0"
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val logging = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Room {
        private const val version = "2.2.5"
        const val runtime = "androidx.room:room-runtime:${version}"
        const val ktx = "androidx.room:room-ktx:${version}"
        const val compiler = "androidx.room:room-compiler:${version}"
    }

    object Rome {
        private const val version = "1.14.1"
        const val rome = "com.rometools:rome:$version"
        const val modules = "com.rometools:rome-modules:$version"
    }

    object Hilt {
        private const val hiltVersion = "2.33-beta"
        private const val hiltAndroidXVersion = "1.0.0-alpha01"
        const val hiltAndroid = "com.google.dagger:hilt-android:$hiltVersion"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"

        const val lifecycle = "androidx.hilt:hilt-lifecycle-viewmodel:$hiltAndroidXVersion"
        const val compiler = "androidx.hilt:hilt-compiler:$hiltAndroidXVersion"
        const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
    }
}