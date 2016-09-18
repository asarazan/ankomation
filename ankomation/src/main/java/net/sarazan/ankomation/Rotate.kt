package net.sarazan.ankomation

import android.view.View

class Rotate : Ankomation {

    constructor(parent: AnkomationSet, view: View) : super(parent, view)

    var from: Float? = null
    var to: Float? = null

    override fun start(pass: Int): Boolean {
        if (pass != 0) return false
        from?.let { view!!.rotation = it }
        to?.let {
            animate().apply { rotation(it) }
        }
        return true
    }
}