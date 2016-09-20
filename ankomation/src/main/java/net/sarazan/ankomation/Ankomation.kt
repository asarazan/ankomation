package net.sarazan.ankomation

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Interpolator

/**
 * Created by Aaron Sarazan on 9/17/16
 */

abstract class Ankomation(val parent: AnkomationSet?, val view: View? = null)  {

    companion object {

        val defaultDuration = 200L
        val defaultInterpolator = AccelerateDecelerateInterpolator()

        fun start(context: Context, fn: AnkomationSet.() -> Unit): AnkomationSet {
            return AnkomationSet(context, null).apply {
                this.fn()
                this.start(0)
            }
        }
    }

    val root: AnkomationSet? by lazy {
        parent?.root
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

    protected fun animate(pass: Int): ValueAnimator {
        return ObjectAnimator.ofPropertyValuesHolder(ViewWrapper(view!!)).apply {
            startDelay = resolvedDelay
            duration = resolvedDuration
            interpolator = resolvedInterpolator
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {}
                override fun onAnimationRepeat(animation: Animator?) {}
                override fun onAnimationCancel(animation: Animator?) {}
                override fun onAnimationEnd(animation: Animator?) {
                    finish(pass)
                }
            })
        }
    }

    abstract fun onStart(pass: Int): Boolean
    fun start(pass: Int): Boolean {
        return onStart(pass)
    }

    open fun onFinish(pass: Int) {}
    fun finish(pass: Int) {
        if (!(parent?.ending ?: false)) {
            onFinish(pass)
            parent?.onChildComplete(this)
        }
    }

    fun runAnimator(animator: Animator) {
        parent!!.runAnimator(this, animator)
    }

    open fun onEnd() {}
    fun end() {
        onEnd()
    }
}

