package net.sarazan.ankomation

import android.animation.Animator
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Interpolator

/**
 * Created by Aaron Sarazan on 9/17/16
 */

abstract class Ankomation(val parent: AnkomationSet?, val view: View? = null)  {

    companion object {

        val defaultDuration = 200L
        val defaultInterpolator = AccelerateDecelerateInterpolator()

        fun create(fn: AnkomationSet.() -> Unit) {
            AnkomationSet(null).apply {
                this.fn()
                this.start()
            }
        }
    }

    var delay: Long? = null
    val resolvedDelay: Long by lazy {
        delay ?: parent?.delay ?: 0L
    }

    var duration: Long? = null
    val resolvedDuration: Long by lazy {
        duration ?: parent?.duration ?: Ankomation.defaultDuration
    }

    var interpolator: Interpolator? = null
    val resolvedInterpolator: Interpolator by lazy {
        interpolator ?: parent?.interpolator ?: Ankomation.defaultInterpolator
    }

    protected fun animate(): ViewPropertyAnimator {
        return view!!.animate()
                .setStartDelay(resolvedDelay)
                .setDuration(resolvedDuration)
                .setInterpolator(resolvedInterpolator)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator) {}
                    override fun onAnimationRepeat(animation: Animator) {}
                    override fun onAnimationCancel(animation: Animator) {}
                    override fun onAnimationEnd(animation: Animator) {
                        parent?.onChildComplete(this@Ankomation)
                    }
                })
    }

    abstract fun start()
}

