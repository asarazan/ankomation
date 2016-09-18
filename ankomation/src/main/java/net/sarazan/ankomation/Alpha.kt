package net.sarazan.ankomation

import android.view.View

class Alpha : Ankomation {

    constructor(set: AnkomationSet, view: View) : super(set, view)

    var from: Float? = null
    var to: Float? = null

    override fun start() {
        from?.let { view!!.alpha = it }
        to?.let { animate().alpha(it).start() }
    }
}