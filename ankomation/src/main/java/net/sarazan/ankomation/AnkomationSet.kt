package net.sarazan.ankomation

import android.os.Handler
import android.view.View
import net.sarazan.ankomation.properties.*

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

    fun add(child: Ankomation) {
        children.add(child)
    }

    fun scale(v: View, fn: Scale.() -> Unit) {
        add(Scale(self, v).apply(fn))
    }

    fun alpha(v: View, fn: Alpha.() -> Unit) {
        add(Alpha(self, v).apply(fn))
    }

    fun rotate(v: View, fn: Rotate.() -> Unit) {
        add(Rotate(self, v).apply(fn))
    }

    fun translate(v: View, fn: Translate.() -> Unit) {
        add(Translate(self, v).apply(fn))
    }

    fun translateFraction(v: View, fn: TranslateFraction.() -> Unit) {
        add(TranslateFraction(self, v).apply(fn))
    }

    fun visible(v: View) {
        add(Visibility(self, v, View.VISIBLE))
    }

    fun invisible(v: View) {
        add(Visibility(self, v, View.INVISIBLE))
    }

    fun gone(v: View) {
        add(Visibility(self, v, View.GONE))
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
        // Hack to make sure onLayout can get called e.g. if something was View.GONE
        Handler().post {
            executePass(++pass)
        }
    }
}