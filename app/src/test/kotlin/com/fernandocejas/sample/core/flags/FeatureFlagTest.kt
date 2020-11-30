/**
 * Copyright (C) 2020 Fernando Cejas Open Source Project
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
package com.fernandocejas.sample.core.flags

import com.fernandocejas.sample.UnitTest
import io.mockk.Called
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class FeatureFlagTest : UnitTest() {

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

    @Test
    fun `given a feature flag, when it is deactivated, then does not execute given logic block`() {
        val inactiveFlag = InactiveFeatureFlag()
        val fakeNavigator = mockk<Navigator>(relaxed = true)

        inactiveFlag whenActivated {
            fakeNavigator.doSomething()
            fakeNavigator.navigateToActiveFeature()
            fakeNavigator.doSomething()
        }

        verify { fakeNavigator wasNot Called }
    }

    @Test
    fun `given a feature flag, when it is activated, then does not execute given "otherwise" logic block`() {
        val activeFlag = ActiveFeatureFlag()
        val fakeNavigator = mockk<Navigator>(relaxed = true)

        activeFlag whenActivated {
            fakeNavigator.doSomething()
            fakeNavigator.navigateToActiveFeature()
            fakeNavigator.doSomething()
        } otherwise {
            fakeNavigator.navigateToDefaultScreen()
        }

        verify(exactly = 1) { fakeNavigator.navigateToActiveFeature() }
        verify(exactly = 2) { fakeNavigator.doSomething() }
        verify(inverse = true) { fakeNavigator.navigateToDefaultScreen() }
    }

    @Test
    fun `given a feature flag, when it is deactivated, then execute given "otherwise" logic block`() {
        val inactiveFlag = InactiveFeatureFlag()
        val fakeNavigator = mockk<Navigator>(relaxed = true)

        inactiveFlag whenActivated {
            fakeNavigator.doSomething()
            fakeNavigator.navigateToActiveFeature()
            fakeNavigator.doSomething()
        } otherwise {
            fakeNavigator.navigateToDefaultScreen()
        }

        verify(inverse = true) { fakeNavigator.navigateToActiveFeature() }
        verify(inverse = true) { fakeNavigator.doSomething() }
        verify(exactly = 1) { fakeNavigator.navigateToDefaultScreen() }
    }

    private class ActiveFeatureFlag : FeatureFlag(enabled = true)
    private class InactiveFeatureFlag : FeatureFlag(enabled = false)

    private class Navigator {
        fun doSomething() { this.hashCode() }
        fun navigateToActiveFeature() { this.hashCode() }
        fun navigateToDefaultScreen() { this.hashCode() }
    }
}
