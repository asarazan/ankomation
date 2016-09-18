package net.sarazan.ankomation

import android.view.View

class Scale : Ankomation {

    constructor(parent: AnkomationSet, view: View) : super(parent, view)

    var xFrom: Float? = null
    var xTo: Float? = null
    var yFrom: Float? = null
    var yTo: Float? = null

    override fun onStart(pass: Int): Boolean {
        if (pass != 0) return false
        view?.let {
            view ->

            xFrom?.let { view.scaleX = it }
            yFrom?.let { view.scaleY = it }

            animate(pass).apply {
                xTo?.let { scaleX(it) }
                yTo?.let { scaleY(it) }
            }
        }
        return true
    }
}