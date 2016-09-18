package net.sarazan.ankomation.properties

import android.animation.PropertyValuesHolder
import android.view.View
import net.sarazan.ankomation.Ankomation
import net.sarazan.ankomation.AnkomationSet

class Rotate : Ankomation {

    constructor(parent: AnkomationSet, view: View) : super(parent, view)

    var from: Float? = null
    var to: Float? = null

    override fun onStart(pass: Int): Boolean {
        if (pass != 0) return false
        from?.let { view!!.rotation = it }
        to?.let {
            animate(pass).apply {
                setValues(PropertyValuesHolder.ofFloat("rotation", it))
                start()
            }
        }
        return true
    }
}