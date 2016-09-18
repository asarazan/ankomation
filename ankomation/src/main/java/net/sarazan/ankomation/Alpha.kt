package net.sarazan.ankomation

import android.view.View

class Alpha : Ankomation {

    constructor(parent: AnkomationSet, view: View) : super(parent, view)

    var from: Float? = null
    var to: Float? = null

    override fun onStart(pass: Int): Boolean {
        if (pass != 0) return false
        from?.let { view!!.alpha = it }
        to?.let { animate(pass).alpha(it).start() }
        return true
    }
}