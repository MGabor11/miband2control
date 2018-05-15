package com.apppoweron.miband2control.ui.common.view.progresstext

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.apppoweron.miband2control.R
import com.apppoweron.miband2control.util.bind

class ProgressTextView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val progressText by bind<TextView>(R.id.progress_tv)
    private val hiderProgressBar by bind<ProgressBar>(R.id.progress_hider_view)

    //
    private var progressAnimationProvider: ProgressAnimationProvider? = null

    init {
        init(context)
    }

    private fun init(context: Context) {
        View.inflate(context, R.layout.view_progress_text, this)
        setHiderWidth(getSizeOfTextView())
    }

    private fun getSizeOfTextView(): Pair<Int, Int> {
        progressText.measure(0, 0)
        return Pair(progressText.measuredHeight, progressText.measuredWidth)
    }

    private fun setHiderWidth(dimensions: Pair<Int, Int>) {
        hiderProgressBar.layoutParams.height = dimensions.first
        hiderProgressBar.layoutParams.width = dimensions.second
        hiderProgressBar.rotation = 180f
        hiderProgressBar.requestLayout()
    }

    fun setProgress(percentage: Int?) {
        percentage?.let {
            //hiderProgressBar.progress = 100 - percentage
            val progress: Int = 100 - percentage
            val anim = ProgressBarAnimation(hiderProgressBar, hiderProgressBar.progress, progress)
            anim.duration = 500
            hiderProgressBar.startAnimation(anim)
        }
    }

    fun setProgress(percentage: Int?, duration: Long, onAnimEnded: () -> Unit?) {
        percentage?.let {
            getProgressAnimationProvider()?.startAnimation(onAnimEnded)
        }
    }

    private fun getProgressAnimationProvider(): ProgressAnimationProvider? {
        progressAnimationProvider ?: run {
            progressAnimationProvider = ProgressAnimationProvider(hiderProgressBar)
        }
        return progressAnimationProvider
    }

}
