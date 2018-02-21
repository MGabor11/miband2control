package com.apppoweron.miband2control.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.apppoweron.miband2control.R
import com.apppoweron.miband2control.ui.BaseActivity
import com.apppoweron.miband2control.ui.MainActivity


class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    private val SPLASH_DURATION = 2000

    private var mSplashDurationHandler: Handler? = null

    override fun onResume() {
        super.onResume()
        if (mSplashDurationHandler == null) {
            mSplashDurationHandler = Handler()
        }

        mSplashDurationHandler?.postDelayed({
            // Start home activity
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))

            // close splash activity
            finish()
        }, SPLASH_DURATION.toLong())
    }

    override fun onPause() {
        super.onPause()
        mSplashDurationHandler?.removeCallbacksAndMessages(null)
    }

}