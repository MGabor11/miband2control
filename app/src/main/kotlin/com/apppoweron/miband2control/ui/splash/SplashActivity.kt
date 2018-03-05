package com.apppoweron.miband2control.ui.splash

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import com.apppoweron.miband2control.R
import com.apppoweron.miband2control.ui.BaseActivity
import com.apppoweron.miband2control.ui.navigationcontroller.NavigationController
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class SplashActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var navigationController: NavigationController

    private val SPLASH_DURATION = 2000
    private var mSplashDurationHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }



    override fun onResume() {
        super.onResume()
        if (mSplashDurationHandler == null) {
            mSplashDurationHandler = Handler()
        }

        mSplashDurationHandler?.postDelayed({
            // Start home activity
            //startActivity(Intent(this@SplashActivity, MainActivity::class.java))

            // close splash activity
            //finish()
            navigationController.navigateToMainActivity()
        }, SPLASH_DURATION.toLong())
    }

    override fun onPause() {
        super.onPause()
        mSplashDurationHandler?.removeCallbacksAndMessages(null)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

}