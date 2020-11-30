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
import scripts.Variants_gradle.ProductFlavors

/**
 * By convention use the prefix FEATURE_ for every
 * defined functionality that will be under a feature flag.
 */
enum class Features {
    FEATURE_HELLO,
    FEATURE_BYE
}

/**
 * Defines a map for activated flags per product flavor.
 */
object FeatureFlags {
    val activated = mapOf(

        //Enabled Features for DEV Product Flavor
        ProductFlavors.DEV to setOf(
            Features.FEATURE_HELLO,
            Features.FEATURE_BYE),

        //Enabled Features for INTERNAL Product Flavor
        ProductFlavors.INTERNAL to setOf(
            Features.FEATURE_BYE),

        //Enabled Features for PUBLIC Product Flavor
        ProductFlavors.PUBLIC to setOf(
            Features.FEATURE_HELLO))
}
