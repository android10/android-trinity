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
package com.fernandocejas.sample

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import com.fernandocejas.sample.core.flags.Flag
import com.fernandocejas.sample.core.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Example code of how Feature Flags can be used.
        Flag.Hello whenActivated { displayGreeting(R.string.hello) }
    }

    private fun displayGreeting(@StringRes resId: Int) {
        tv_greeting.text = getString(resId)
    }
}
