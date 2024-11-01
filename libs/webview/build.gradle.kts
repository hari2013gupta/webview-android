plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    `maven-publish`
}
group = "com.harry"
version = "1.0"
// https://developer.android.com/build/publish-library/upload-library
// Publish with   ./gradlew clean build publish
android {
    namespace = "com.harry.webview"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        aarMetadata {
            minCompileSdk = 29
        }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

//    productFlavors {
//        register("release") {
//            aarMetadata {
//                minCompileSdk = 30
//            }
//        }
//    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
//        multipleVariants("custom") {
//            includeBuildTypeValues("debug", "release")
//            includeFlavorDimensionAndValues(
//                dimension = "color",
//                values = arrayOf("blue", "pink")
//            )
//            includeFlavorDimensionAndValues(
//                dimension = "shape",
//                values = arrayOf("square")
//            )
//        }
    }
    testFixtures {
        enable = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

//    publishing {
//        singleVariant("release") {
//            // if you don't want sources/javadoc, remove these lines
//            withSourcesJar()
//            withJavadocJar()
//        }
//    }
//    publishing.publications {
//        register<MavenPublication>("aar") {
//            groupId = "com.foo"
//            artifactId = "bar"
//            version = "0.1"
//
//            artifact("$buildDir/outputs/aar/bar-release.aar")
//
//            pom.withXml {
//                val dependencies = asNode().appendNode("dependencies")
//
//                val addNode = { groupId: String, artifactId: String, version: String ->
//                    val dependency = dependencies.appendNode("dependency")
//                    dependency.appendNode("groupId", groupId)
//                    dependency.appendNode("artifactId", artifactId)
//                    dependency.appendNode("version", version)
//                }
//
//                addNode("com.example", "dependency-name", "1.0")
//            }
//        }
//    }
}

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "com.harry"
            artifactId = "webview-lib"
            version = "1.0"
//            artifact("${layout.buildDirectory}/outputs/aar/bar-release.aar")
//            afterEvaluate {
//                from(components["release"])
//            }
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name = "WebView Library"
                description = "A concise description of my library"
                url = "https://github.com/hari2013gupta/webview-android"
                properties = mapOf(
                    "myProp" to "value",
                    "prop.with.dots" to "anotherValue"
                )
                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }
                developers {
                    developer {
                        id = "hari2013gupta"
                        name = "Harry"
                        email = "hk.mca08@gmail.com"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/hari2013gupta/webview-android.git"
                    developerConnection =
                        "scm:git:ssh://github.com/hari2013gupta/webview-android.git"
                    url = "https://github.com/hari2013gupta/webview-android"
//                    connection = "scm:svn:http://subversion.example.com/svn/project/trunk/"
//                    developerConnection = "scm:svn:https://subversion.example.com/svn/project/trunk/"
//                    url = "http://subversion.example.com/svn/project/trunk/"
                }
            }
        }
    }
    repositories {
        maven {
            // change URLs to point to your repos, e.g. http://my.org/repo
            name = "webview-lib"
            val releasesRepoUrl = uri(layout.buildDirectory.dir("repos/releases"))
            val snapshotsRepoUrl = uri(layout.buildDirectory.dir("repos/snapshots"))
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
//                url = uri(layout.buildDirectory.dir("https://github.com/hari2013gupta/webview-android.git"))
//                url = uri(if (project.hasProperty("release")) releasesRepoUrl else snapshotsRepoUrl)
        }
    }
}

//publishing {
//    publications {
//        // ... artifact publications
//
//        // Specify relocation POM
//        create<MavenPublication>("relocation") {
//            pom {
//                // Old artifact coordinates
//                groupId = "com.example"
//                artifactId = "lib"
//                version = "2.0.0"
//
//                distributionManagement {
//                    relocation {
//                        // New artifact coordinates
//                        groupId = "com.new-example"
//                        artifactId = "lib"
//                        version = "2.0.0"
//                        message = "groupId has been changed"
//                    }
//                }
//            }
//        }
//    }
//}
tasks.withType<GenerateModuleMetadata> {
    // The value 'enforced-platform' is provided in the validation
    // error message you got
    suppressedValidationErrors.add("enforced-platform")
}
tasks.register<Zip>("generateRepo") {
    val publishTask = tasks.named(
        "publishReleasePublicationToMyrepoRepository",
        PublishToMavenRepository::class.java
    )
    from(publishTask.map { it.repository.url })
    into("mylibrary")
    archiveFileName.set("mylibrary.zip")
}
dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.androidx.webkit)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
