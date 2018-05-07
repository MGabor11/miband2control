package com.apppoweron.miband2control.ui.splash

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import android.os.CountDownTimer
import com.apppoweron.miband2control.ui.common.BaseViewModelImpl
import javax.inject.Inject


class SplashViewModelImpl @Inject constructor(): BaseViewModelImpl(), SplashViewModel {

    companion object {
        private const val SPLASH_DURATION = 7000
    }

    val currentCountDownTimeObservableField = ObservableField<String>()
    var currentCountDownTime: Long = SPLASH_DURATION.toLong()

    private val progress = MutableLiveData<Int?>()

    private var mSplashDurationTimer: CountDownTimer? = null

    override fun startCountDown() {
        mSplashDurationTimer = object : CountDownTimer(currentCountDownTime, 500) {

            override fun onTick(millisUntilFinished: Long) {
                currentCountDownTimeObservableField.set("" + millisUntilFinished)
                currentCountDownTime = millisUntilFinished
                progress.value = ((SPLASH_DURATION - currentCountDownTime.toInt()).toFloat() / SPLASH_DURATION * 100).toInt()
            }

            override fun onFinish() {
                navigationController?.navigateToMainActivity()
            }

        }.start()
    }

    override fun getProgress() : LiveData<Int?> {
        return progress
    }

    override fun stopCountDown() {
        mSplashDurationTimer?.cancel()
        mSplashDurationTimer = null
    }
}
