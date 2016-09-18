package net.sarazan.ankomation

import android.view.View
import android.view.View.*

/**
 * Created by Aaron Sarazan on 9/17/16
 */
class AnkomationSet : Ankomation {

    constructor(parent: AnkomationSet?) : super(parent)

    private val operations = mutableListOf<Ankomation>()
    private val self = this // to make constructors easier

    init {

    }

    private inline fun <T : Ankomation> T.initChild(fn: T.() -> Unit): T {
        return apply(fn)
    }

    fun View.scale(fn: Scale.() -> Unit) {
        operations.add(Scale(self, this).initChild(fn))
    }

    fun View.alpha(fn: Alpha.() -> Unit) {
        operations.add(Alpha(self, this).initChild(fn))
    }

    fun View.rotate(fn: Rotate.() -> Unit) {
        operations.add(Rotate(self, this).initChild(fn))
    }

    fun then(fn: AnkomationSet.() -> Unit) {
        operations.add(AnkomationSet(self).initChild(fn))
    }

    fun View.show() {
        operations.add(Visibility(self, this, VISIBLE).initChild {  })
    }

    fun View.hide() {
        operations.add(Visibility(self, this, INVISIBLE).initChild {  })
    }

    fun View.disappear() {
        operations.add(Visibility(self, this, GONE).initChild {  })
    }

    override fun start() {
        operations.forEach { it.prepare() }
        operations.forEach { it.start() }
    }
}