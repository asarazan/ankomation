package net.sarazan.ankomation

/**
 * Created by Aaron Sarazan on 9/18/16
 */
class Run(parent: AnkomationSet, val fn: () -> Unit) : Ankomation(parent, null) {

    override fun onStart(pass: Int): Boolean {
        if (pass != 0) return false
        fn()
        finish(pass)
        return true
    }
}