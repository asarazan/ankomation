package net.sarazan.ankomation

import android.view.View

class Rotate : Ankomation {

    constructor(set: AnkomationSet, view: View) : super(set, view)

    var from: Float? = null
    var to: Float? = null

    override fun start() {
        from?.let { view!!.rotation = it }
        to?.let {
            animate().apply { rotation(it) }
        }
    }
}