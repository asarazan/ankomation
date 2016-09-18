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

    fun View.translate(fn: Translate.() -> Unit) {
        add(Translate(self, this).apply(fn))
    }

    fun View.visible() {
        add(Visibility(self, this, VISIBLE))
    }

    fun View.invisible() {
        add(Visibility(self, this, INVISIBLE))
    }

    fun View.gone() {
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

    override fun onStart(pass: Int): Boolean {
        if (pass != 0) return false
        if (children.isEmpty()) {
            isComplete = true
            return true
        }
        executePass(pass)
        return true
    }

    private fun executePass(pass: Int) {
        var count = 0
        completeThisPass = 0
        children.forEach {
            if (it.start(pass)) {
                count++
            }
        }
        runningCount = count
        if (completeCount < children.size && completeThisPass == runningCount) {
            nextPass()
        }
    }

    internal fun onChildComplete(child: Ankomation) {
        completeCount++
        completeThisPass++

        if (completeCount == children.size) {
            finish(pass)
        } else if (completeThisPass == runningCount) {
            nextPass()
        }
    }

    private fun nextPass() {
        executePass(++pass)
    }
}