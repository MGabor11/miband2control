package com.apppoweron.miband2control.ui.common.view.progresstext

import android.animation.Animator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Build
import android.view.ViewTreeObserver
import android.view.animation.LinearInterpolator
import android.widget.ProgressBar


class ProgressAnimationProvider(private val progressBar: ProgressBar, private var ) {

    fun setUpObserver() {
        progressBar.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            @SuppressLint("ObsoleteSdkInt")
            override fun onGlobalLayout() {
                startAnimation()

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    progressBar.viewTreeObserver.removeOnGlobalLayoutListener(this)
                } else {
                    progressBar.viewTreeObserver.removeGlobalOnLayoutListener(this)
                }
            }
        })
    }

    fun setProgress(percentage: Int) {

    }

    fun setProgressValue(value: Int) {

    }

    fun startAnimation(percentage: Int) {

    }

    fun startAnimation(onAnimEnded: () -> Unit?) {
        val width = progressBar.width
        progressBar.max = width

        val animator = ValueAnimator.ofInt(width, 0)
        animator.interpolator = LinearInterpolator()
        animator.startDelay = 0
        animator.duration = 10000
        animator.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            progressBar.progress = value
        }
        animator.setListener(animationEnd = {onAnimEnded()},
                animationCancel = {})

        animator.start()
    }

    private inline fun ValueAnimator.setListener(
            crossinline animationStart: (Animator) -> Unit = {},
            crossinline animationRepeat: (Animator) -> Unit = {},
            crossinline animationCancel: (Animator) -> Unit = {},
            crossinline animationEnd: (Animator) -> Unit = {}) {
    }
}


