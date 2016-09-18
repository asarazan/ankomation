package net.sarazan.ankomation

import android.view.View
import android.view.View.*

/**
 * Created by Aaron Sarazan on 9/17/16
 */
class AnkomationSetImpl : TimeAnkomationImpl(), AnkomationSet {

    private val operations = mutableListOf<Ankomation>()

    init {

    }

    override fun View.scale(fn: Scale.() -> Unit) {
        operations.add(Scale(this).apply(fn))
    }

    override fun View.alpha(fn: Alpha.() -> Unit) {
        operations.add(Alpha(this).apply(fn))
    }

    override fun View.rotate(fn: Rotate.() -> Unit) {
        operations.add(Rotate(this).apply(fn))
    }

    override fun then(fn: AnkomationSet.() -> Unit) {
        operations.add(AnkomationSetImpl().apply(fn))
    }

    override fun View.show() {
        operations.add(Visibility(this, VISIBLE))
    }

    override fun View.hide() {
        operations.add(Visibility(this, INVISIBLE))
    }

    override fun View.disappear() {
        operations.add(Visibility(this, GONE))
    }

    override fun prepare() {
        operations.forEach { it.prepare() }
    }

    override fun execute() {
        operations.forEach { it.execute() }
    }
}