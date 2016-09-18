package net.sarazan.ankomation

import android.view.View

class Rotate : Ankomation {

    constructor(set: AnkomationSet, view: View) : super(set, view)

    var from: Float? = null
    var to: Float? = null

    override fun prepare() {
        from?.let { view!!.rotation = it }
    }

    override fun start() {
        to?.let {
            ankomate().apply { rotation(it) }
        }
    }
}