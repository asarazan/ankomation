package net.sarazan.ankomation

import android.view.View

class Visibility(set: AnkomationSet, view: View, val visibility: Int) : Ankomation(set, view) {
    override fun start() {
        view!!.visibility = visibility
    }
}