plugins {
  id("project.setup") // This will be removed automatically after `./gradlew setupProject `
  id(ScriptPlugins.infrastructure)
}

buildscript {
  repositories {
    google()
    jcenter()
  }

  dependencies {
    classpath (BuildPlugins.androidGradlePlugin)
    classpath (BuildPlugins.kotlinGradlePlugin)
  }
}

allprojects {
  repositories {
    google()
    jcenter()
  }
}
