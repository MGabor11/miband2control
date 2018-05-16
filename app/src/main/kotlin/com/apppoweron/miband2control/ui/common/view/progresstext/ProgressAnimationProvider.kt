package com.apppoweron.miband2control.ui.common.view.progresstext

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Build
import android.view.ViewTreeObserver
import android.view.animation.LinearInterpolator
import android.widget.ProgressBar


class ProgressAnimationProvider(private val progressBar: ProgressBar, private val onAnimEnded: (() -> Unit?)? = null) {

    private var isFirstAnimStarted: Boolean = false

    private fun setUpObserver(onAnimEnded: () -> Unit?) {
        progressBar.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            @SuppressLint("ObsoleteSdkInt")
            override fun onGlobalLayout() {
                isFirstAnimStarted = true
                startAnimation(onAnimEnded)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    progressBar.viewTreeObserver.removeOnGlobalLayoutListener(this)
                } else {
                    progressBar.viewTreeObserver.removeGlobalOnLayoutListener(this)
                }
            }
        })
    }

    fun setProgress(percentage: Int) {
        //TODO
    }

    fun setProgressValue(value: Int) {
        //TODO
    }

    fun startAnimation(percentage: Int) {

    }

    fun startAnimation(onAnimEnded: () -> Unit?) {
        if (!isFirstAnimStarted) {
            setUpObserver(onAnimEnded)
            return
        }

        val width = progressBar.width
        progressBar.max = width

        val animator = ValueAnimator.ofInt(width, 0)
        animator.interpolator = LinearInterpolator()
        animator.startDelay = 0
        animator.duration = 7000
        animator.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            progressBar.progress = value
        }
        animator.addListener (animationEnd = {
            onAnimEnded()
        }, animationCancel = {
            onAnimEnded()
        })

        animator.start()
    }

    private inline fun ValueAnimator.addListener (
            crossinline animationStart: (Animator) -> Unit = {},
            crossinline animationRepeat: (Animator) -> Unit = {},
            crossinline animationCancel: (Animator) -> Unit = {},
            crossinline animationEnd: (Animator) -> Unit = {}) {

        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                animationStart(animation)
            }

            override fun onAnimationRepeat(animation: Animator) {
                animationRepeat(animation)
            }

            override fun onAnimationCancel(animation: Animator) {
                animationCancel(animation)
            }

            override fun onAnimationEnd(animation: Animator) {
                animationEnd(animation)
            }
        })
    }

}




