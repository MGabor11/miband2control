package com.apppoweron.miband2control.ui.common.view.progresstext



class ProgressAnimationProvider {

    private fun setUpObserver() {
        /*progressBar.getViewTreeObserver().addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                startAnimation()

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    progressBar.getViewTreeObserver().removeOnGlobalLayoutListener(this)
                } else {
                    progressBar.getViewTreeObserver().removeGlobalOnLayoutListener(this)
                }
            }
        })*/
    }

    private fun startAnimation() {
        /*val width = progressBar.getWidth()
        progressBar.setMax(width)

        val animator = ValueAnimator.ofInt(0, width)
        animator.interpolator = LinearInterpolator()
        animator.startDelay = 0
        animator.duration = 10000
        animator.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            progressBar.setProgress(value)
        }

        animator.start()*/
    }

}
