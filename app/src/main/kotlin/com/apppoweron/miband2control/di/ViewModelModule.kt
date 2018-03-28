package com.apppoweron.miband2control.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.apppoweron.miband2control.ui.splash.SplashViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModelImpl::class)
    internal abstract fun bindSplashViewModel(splashViewModel: SplashViewModelImpl): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: AppViewModelFactory) :  ViewModelProvider.Factory
}
