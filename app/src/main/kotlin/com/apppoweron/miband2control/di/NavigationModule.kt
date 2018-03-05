package com.apppoweron.miband2control.di

import android.app.Activity
import com.apppoweron.miband2control.ui.navigationcontroller.NavigationController
import com.apppoweron.miband2control.ui.navigationcontroller.NavigationControllerImpl
import dagger.Module
import dagger.Provides

@Module
internal class NavigationModule {

    @Provides
    internal fun provideNavigationController(activity: Activity): NavigationController {
        return NavigationControllerImpl(activity)
    }

}