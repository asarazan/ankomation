package net.sarazan.ankomation

import android.view.View
import android.view.animation.Interpolator

/**
 * Created by Aaron Sarazan on 9/17/16
 */

abstract class TimeAnkomationImpl() : TimeAnkomation {
    override var duration: Long? = null
    override var interpolator: Interpolator? = null
}

class Visibility(override val view: View, val visibility: Int) : ViewAnkomation

class Rotate(override val view: View) : TimeAnkomationImpl(), ViewAnkomation {
    var from: Int? = null
    var to: Int? = null
}

class Scale(override val view: View) : TimeAnkomationImpl(), ViewAnkomation {
    var xFrom: Float? = null
    var xTo: Float? = null
    var yFrom: Float? = null
    var yTo: Float? = null
}

class Alpha(override val view: View) : TimeAnkomationImpl(), ViewAnkomation {
    var from: Float? = null
    var to: Float? = null

    override fun prepare() {
        from?.let { view.alpha = it }
    }

    override fun execute() {
        to?.let {
            view.animate()
                    .alpha(it)
            // TODO duration

        }
    }
}