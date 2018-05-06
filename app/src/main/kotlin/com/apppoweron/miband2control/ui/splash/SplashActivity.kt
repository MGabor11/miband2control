package com.apppoweron.miband2control.ui.splash

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import com.apppoweron.miband2control.R
import com.apppoweron.miband2control.databinding.ActivitySplashBinding
import com.apppoweron.miband2control.ui.common.BaseActivity
import com.apppoweron.miband2control.ui.common.view.progresstext.ProgressTextView
import com.apppoweron.miband2control.ui.navigationcontroller.NavigationController
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class SplashActivity : BaseActivity(), HasSupportFragmentInjector {

    companion object {
        private const val TAG = "SplashActivity"
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var navigationControllerImpl: NavigationController

    private var mSplashViewModel: SplashViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        mSplashViewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModelImpl::class.java)
        binding.model = mSplashViewModel as SplashViewModelImpl
        mSplashViewModel?.navigationController = navigationControllerImpl
        val progressText: ProgressTextView = findViewById(R.id.splash_progress_tv)
        (mSplashViewModel as SplashViewModelImpl).getProgress().observe(this, Observer { progress ->  progressText.setProgress(progress!!)}) //TODO
        mSplashViewModel?.startCountDown()

    }

    override fun onDestroy() {
        mSplashViewModel?.stopCountDown()
        super.onDestroy()
    }


    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }
}