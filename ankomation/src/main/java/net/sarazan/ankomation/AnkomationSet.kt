package net.sarazan.ankomation

import android.view.View
import android.view.View.*

/**
 * Created by Aaron Sarazan on 9/17/16
 */
open class AnkomationSet : Ankomation {

    constructor(parent: AnkomationSet?) : super(parent)

    private val children = mutableListOf<Ankomation>()
    private val self = this // to make constructors easier

    init {

    }
    
    fun add(child: Ankomation) {
        children.add(child)
    }

    fun View.scale(fn: Scale.() -> Unit) {
        children.add(Scale(self, this).apply(fn))
    }

    fun View.alpha(fn: Alpha.() -> Unit) {
        children.add(Alpha(self, this).apply(fn))
    }

    fun View.rotate(fn: Rotate.() -> Unit) {
        children.add(Rotate(self, this).apply(fn))
    }

    fun View.show() {
        children.add(Visibility(self, this, VISIBLE))
    }

    fun View.hide() {
        children.add(Visibility(self, this, INVISIBLE))
    }

    fun View.disappear() {
        children.add(Visibility(self, this, GONE))
    }

    fun set(fn: AnkomationSet.() -> Unit) {
        children.add(AnkomationSet(self).apply(fn))
    }

    fun then(fn: AnkomationSet.() -> Unit) {
        children.add(Then(self).apply(fn))
    }

    override fun start() {
        children.forEach { it.start() }
    }

    private var completeCount = 0
    internal fun onChildComplete(child: Ankomation) {
        if (completeCount++ == children.size) {
            // TODO
        }
    }
}