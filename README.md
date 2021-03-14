# android-trinity
This is tiny framework with much of the scaffolding code (with some nice utilities and prepared source code) required to start a new **Android Application.** 

## Background 📦

Yes, android studio might create a project for you, but there are missing parts, which I **ALWAYS** end up adding to every single project I create. This tiny Framework (I would say still scaffolding code) aims to solve this by making easy and fast to create an android project with the fundamental building blocks. Check the **TODO** section below for more information on the evolution and progress. 

## What does android-trinity provide? 🎨

**Freedom.** It uses standard tools but organized in such a way that facilitates android development. You can refactor or change anything but the idea is to have a robust starting point. All the wiring is done for you with the flexibility of being modified according to your needs.   

 - `Kotlin DSL` by default at Gradle build system leve.
 - Static Analisis Code Report (Detekt).
 - Code Coverage Report (Jacoco).
 - Base Classes: [`BaseActivity`](https://github.com/android10/android-trinity/blob/main/app/src/main/kotlin/com/fernandocejas/sample/core/platform/BaseActivity.kt) and [`BaseFragments`](https://github.com/android10/android-trinity/blob/main/app/src/main/kotlin/com/fernandocejas/sample/core/platform/BaseFragment.kt).
 - Toolbar with styles pre-defined.
 - Pre-setup Test clases: [`UnitTest`](https://github.com/android10/android-trinity/blob/main/app/src/test/kotlin/com/fernandocejas/sample/UnitTest.kt), [`AndroidTest`](https://github.com/android10/android-trinity/blob/main/app/src/test/kotlin/com/fernandocejas/sample/AndroidTest.kt), [`AcceptanceTest`](https://github.com/android10/android-trinity/blob/main/app/src/androidTest/kotlin/com/fernandocejas/sample/AcceptanceTest.kt).
 - Pre-compiled Gradle Scripts (`buildScr` folder) organized by aspects:
    - [`compilation.gradle.kts`](https://github.com/android10/android-trinity/blob/main/buildSrc/src/main/kotlin/scripts/compilation.gradle.kts)
    - [`infrastructure.gradle.kts`](https://github.com/android10/android-trinity/blob/main/buildSrc/src/main/kotlin/scripts/infrastructure.gradle.kts)
    - [`quality.gradle.kts`](https://github.com/android10/android-trinity/blob/main/buildSrc/src/main/kotlin/scripts/quality.gradle.kts)
    - [`variants.gradle.kts`](https://github.com/android10/android-trinity/blob/main/buildSrc/src/main/kotlin/scripts/variants.gradle.kts)
 - Dependencies Management in one place: [`Dependencies.kt`](https://github.com/android10/android-trinity/blob/main/buildSrc/src/main/kotlin/Dependencies.kt)
 - LeakCanary for leaks detection.
 - Pre-defined Build Variants (check [`variants.gradle.kts`](https://github.com/android10/android-trinity/blob/main/buildSrc/src/main/kotlin/scripts/variants.gradle.kts) file)
    - Build Types:
        - `DEBUG`
        - `RELEASE`
    - Product Flavors:
        - `DEV`
        - `INTERNAL`
        - `PUBLIC`
 - Kotlin Coroutines
 - A [Compact Feature Flags Framework](https://github.com/android10/android-trinity/pull/1)

## What does android-trinity NOT INCLUDE? (likely to change if necessary in future versions)

 - DI. You should choose your Dependency Injection Framework.
 - NO Navigation Framework. 
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
 
## How to use it 👣

At the time being there are a couple of manual steps involved since we are at a very early stage. But we believe in sofware evolution right? So stay tuned and check both the **How to Contribute** and **TODO List** sections.

 - **STEP 1:** Click on [![Use this template](https://img.shields.io/badge/-Use%20this%20template-brightgreen)](https://github.com/android10/android-trinity/generate) to create a new repo. 
 - **STEP 2:** Import the project in Android Studio
 - **STEP 3:** Rename packages according to your needs. `com.fernandocejas.sample` by default. 

## Using Test Helpers

Let's say you want to write tests (and you should **ALWAYS** do), As mentioned there are 3 classes which come into play and here examples of their usage:
 
- `UnitTest.kt`: Unit Test base class which setup mocks for you (You only use the `@Mockk` annotation)

```kotlin 
class FeatureFlagTest : UnitTest() {

    @Mockk prival val yourMock

    @Test
    fun `given a feature flag, when it is activated, then executes given logic block`() {
        val activeFlag = ActiveFeatureFlag()
        val fakeNavigator = mockk<Navigator>(relaxed = true)

        activeFlag whenActivated {
            fakeNavigator.doSomething()
            fakeNavigator.navigateToActiveFeature()
            fakeNavigator.doSomething()
        }

        verify(exactly = 1) { fakeNavigator.navigateToActiveFeature() }
        verify(exactly = 2) { fakeNavigator.doSomething() }
    }
```

- `AndroidTest.kt`: Integration Test base class which setup mocks for you (You only use the `@Mockk` annotation). You might use this classes when they are Android Components involved. It is backed up by [Robolectric](https://github.com/robolectric/robolectric).

```kotlin 
class YourTestClass : AndroidTest() {

    @Mockk prival val yourMock

    @Test
    fun `given something, when something happens, then do something`() {
        TODO()
    }
```

- `AcceptanceTest.kt`: UI Test base class which setup [Espresso](https://developer.android.com/training/testing/espresso) for you

```kotlin 
class MainActivityTest: AcceptanceTest(MainActivity::class.java) {

    @Test
    fun checkToolBarTest() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
    }
}
```

## Feature Flags

Android-trinity includes a simple offline feature flags framework with a very simple api generated at compile time. 
If you want more information, refer to the [introduced pull request](https://github.com/android10/android-trinity/pull/1). (TODO: Add more documentation)

Example of its usage:
```kotlin
Flag.Hello whenActivated { displayGreeting(R.string.hello) }
```


## Quality Reports: Static Analysis

 - The tool chosen here is [Detekt](https://github.com/detekt/detekt) due to its nature for Kotlin. 
 - The gradlew task and its configuration could be found inside [`quality.gradle.kts`](https://github.com/android10/android-trinity/blob/main/buildSrc/src/main/kotlin/scripts/quality.gradle.kts) file.
 - cconfiguration Rules are in [config/detekt/detekt.yml](https://github.com/android10/android-trinity/blob/main/config/detekt/detekt.yml).

## Quality Reports: Code Coverage

- The tool chosen here is [Jacoco](https://github.com/jacoco/jacoco) due to its nature and popularity in the community. 
 - The gradlew task and its configuration could be found inside [`quality.gradle.kts`](https://github.com/android10/android-trinity/blob/main/buildSrc/src/main/kotlin/scripts/quality.gradle.kts) file.

## TODO List

- [ ] Gradle Tasks for Publishing to Google Play: [App Bundles](https://developer.android.com/guide/app-bundle).
- [ ] Automate the process from **How to Use** section: Idea: `./gradlew setupProject`
- [X] Local Feature Flags.
- [ ] Rename default packages to `io.android-trinity` or `io.android.trinity`.
- [ ] ???
- [ ] ???

## How to Contribute

Nothing is set in stone here and things can change and evolve based on the community work and requirements. So if you want to contribute, feel free to open an [issue](https://github.com/android10/android-trinity/issues) and label it properly: **Bug**, **Enhancement**, etc.. or send a [PR](https://github.com/android10/android-trinity/pulls). Please both with a good descriptions of the intention, in order to facilitate review

## License

    Copyright 2021 Fernando Cejas

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
