package net.sarazan.ankomation.properties

import android.view.View
import net.sarazan.ankomation.Ankomation
import net.sarazan.ankomation.AnkomationSet

class Visibility(parent: AnkomationSet, view: View, val visibility: Int) : Ankomation(parent, view) {
    override fun onStart(pass: Int): Boolean {
        if (pass != 0) return false
        view!!.visibility = visibility
        finish(pass)
        return true
    }
}