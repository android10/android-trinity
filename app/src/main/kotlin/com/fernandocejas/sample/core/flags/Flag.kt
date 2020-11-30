package com.fernandocejas.sample.core.flags

import com.fernandocejas.sample.BuildConfig

/**
 * Feature flags states (activated/deactivated) can be used as conditionals.
 *
 * ### Example:
 *
 * ```Flag.Conversations whenActivated { fn } otherwise { fn }```
 */
internal sealed class Flag(enabled: Boolean) : FeatureFlag(enabled) {

    /**
     * Defined Feature Flags.
     * @see "FeatureFlags.kt" file for compile-time feature definition.
     */
    object Hello : Flag(BuildConfig.FEATURE_HELLO)
    object Bye : Flag(BuildConfig.FEATURE_BYE)
}
