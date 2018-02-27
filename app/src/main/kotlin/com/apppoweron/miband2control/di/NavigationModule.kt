package com.apppoweron.miband2control.di

import com.apppoweron.miband2control.ui.NavigationController
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class NavigationModule {

    @Provides
    @Singleton
    fun provideNavigationController(): NavigationController {
        return NavigationController();
    }

}