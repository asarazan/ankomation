package net.sarazan.ankomation

import android.view.View

class Scale : Ankomation {

    constructor(set: AnkomationSet, view: View) : super(set, view)

    var xFrom: Float? = null
    var xTo: Float? = null
    var yFrom: Float? = null
    var yTo: Float? = null

    override fun start() {
        view?.let {
            view ->

            xFrom?.let { view.scaleX = it }
            yFrom?.let { view.scaleY = it }

            animate().apply {
                xTo?.let { scaleX(it) }
                yTo?.let { scaleY(it) }
            }
        }
    }
}