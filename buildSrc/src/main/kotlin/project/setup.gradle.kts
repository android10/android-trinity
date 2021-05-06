/**
 * Copyright (C) 2021 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package project

import java.io.File
import java.io.File.separator as SEPARATOR

/**
 * ATTENTION: This file is meant to setup the project after being cloned
 * from this template: https://github.com/android10/android-trinity
 *
 * THIS IS A BIT HACKY (NOT PROUD OF IT) BUT IT SERVES ITS PURPOSE.
 * IT CAN BE SAFELY REMOVED AFTERWARDS IF NOT REMOVED AUTOMATICALLY.
 */


val dirCom = "com"
val dirFernandoCejas = "fernandocejas"
val dirSample = "sample"
val defaultPackageName = "${dirCom}.${dirFernandoCejas}.${dirSample}"

val projectPath: String = rootProject.rootDir.absolutePath

val appPath = File(projectPath.plus(SEPARATOR)
        .plus("app"))
val buildSrcPath = File(projectPath.plus(SEPARATOR)
        .plus("buildSrc").plus(SEPARATOR)
        .plus("src"))
val currentDirPath = File(buildSrcPath.absolutePath.plus(SEPARATOR)
        .plus("main").plus(SEPARATOR)
        .plus("kotlin").plus(SEPARATOR)
        .plus("project"))
val directoryList = listOf(appPath ,buildSrcPath)

val appSrcPath = File(projectPath.plus(SEPARATOR)
        .plus("app").plus(SEPARATOR)
        .plus("main").plus(SEPARATOR)
        .plus("kotlin"))

tasks.register("setupProject") {
    description = "Sets up the project. A valid package name should be passed as argument"

    // Check valid package name
    if (validatePackageName("com.example.android")) {
        directoryList.map { directory ->
            // Change package name in source code
            directory
                    .walk()
                    .filter { filterFile(it) }
                    .filter { filterContent(it) }
                    .forEach { println(it) }
            //.forEach { it.readText().replace(defaultPackageName, "newPackageName".toLowerCase()) }
        }

        // Rename package directories. TODO
        val directories = "com.example.android".split(".")
        val dirOne = File(appSrcPath.absolutePath.plus(SEPARATOR)
                .plus(dirCom).plus(SEPARATOR)
                .plus(dirFernandoCejas).plus(SEPARATOR)
                .plus(dirSample).plus(SEPARATOR))
        val dirTwo = File(appSrcPath.absolutePath.plus(SEPARATOR)
                .plus(dirCom).plus(SEPARATOR)
                .plus(dirFernandoCejas).plus(SEPARATOR))
        val dirThree = File(appSrcPath.absolutePath.plus(SEPARATOR)
                .plus(dirCom).plus(SEPARATOR))

        println("Rename this directory --> ${dirOne.absolutePath} with --> ${directories[2]}")
        println("Rename this directory --> ${dirTwo.absolutePath} with --> ${directories[1]}")
        println("Rename this directory --> ${dirThree.absolutePath} with --> ${directories[0]}")

        // Clean up code by removing leftover from this script. TODO
        val buildGradleFile = File(projectPath.plus(SEPARATOR).plus("build.gradle.kts"))
        println("Remove this dependency from file --> ${buildGradleFile.absolutePath}")
        println("Remove this directory --> ${currentDirPath.absolutePath}")

    } else {
        println("Wrong PACKAGE NAME. Example: 'com.yourname.android'")
    }
}

fun filterFile(file: File): Boolean {
    if (file.name.startsWith("setup.gradle")) return false

    if (file.name.startsWith("AndroidManifest")) return true
    if (file.extension == "kts" || file.extension == "kt") return true

    return false
}

fun filterContent(file: File): Boolean {
    if (file.readText().contains(defaultPackageName)) return true
    return false
}

fun validatePackageName(packageName: String): Boolean {
    // This regex ensures that the package name:
    // - can only be formed by letters, digits, dots and underscores.
    // - must always start with a word (or with an underscore), but never with a digit or a dot.
    // - can optionally contain a sequence of a pattern made by: a dot, followed by a word that
    //   can start with letters and underscore but not with digits.
    // - must always end with a non-dot character.
    // - must start with a lowercase letter by convention.
    val regex = "(^(?:[a-z_]+(?:\\d*[a-zA-Z_]*)*)(?:\\.[a-z_]+(?:\\d*[a-zA-Z_]*)*)*\$)".toRegex()

    // force to match the regex and at least the package name should be: com.example.android.
    return (regex.matches(packageName) && packageName.trim().split(".").size == 3)
}

open class ConfigTask: DefaultTask() {
    @get:Input
    var packageName = ""

    @TaskAction
    fun setupProject() {
        println("Package name parameter --> $packageName")
    }
}

tasks.register("configProject", ConfigTask::class) {
    description = "Sets up the project. A valid package name should be passed as argument"
}
