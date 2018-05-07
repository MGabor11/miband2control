package com.apppoweron.miband2control.ui.splash

import android.arch.lifecycle.LiveData
import com.apppoweron.miband2control.ui.common.BaseViewModel


interface SplashViewModel : BaseViewModel{
    fun startCountDown()
    fun stopCountDown()
    fun getProgress() : LiveData<Int?>
}
