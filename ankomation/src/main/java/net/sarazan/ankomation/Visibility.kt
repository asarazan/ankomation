package net.sarazan.ankomation

import android.util.Log
import android.view.View

class Visibility(parent: AnkomationSet, view: View, val visibility: Int) : Ankomation(parent, view) {
    override fun onStart(pass: Int): Boolean {
        if (pass != 0) return false
        Log.d("Anim", "[$pass] Visibility")
        view!!.visibility = visibility
        finish(pass)
        return true
    }
}