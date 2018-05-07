package com.apppoweron.miband2control.ui.common.view.progresstext

import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ProgressBar


class ProgressBarAnimation(private val progressBar: ProgressBar, private val from: Int, private val to: Int) : Animation() {

    //https://stackoverflow.com/questions/48599237/how-to-set-smoothly-animation-to-progressbar-in-android

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        super.applyTransformation(interpolatedTime, t)
        val value = from + (to - from) * interpolatedTime
        progressBar.progress = value.toInt()
    }

}