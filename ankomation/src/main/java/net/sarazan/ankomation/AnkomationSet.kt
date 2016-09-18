package net.sarazan.ankomation

import android.view.View
import android.view.View.*

/**
 * Created by Aaron Sarazan on 9/17/16
 */
open class AnkomationSet : Ankomation {

    constructor(parent: AnkomationSet?) : super(parent)

    private val children = mutableListOf<Ankomation>()
    private var pass = 0

    private var runningCount = 0
    private var completeCount = 0
    private var completeThisPass = 0

    private val self = this // to make constructors easier

    var isComplete: Boolean = false
        private set
    
    fun add(child: Ankomation) {
        children.add(child)
    }

    fun View.scale(fn: Scale.() -> Unit) {
        add(Scale(self, this).apply(fn))
    }

    fun View.alpha(fn: Alpha.() -> Unit) {
        add(Alpha(self, this).apply(fn))
    }

    fun View.rotate(fn: Rotate.() -> Unit) {
        add(Rotate(self, this).apply(fn))
    }

    fun View.show() {
        add(Visibility(self, this, VISIBLE))
    }

    fun View.hide() {
        add(Visibility(self, this, INVISIBLE))
    }

    fun View.disappear() {
        add(Visibility(self, this, GONE))
    }

    fun run(fn: () -> Unit) {
        add(Run(self, fn))
    }

    fun and(fn: AnkomationSet.() -> Unit) {
        add(AnkomationSet(self).apply(fn))
    }

    fun then(fn: AnkomationSet.() -> Unit) {
        add(Then(self).apply(fn))
    }

    override fun start(pass: Int): Boolean {
        if (children.isEmpty()) {
            isComplete = true
            return true
        }

        var count = 0
        children.forEach {
            if (it.start(pass)) {
                count++
            }
        }
        runningCount = count
        completeThisPass = 0

        if (runningCount == 0 && completeCount < children.size) {
            return nextPass()
        }

        return true
    }

    internal fun onChildComplete(child: Ankomation) {
        completeCount++
        if (completeThisPass++ == runningCount) {
            nextPass()
        }
    }

    private fun nextPass(): Boolean {
        return start(++pass)
    }
}