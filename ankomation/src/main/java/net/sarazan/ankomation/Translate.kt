package net.sarazan.ankomation

import android.view.View

class Translate : Ankomation {

    constructor(set: AnkomationSet, view: View) : super(set, view)

    var xFrom: Float? = null
    var xFromPercent: Float? = null
    var xTo: Float? = null
    var xToPercent: Float? = null
    var yFrom: Float? = null
    var yFromPercent: Float? = null
    var yTo: Float? = null
    var yToPercent: Float? = null

    override fun prepare() {
        view?.let {
            view ->
            xFrom?.let { view.x = it }
            xFromPercent?.let { view.x = it * view.measuredWidth }
            yFrom?.let { view.y = it }
            yFromPercent?.let { view.y = it * view.measuredHeight }
        }
    }

    override fun start() {
        view?.let {
            view ->
            ankomate().apply { 
                xTo?.let { translationX(it) }
                xToPercent?.let { translationX(it * view.measuredWidth) }
                yTo?.let { translationY(it) }
                yToPercent?.let { translationY(it * view.measuredHeight) }
            }
        }
    }
}