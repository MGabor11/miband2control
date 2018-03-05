/*
 * Copyright (C) 2017 The Android Open Source Project
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

package com.apppoweron.miband2control.ui.navigationcontroller

import android.app.Activity
import android.content.Intent
import com.apppoweron.miband2control.ui.MainActivity
import javax.inject.Inject


class NavigationControllerImpl @Inject constructor(private val activity: Activity) : NavigationController {

    override fun navigateToMainActivity() {
        // Start home activity
        activity.startActivity(Intent(activity, MainActivity::class.java))

        // close splash activity
        activity.finish()
    }

}
