# android-trinity
This is tiny framework with much of the scaffolding code (with some nice utilities and prepared source code) required to start a new **Android Application.** 

## Background

Yes, android studio might create a project for you, but there are missing parts, which I **ALWAYS** end up adding to every single project I create. This tiny Framework (I would say still scaffolding code) aims to solve this by making easy and fast to create an android project with the fundamental building blocks. Check the **TODO** section below for more information on the evolution and progress. 

## What does android-trinity provide?

**Freedom.** It uses standard tools but organized in such a way that facilitates android development. You can refactor or change anything but the idea is to have a robust starting point. All the wiring is done for you with the flexibility of being modified according to your needs.   

 - `Kotlin DSL` by default at Gradle build system leve.
 - Static Analisis Code Report (Detekt).
 - Code Coverage Report (Jacoco).
 - Base Classes: `BaseActivity` and `BaseFragments`.
 - Toolbar with styles pre-defined.
 - Pre-setup Test clases: `UnitTest`, `AndroidTest`, `AcceptanceTest`.
 - Pre-compiled Gradle Scripts (`buildScr folder`) organized by aspects:
    - `compilation.gradle.kts`
    - `infrastructure.gradle.kts`
    - `quality.gradle.kts`
    - `variants.gradle.kts`
 - Dependencies Management in one place: `Dependencies.kt`
 - LeakCanary for leaks detection.
 - Pre-defined Build Variants (check `variants.gradle.kts` file)
    - Build Types:
        - `DEBUG`
        - `RELEASE`
    - Product Flavors:
        - `DEV`
        - `INTERNAL`
        - `PUBLIC`
 - Kotlin Coroutines

## What does android-trinity NOT INCLUDE? (likely to change if necessary in future versions)

 - DI. You should choose your Dependency Injection Framework.
 - N0 Navigation Framework. 
 - NO `LiveData` and `ViewModel` libraries since some Engineers might use other approaches at UI level like MVI, MVP, etc.
 - NO Kotlin Flow.
 - NO UI Layouts for Tablet or Android TV support. 

## Pre-defined Gradle Tasks

 - `./gradlew runStaticAnalysis`
 - `./gradlew runTestCoverage`
 - `./gradlew runUnitTests`
 - `./gradlew runAcceptanceTests`
 - `./gradlew compileApp`
 - `./gradlew runApp`
 
## How to use it

At the time being there are a couple of manual steps involved since we are at a very early stage. But we believe in sofware evolution right? So stay tuned and check both the **How to Contribute** and **TODO List** sections.

 - **STEP 1:** Clone this repo.
 - **STEP 2:** Delete the `.git` folder so you can add your files to any repo of your own.
 - **STEP 3:** Import the project in Android Studio
 - **STEP 4:** Rename packages if you need. `com.fernandocejas.sample` by default. 

## Using Test Helpers

Let's say you want to write tests (and you should ALWAYS do), As mentioned there are 3 classes which come into play and here examples of their usage:
 
- `UnitTest.kt`: Unit Test base class which setup mocks for you (You only use the `@Mockk` annotation)

```kotlin 
TODO()
```

- `AndroidTest.kt`: Integration Test base class which setup mocks for you (You only use the `@Mockk` annotation). You might use this classes when they are Android Components involved. It is backed up by [Robolectric]().

```kotlin 
TODO()
```

- `AcceptanceTest.kt`: UI Test base class which setup [Espresso] for you

```kotlin 
TODO()
```

## Quality Reports: Static Analysis

TODO(): Config folder

## Quality Reports: Code Coverage

TODO(): Config folder

## How to Contribute

Nothing is set in stone here and things can change and evolve based on the community work and requirements. So if you want to contribute, feel free to open an [issue]() and label it properly with a good description of the **Bug**, **Enhancement**, etc 

## TODO List

- [ ] Gradle Tasks for Publishing to Google Play: [App Bundles]().
- [ ] Automate the process from **How to Use** section: Idea: `./gradlew setupProject`
- [ ] Local Feature Flags.
- [ ] ???
- [ ] ???

## License

    Copyright 2018 Fernando Cejas

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


![http://www.fernandocejas.com](https://github.com/android10/Sample-Data/blob/master/android10/android10_logo_big.png)

<a href="https://www.buymeacoffee.com/android10" target="_blank"><img src="https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png" alt="Buy Me A Coffee" style="height: auto !important;width: auto !important;" ></a>