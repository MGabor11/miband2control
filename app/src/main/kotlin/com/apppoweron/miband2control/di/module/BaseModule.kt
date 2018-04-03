package com.apppoweron.miband2control.di.module

import android.app.Activity

import dagger.Module
import dagger.Provides

@Module(includes = [(NavigationModule::class)])
abstract class BaseModule<in T : Activity> {

    @Provides
    internal fun provideActivity(activity: T): Activity {
        return activity
    }


}
