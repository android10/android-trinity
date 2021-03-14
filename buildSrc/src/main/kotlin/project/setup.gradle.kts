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

val defaultPackageName = "com.fernandocejas.sample"
val projectPath: String = rootProject.rootDir.absolutePath
val directoryList = listOf(
    File(projectPath.plus(SEPARATOR).plus("app")),
    File(projectPath.plus(SEPARATOR).plus("buildSrc").plus(SEPARATOR).plus("src")))

tasks.register("setupProject") {
    description = "Sets up the project. A valid package name should be passed as argument"

    // Check valid package name
    if (validatePackageName("com.java")) {
        directoryList.map { directory ->
            // Change package name in source code
            directory
                .walk()
                .filter { filterFile(it) }
                .filter { filterContent(it) }
                .forEach { println(it) }
                //.forEach { it.readText().replace(defaultPackageName, "newPackageName".toLowerCase()) }

            // Rename package directories


            // Clean up code by removing leftover from this script

        }
    } else {
        println("Wrong package name")
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
    return regex.matches(packageName)
}