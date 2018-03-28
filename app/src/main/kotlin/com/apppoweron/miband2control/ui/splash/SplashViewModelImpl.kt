package com.apppoweron.miband2control.ui.splash

import android.databinding.ObservableField
import android.os.CountDownTimer
import com.apppoweron.miband2control.ui.common.BaseViewModelImpl
import javax.inject.Inject


class SplashViewModelImpl @Inject constructor(): BaseViewModelImpl(), SplashViewModel {

    private val SPLASH_DURATION = 3000

    val currentCountDownTimeObservableField = ObservableField<String>()
    var currentCountDownTime: Long = SPLASH_DURATION.toLong()

    private var mSplashDurationTimer: CountDownTimer? = null



    override fun startCountDown() {
        mSplashDurationTimer = object : CountDownTimer(currentCountDownTime, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                currentCountDownTimeObservableField.set("" + millisUntilFinished)
                currentCountDownTime = millisUntilFinished
            }

            override fun onFinish() {
                //listener.onSplashDurationExpired()
                navigationController?.navigateToMainActivity()
            }

        }.start()
    }

    override fun stopCountDown() {
        mSplashDurationTimer?.cancel()
        mSplashDurationTimer = null
    }
}
