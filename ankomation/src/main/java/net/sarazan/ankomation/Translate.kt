package net.sarazan.ankomation

import android.view.View

class Translate : Ankomation {

    constructor(parent: AnkomationSet, view: View) : super(parent, view)

    var xFrom: Float? = null
    var xFromPercent: Float? = null
    var xTo: Float? = null
    var xToPercent: Float? = null
    var yFrom: Float? = null
    var yFromPercent: Float? = null
    var yTo: Float? = null
    var yToPercent: Float? = null

    override fun onStart(pass: Int): Boolean {
        if (pass != 0) return false
        view?.let {
            view ->

            xFrom?.let { view.translationX = it }
            xFromPercent?.let { view.translationX = it * view.measuredWidth }
            yFrom?.let { view.translationY = it }
            yFromPercent?.let { view.translationY = it * view.measuredHeight }

            animate(pass).apply {
                xTo?.let { translationX(it) }
                xToPercent?.let { translationX(it * view.measuredWidth) }
                yTo?.let { translationY(it) }
                yToPercent?.let { translationY(it * view.measuredHeight) }
            }
        }
        return true
    }
}