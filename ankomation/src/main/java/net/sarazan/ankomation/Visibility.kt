package net.sarazan.ankomation

import android.view.View

class Visibility(parent: AnkomationSet, view: View, val visibility: Int) : Ankomation(parent, view) {
    override fun start(pass: Int): Boolean {
        if (pass != 0) return false
        view!!.visibility = visibility
        parent!!.onChildComplete(this)
        return true
    }
}